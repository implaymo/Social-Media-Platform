package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Interface.Registration.IUserRegistrationMapper;
import com.SocialMediaPlatform.Interface.Registration.IUserRegistrationService;
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

    private final IUserRegistrationMapper iUserRegistrationMapper;
    private final IUserRegistrationService iUserRegistrationService;

    public AuthRegistrationController(IUserRegistrationMapper iUserRegistrationMapper, IUserRegistrationService iUserRegistrationService) {
        if (iUserRegistrationMapper == null || iUserRegistrationService == null) {
            throw new IllegalArgumentException("Mapper and Service cannot be null");
        }
        this.iUserRegistrationMapper = iUserRegistrationMapper;
        this.iUserRegistrationService = iUserRegistrationService;
    }

    @PostMapping(path = "/auth/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        User user = iUserRegistrationMapper.toEntityForRegistration(userRegisterDto);
        Optional<User> result = iUserRegistrationService.registerUser(user);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
        return ResponseEntity.ok(true);
    }

}
