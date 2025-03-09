package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.Repository.UserRepository;
import com.SocialMediaPlatform.Security.JWTUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class UserLoginService {

    private final UserRepository userRepository;
    private final UserLoginMapper userLoginMapper;
    private final JWTUtil jwtUtil;
    private final PasswordService passwordService;

    public UserLoginService(UserRepository userRepository, UserLoginMapper userLoginMapper, JWTUtil jwtUtil, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.userLoginMapper = userLoginMapper;
        this.jwtUtil = jwtUtil;
        this.passwordService = passwordService;
    }


    @Transactional
    public String loginUser(User userLogin) throws Exception {
        if (userLogin == null) {
            throw new IllegalArgumentException("User login data is required.");
        }

        Optional<User> userOptional = userRepository.findByEmail(userLogin.getEmail());

        if (userOptional.isPresent()) {
            User databaseUser = userOptional.get();
            String providedPassword = passwordService.encryptPasswordFromUserThatTriesToLogin(userLogin, databaseUser);
            if (providedPassword.equals(databaseUser.getPassword())) {
                return jwtUtil.generateToken(databaseUser.getEmail());
            }
        }
        throw new AuthenticationException("Invalid email or password.");
    }
}
