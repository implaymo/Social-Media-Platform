package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;

public class UserLoginMapper {

    public User toEntityForLogin(UserLoginDto userLoginDto) {
        if (userLoginDto == null) {
            return null;
        }
        return User.builder()
                .email(userLoginDto.getEmail())
                .password(userLoginDto.getPassword())
                .build();
    }
}
