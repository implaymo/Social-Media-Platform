package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Interface.IUserLoginMapper;
import org.springframework.stereotype.Component;

@Component
public class UserLoginMapper implements IUserLoginMapper {

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
