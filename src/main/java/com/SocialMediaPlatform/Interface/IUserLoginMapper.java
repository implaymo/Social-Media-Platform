package com.SocialMediaPlatform.Interface;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;

public interface IUserLoginMapper {
    User toEntityForLogin(UserLoginDto userLoginDto);
}
