package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.UUID;

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
        User mappedUser = mock(User.class);
        UserRegisterDto userRegisterDto = mock(UserRegisterDto.class);
        byte[] salt = "salt".getBytes();

        when(mappedUser.getPassword()).thenReturn("John@1234");
        when(userRegisterMapper.toEntityForRegistration(userRegisterDto)).thenReturn(mappedUser);
        when(passwordSalt.generateRandomSalt()).thenReturn(salt);
        when(passwordHash.generateHashPassword(mappedUser.getPassword(), salt)).thenReturn("passwordHash");
        // act
        Optional<User> registerUser = userRegistrationService.registerUser(userRegisterDto);
        // assert
        assertTrue(registerUser.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalIfUserRegisterDtoIsNull() {
        // arrange
        UserRegisterDto userRegisterDto = null;
        // act
        Optional<User> registerUser = userRegistrationService.registerUser(userRegisterDto);
        // assert
        assertTrue(registerUser.isEmpty());
    }

}