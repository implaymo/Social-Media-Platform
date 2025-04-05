package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserRegisterMapperImpl;
import com.SocialMediaPlatform.Service.UserRegistrationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthRegistrationController {

    private final UserRegisterMapperImpl userRegisterMapper;
    private final UserRegistrationServiceImpl userRegistrationServiceImpl;

    public AuthRegistrationController(UserRegisterMapperImpl userRegisterMapper, UserRegistrationServiceImpl userRegistrationServiceImpl) {
        this.userRegisterMapper = userRegisterMapper;
        this.userRegistrationServiceImpl = userRegistrationServiceImpl;
    }

    @PostMapping(path = "/auth/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        User user = userRegisterMapper.toEntityForRegistration(userRegisterDto);
        Optional<User> result = userRegistrationServiceImpl.registerUser(user);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
        return ResponseEntity.ok(true);
    }

}
