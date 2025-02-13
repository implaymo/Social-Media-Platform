package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;

public class UserRegisterMapper {


    public User toEntity(UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            return null;
        }
        return User.builder()
                .name(userRegisterDto.getName())
                .email(userRegisterDto.getEmail())
                .password(userRegisterDto.getPassword())
                .build();
    }
}
