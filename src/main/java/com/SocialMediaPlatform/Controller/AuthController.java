package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    public boolean registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return userService.registerUser(userRegisterDto);
    }

//    public String loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
//        return "";
//    }
}
