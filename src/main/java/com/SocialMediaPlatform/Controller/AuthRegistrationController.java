package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public AuthRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }


    public boolean registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return userRegistrationService.registerUser(userRegisterDto);
    }

    public String loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        return "";
    }
}
