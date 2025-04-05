package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserRegistrationService userRegistrationService;
    private UserRegisterMapper userRegisterMapper;
    private PasswordHash passwordHash;
    private PasswordSalt passwordSalt;

    @BeforeEach
    void setUp() {
        passwordSalt = mock(PasswordSalt.class);
        passwordHash = mock(PasswordHash.class);
        userRegisterMapper = mock(UserRegisterMapper.class);
        userRegistrationService = new UserRegistrationService(userRepository, userRegisterMapper, passwordHash, passwordSalt);
    }

    // Registration method tests
    @Test
    void shouldReturnPresentOptionalIfUserRegistered() {
        // arrange
        User user = mock(User.class);
        byte[] salt = "salt".getBytes();

        when(user.getPassword()).thenReturn("John@1234");
        when(passwordSalt.generateRandomSalt()).thenReturn(salt);
        when(passwordHash.generateHashPassword(user.getPassword(), salt)).thenReturn("passwordHash");
        // act
        Optional<User> registerUser = userRegistrationService.registerUser(user);
        // assert
        assertTrue(registerUser.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalIfUserRegisterDtoIsNull() {
        // arrange
        User user = null;
        // act
        Optional<User> registerUser = userRegistrationService.registerUser(user);
        // assert
        assertTrue(registerUser.isEmpty());
    }

}