package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final PasswordHash passwordHash;
    private final PasswordSalt passwordSalt;

    public UserService(UserRepository userRepository, UserRegisterMapper userRegisterMapper,
                       PasswordHash passwordHash,PasswordSalt passwordSalt) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    @Transactional
    public boolean registerUser(UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            return false;
        }

        try {
            User user = userRegisterMapper.toEntityForRegistration(userRegisterDto);
            byte[] salt = passwordSalt.generateRandomSalt();
            String base64Salt = Base64.getEncoder().encodeToString(salt);
            user.setSalt(base64Salt);
            String hashedPassword = passwordHash.generateHashPassword(user.getPassword(), salt);
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return true;
        } catch (ConstraintViolationException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
//
//    @Transactional
//    public String loginUser(UserLoginDto userLoginDto) {
//
//    }
}
