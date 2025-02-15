package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Service.UserLoginService;
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
    void shouldReturnTokenWhenUserLogsInSuccessfully(){
        // arrange
        // act
        String userLoginToken = authLoginController.loginUser(userLoginDto);
        // assert
        assertEquals(token, userLoginToken);

    }

    @Test
    void shouldReturnEmptyStringWhenUserEmailNotInDatabase() {
        // arrange
        userLoginDto = UserLoginDto.builder()
                .email("johnhatanluisdematos123@gmail.com")
                .password("John@12345")
                .build();
        // act
        String userLoginToken = authLoginController.loginUser(userLoginDto);
        // assert
        assertNotEquals(token, userLoginToken);

    }

    @Test
    void shouldReturnEmptyStringWhenUserPasswordWrong() {
        // arrange
        userLoginDto = UserLoginDto.builder()
                .email("john123@gmail.com")
                .password("john@12345")
                .build();
        // act
        String userLoginToken = authLoginController.loginUser(userLoginDto);
        // assert
        assertNotEquals(token, userLoginToken);

    }
}