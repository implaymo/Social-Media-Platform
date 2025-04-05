package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapperImpl;
import com.SocialMediaPlatform.Service.UserLoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthLoginController {

    private final UserLoginMapperImpl userLoginMapper;
    private final UserLoginService userLoginService;


    public AuthLoginController(UserLoginMapperImpl userLoginMapper, UserLoginService userLoginService) {
        this.userLoginMapper = userLoginMapper;
        this.userLoginService = userLoginService;
    }

    @PostMapping(path = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        try {
            User user = userLoginMapper.toEntityForLogin(userLoginDto);
            String token = userLoginService.loginUser(user);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials: " + e.getMessage());
        }
    }
}
