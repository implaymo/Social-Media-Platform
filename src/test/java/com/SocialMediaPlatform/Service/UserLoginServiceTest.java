package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.Repository.UserRepository;
import com.SocialMediaPlatform.Security.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

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
        PasswordHash passwordHash = mock(PasswordHash.class);
        passwordService = new PasswordService(passwordHash);
        userLoginService = new UserLoginService(userRepository, userLoginMapper, jwtUtil, passwordService);
    }


    @Test
    void loginUser_whenCredentialsValid_thenReturnToken() throws Exception {
        // Arrange
        String encryptedPassword = "DS89ADS8A0D9";

        UserLoginDto dto = mock(UserLoginDto.class);
        User mappedUser = mock(User.class);
        when(mappedUser.getEmail()).thenReturn("test@example.com");
        when(mappedUser.getPassword()).thenReturn("encodedCorrectPassword");
        when(mappedUser.getSalt()).thenReturn("salt");

        User databaseUser = mock(User.class);
        when(databaseUser.getEmail()).thenReturn("test@example.com");
        when(databaseUser.getPassword()).thenReturn("DS89ADS8A0D9");

        // Stub getSalt() for the database user to return a non-null value.
        when(databaseUser.getSalt()).thenReturn("salt");
        when(userLoginMapper.toEntityForLogin(dto)).thenReturn(mappedUser);
        when(userRepository.findByEmail(mappedUser.getEmail())).thenReturn(Optional.of(databaseUser));
        when(passwordService.encryptPassword(mappedUser, databaseUser)).thenReturn(encryptedPassword);
        when(jwtUtil.generateToken("test@example.com")).thenReturn("jwt-token");

        // Act
        String result = userLoginService.loginUser(dto);

        // Assert
        assertEquals("jwt-token", result);
        verify(jwtUtil).generateToken("test@example.com");
    }

    @Test
    void shouldReturnExceptionMessageIfUserNotLoginSuccessfully() {
        // arrange
        UserLoginDto userLoginDto = mock(UserLoginDto.class);
        User mappedUser = mock(User.class);
        when(userLoginMapper.toEntityForLogin(userLoginDto)).thenReturn(mappedUser);
        when(mappedUser.getEmail()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            userLoginService.loginUser(userLoginDto);
        });
    }

    @Test
    void shouldReturnExceptionMessageIfUserLoginDtoNull() {
        // arrange
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> {
            userLoginService.loginUser(null);
        });    }

}