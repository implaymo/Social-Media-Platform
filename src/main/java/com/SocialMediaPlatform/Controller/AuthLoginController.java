package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Interface.Login.IUserLoginMapper;
import com.SocialMediaPlatform.Interface.Login.IUserLoginService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthLoginController {

    private final IUserLoginMapper userLoginMapper;
    private final IUserLoginService userLoginService;


    public AuthLoginController(IUserLoginMapper userLoginMapper, IUserLoginService userLoginService) {
        if (userLoginMapper == null || userLoginService == null) {
            throw new IllegalArgumentException("UserLoginMapper and UserLoginService cannot be null");
        }
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
