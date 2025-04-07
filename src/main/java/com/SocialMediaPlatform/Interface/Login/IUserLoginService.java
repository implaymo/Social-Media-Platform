package com.SocialMediaPlatform.Interface.Login;

import com.SocialMediaPlatform.Domain.User;

import javax.naming.AuthenticationException;

public interface IUserLoginService {
    String loginUser(User user) throws AuthenticationException;
}
