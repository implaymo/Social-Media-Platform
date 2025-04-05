package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Interface.IUserRegistrationService;
import com.SocialMediaPlatform.Mapper.UserRegisterMapperImpl;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserRegistrationServiceImpl implements IUserRegistrationService {

    private final IUserRepository iUserRepository;
    private final PasswordHash passwordHash;
    private final PasswordSalt passwordSalt;

    public UserRegistrationServiceImpl(IUserRepository iUserRepository, UserRegisterMapperImpl userRegisterMapper,
                                       PasswordHash passwordHash, PasswordSalt passwordSalt) {
        this.iUserRepository = iUserRepository;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    @Transactional
    public Optional<User> registerUser(User user) {
        if (user == null) {
            return Optional.empty();
        }
        if (isUserInDatabase(user).isPresent()) {
            return Optional.empty();
        }

        try {
            byte[] salt = passwordSalt.generateRandomSalt();
            String base64Salt = Base64.getEncoder().encodeToString(salt);
            user.setSalt(base64Salt);
            String hashedPassword = passwordHash.generateHashPassword(user.getPassword(), salt);
            user.setPassword(hashedPassword);
            iUserRepository.save(user);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<User> isUserInDatabase(User user) {
        return iUserRepository.findByEmail(user.getEmail());
    }
}
