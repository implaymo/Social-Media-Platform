package com.SocialMediaPlatform.Interface;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;

public interface IUserRegistrationMapper {
    User toEntityForRegistration(UserRegisterDto userRegisterDto);
}
