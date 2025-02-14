package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;

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

    public boolean registerUser(UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            return false;
        }

        try {
            User user = userRegisterMapper.toEntity(userRegisterDto);
            byte[] salt = passwordSalt.generateRandomSalt();
            user.setSalt(salt);
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
}
