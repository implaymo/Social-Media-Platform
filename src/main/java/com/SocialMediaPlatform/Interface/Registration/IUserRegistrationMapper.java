package com.SocialMediaPlatform.Interface.Registration;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Domain.User;

public interface IUserRegistrationMapper {
    User toEntityForRegistration(UserRegisterDto userRegisterDto);
}
