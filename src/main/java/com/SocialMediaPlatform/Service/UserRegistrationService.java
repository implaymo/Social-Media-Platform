package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final PasswordHash passwordHash;
    private final PasswordSalt passwordSalt;

    public UserRegistrationService(UserRepository userRepository, UserRegisterMapper userRegisterMapper,
                                   PasswordHash passwordHash, PasswordSalt passwordSalt) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    @Transactional
    public Optional<User> registerUser(UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            return Optional.empty();
        }
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            return Optional.empty(); // or throw a custom exception
        }

        try {
            User user = userRegisterMapper.toEntityForRegistration(userRegisterDto);
            byte[] salt = passwordSalt.generateRandomSalt();
            String base64Salt = Base64.getEncoder().encodeToString(salt);
            user.setSalt(base64Salt);
            String hashedPassword = passwordHash.generateHashPassword(user.getPassword(), salt);
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
