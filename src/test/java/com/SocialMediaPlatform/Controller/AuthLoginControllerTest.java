package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.UserRepository;
import com.SocialMediaPlatform.Security.JWTUtil;
import com.SocialMediaPlatform.Service.UserLoginService;
import com.SocialMediaPlatform.Service.UserRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthLoginControllerTest {

    @Mock
    UserLoginService userLoginService;

    private AuthLoginController authLoginController;
    private UserLoginDto userLoginDto;
    private String token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authLoginController = new AuthLoginController(userLoginService);

        // Build UserLoginDto
        userLoginDto = UserLoginDto.builder()
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        token = "dummy_token_12345";
        when(userLoginService.loginUser(userLoginDto)).thenReturn(token);
    }


    @Test
    void shouldReturnTrueIfUserLoginSuccessfully(){
        // arrange
        // act
        String userLoginToken = authLoginController.loginUser(userLoginDto);
        // assert
        assertEquals(token, userLoginToken);

    }
}