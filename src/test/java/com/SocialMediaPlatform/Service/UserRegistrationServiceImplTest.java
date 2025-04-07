package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Mapper.UserRegisterMapperImpl;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.IUserRepository;
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
class UserRegistrationServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    private UserRegistrationServiceImpl userRegistrationServiceImpl;
    private UserRegisterMapperImpl userRegisterMapper;
    private PasswordHash passwordHash;
    private PasswordSalt passwordSalt;

    @BeforeEach
    void setUp() {
        passwordSalt = mock(PasswordSalt.class);
        passwordHash = mock(PasswordHash.class);
        userRegisterMapper = mock(UserRegisterMapperImpl.class);
        userRegistrationServiceImpl = new UserRegistrationServiceImpl(userRepository, userRegisterMapper, passwordHash, passwordSalt);
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
        Optional<User> registerUser = userRegistrationServiceImpl.registerUser(user);
        // assert
        assertTrue(registerUser.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalIfUserRegisterDtoIsNull() {
        // arrange
        User user = null;
        // act
        Optional<User> registerUser = userRegistrationServiceImpl.registerUser(user);
        // assert
        assertTrue(registerUser.isEmpty());
    }

}