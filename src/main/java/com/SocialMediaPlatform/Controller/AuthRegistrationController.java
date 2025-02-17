package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthRegistrationController {


    private final UserRegistrationService userRegistrationService;

    public AuthRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping(path = "/auth/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        try {
            boolean result = userRegistrationService.registerUser(userRegisterDto);
            if (!result) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
            }
            return org.springframework.http.ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service failure", e);
        }
    }

}
