package com.SocialMediaPlatform.Interface;

import com.SocialMediaPlatform.Entity.User;

import java.util.Optional;

public interface IUserRegistrationService {
    Optional<User> registerUser(User user);
}
