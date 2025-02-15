package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Service.UserLoginService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthLoginController {

    private final UserLoginService userLoginService;


    public AuthLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    public String loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        return userLoginService.loginUser(userLoginDto);
    }
}
