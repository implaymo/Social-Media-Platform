package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.UserRepository;
import com.SocialMediaPlatform.Security.JWTUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

public class UserLoginService {

    private final UserRepository userRepository;
    private final UserLoginMapper userLoginMapper;
    private final PasswordHash passwordHash;
    private final JWTUtil jwtUtil;

    public UserLoginService(UserRepository userRepository, UserLoginMapper userLoginMapper, PasswordHash passwordHash, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userLoginMapper = userLoginMapper;
        this.passwordHash = passwordHash;
        this.jwtUtil = jwtUtil;
    }


    @Transactional
    public String loginUser(UserLoginDto userLoginDto) {
        if (userLoginDto == null) {
            return null;
        }

        User user = userLoginMapper.toEntityForLogin(userLoginDto);
        Optional<User> userInDatabase = userRepository.findByEmail(user.getEmail());

        if (userInDatabase.isPresent()) {
            User userEntity = userInDatabase.get();
            byte[] saltFromUserInDatabase = transformBase64SaltIntoByte(userEntity);
            String passwordProvidedInLoginHashed = hashPasswordProvidedToLogin(user, saltFromUserInDatabase);

            if (Objects.equals(passwordProvidedInLoginHashed, userEntity.getPassword())) {
                return jwtUtil.generateToken(userEntity.getEmail());
            }
        }
        return null;
    }

    private byte[] transformBase64SaltIntoByte(User userInDatabase) {
        String userInDatabaseSalt = userInDatabase.getSalt();
        return Base64.getDecoder().decode(userInDatabaseSalt);
    }

    private String hashPasswordProvidedToLogin(User user, byte[] userInDatabaseSaltInBytes) {
        String passwordTriedToLogin = user.getPassword();
        return passwordHash.generateHashPassword(passwordTriedToLogin,
                userInDatabaseSaltInBytes);
    }
}
