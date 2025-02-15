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

class AuthLoginControllerTest {

    private UserLoginService userLoginService;
    private UserLoginMapper userLoginMapper;
    private PasswordHash passwordHash;


    @Mock
    UserRepository userRepository;

    @Mock
    private JWTUtil jwtUtil;

    private String token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userLoginMapper = new UserLoginMapper();
        passwordHash = new PasswordHash();
        jwtUtil = new JWTUtil();
        userLoginService = new UserLoginService(userRepository, userLoginMapper, passwordHash,jwtUtil);


    }


//    @Test
//    void shouldReturnTrueIfUserLoginSuccessfully(){
//        // arrange
//        UserLoginDto userLoginDto = UserLoginDto.builder()
//                .email("john123@gmail.com")
//                .password("John@12345")
//                .build();
//        User user = userLoginMapper.toEntityForLogin(userLoginDto);
//
//        // act
//        String userLoginToken = authController.loginUser(userLoginDto);
//        // assert
//
//    }
}