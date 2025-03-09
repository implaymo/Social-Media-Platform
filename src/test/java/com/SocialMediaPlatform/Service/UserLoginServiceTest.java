package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.Repository.UserRepository;
import com.SocialMediaPlatform.Security.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.naming.AuthenticationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserLoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JWTUtil jwtUtil;

    private UserLoginService userLoginService;
    private UserLoginMapper userLoginMapper;
    private PasswordService passwordService;

    @BeforeEach
    void setUp() {
        userLoginMapper = mock(UserLoginMapper.class);
        passwordService = mock(PasswordService.class);
        userLoginService = new UserLoginService(userRepository, userLoginMapper, jwtUtil, passwordService);
    }


    @Test
    void loginUser_whenCredentialsValid_thenReturnToken() throws Exception {
        // Arrange
        String encryptedPassword = "DS89ADS8A0D9";

        User user = mock(User.class);
        when(user.getEmail()).thenReturn("test@example.com");
        when(user.getPassword()).thenReturn("encodedCorrectPassword");
        when(user.getSalt()).thenReturn("salt");

        User databaseUser = mock(User.class);
        when(databaseUser.getEmail()).thenReturn("test@example.com");
        when(databaseUser.getPassword()).thenReturn("DS89ADS8A0D9");

        when(databaseUser.getSalt()).thenReturn("salt");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(databaseUser));
        when(passwordService.encryptPasswordFromUserThatTriesToLogin(user, databaseUser)).thenReturn(encryptedPassword);
        when(jwtUtil.generateToken("test@example.com")).thenReturn("jwt-token");

        // Act
        String result = userLoginService.loginUser(user);

        // Assert
        assertEquals("jwt-token", result);
        verify(jwtUtil).generateToken("test@example.com");
    }

    @Test
    void shouldReturnExceptionMessageIfUserNotLoginSuccessfully() {
        // arrange
        User user = mock(User.class);
        User mappedUser = mock(User.class);
        when(mappedUser.getEmail()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        // act + assert
        assertThrows(AuthenticationException.class, () -> {
            userLoginService.loginUser(user);
        });
    }

    @Test
    void shouldReturnExceptionMessageIfUserNull() {
        // arrange
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> {
            userLoginService.loginUser(null);
        });    }

}