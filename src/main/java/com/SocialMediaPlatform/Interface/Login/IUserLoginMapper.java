package com.SocialMediaPlatform.Interface.Login;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Domain.User;

public interface IUserLoginMapper {
    User toEntityForLogin(UserLoginDto userLoginDto);
}
