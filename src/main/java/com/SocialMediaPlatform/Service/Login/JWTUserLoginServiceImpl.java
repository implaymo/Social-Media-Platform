package com.SocialMediaPlatform.Service.Login;

import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Interface.Login.IUserLoginService;
import com.SocialMediaPlatform.Repository.IUserRepository;
import com.SocialMediaPlatform.Security.JWTUtil;
import com.SocialMediaPlatform.Service.Password.PasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class JWTUserLoginServiceImpl implements IUserLoginService {

    private final IUserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PasswordService passwordService;

    public JWTUserLoginServiceImpl(IUserRepository userRepository, JWTUtil jwtUtil, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordService = passwordService;
    }


    @Transactional
    public String loginUser(User userLogin) throws AuthenticationException {
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
