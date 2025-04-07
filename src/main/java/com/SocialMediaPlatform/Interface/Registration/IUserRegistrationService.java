package com.SocialMediaPlatform.Interface.Registration;

import com.SocialMediaPlatform.Domain.User;

import java.util.Optional;

public interface IUserRegistrationService {
    Optional<User> registerUser(User user);
}
