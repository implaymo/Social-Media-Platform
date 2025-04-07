package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Interface.Login.IUserLoginMapper;
import org.springframework.stereotype.Component;

@Component
public class UserLoginMapperImpl implements IUserLoginMapper {

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
