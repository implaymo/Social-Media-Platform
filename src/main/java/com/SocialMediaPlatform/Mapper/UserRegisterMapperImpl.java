package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Interface.IUserRegistrationMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapperImpl implements IUserRegistrationMapper {

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
