package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {


    public User toEntityForRegistration(UserRegisterDto userRegisterDto) {
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
