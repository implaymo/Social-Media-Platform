package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserLoginService userLoginService;

    @BeforeEach
    void setUp() {
        UserLoginMapper userLoginMapper = new UserLoginMapper();
        MockitoAnnotations.openMocks(this);
        userLoginService = new UserLoginService(userRepository, userLoginMapper);
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
        assertEquals("lol1234", loginUser);
    }

    @Test
    void shouldReturnBlankIfUserNotLoginSuccessfully(){
        // arrange
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        // act
        String loginUser = userLoginService.loginUser(userLoginDto);
        // assert
        assertEquals("", loginUser);
    }

}