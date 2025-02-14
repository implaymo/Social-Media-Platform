package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.UserRepository;
import com.SocialMediaPlatform.Security.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Base64;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserLoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JWTUtil jwtUtil;

    private UserLoginService userLoginService;
    private String token;

    @BeforeEach
    void setUp() {
        UserLoginMapper userLoginMapper = new UserLoginMapper();
        PasswordHash passwordHash = new PasswordHash();
        PasswordSalt passwordSalt = new PasswordSalt();
        jwtUtil = new JWTUtil();
        MockitoAnnotations.openMocks(this);
        userLoginService = new UserLoginService(userRepository, userLoginMapper, passwordHash, jwtUtil);

        // Prepare the user with salt and hashed password
        User user = User.builder()
                .email("john123@gmail.com")
                .password("John@12345")  // Password before hashing
                .salt(Base64.getEncoder().encodeToString(passwordSalt.generateRandomSalt()))  // Generate and encode salt
                .build();

        // Hash the password before saving it
        String hashedPassword = passwordHash.generateHashPassword(user.getPassword(), Base64.getDecoder().decode(user.getSalt()));
        user.setPassword(hashedPassword);

        // Mock the repository to return the prepared user when searching by email
        when(userRepository.findByEmail("john123@gmail.com")).thenReturn(Optional.of(user));
        // Mock JWT TOKEN to test if user login is done successfully
        token = "dummy_token_12345";
        when(jwtUtil.generateToken("john123@gmail.com")).thenReturn(token);
    }


    @Test
    void shouldReturnTokenIfUserLoginSuccessfully(){
        // arrange
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        // act
        String loginUser = userLoginService.loginUser(userLoginDto);
        // assert
        assertEquals(token, loginUser);
    }

    @Test
    void shouldReturnBlankIfUserNotLoginSuccessfully(){
        // arrange
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("johnaTHanSIlva123@gmail.com")
                .password("john12345")
                .build();
        // act
        String loginUser = userLoginService.loginUser(userLoginDto);
        // assert
        assertEquals("", loginUser);
    }

}