package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Interface.Registration.IUserRegistrationMapper;
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
