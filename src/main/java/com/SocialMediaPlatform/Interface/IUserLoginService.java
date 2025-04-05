package com.SocialMediaPlatform.Interface;

import com.SocialMediaPlatform.Entity.User;

import javax.naming.AuthenticationException;

public interface IUserLoginService {
    String loginUser(User user) throws AuthenticationException;;
}
