package com.SocialMediaPlatform.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Service.UserLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AuthLoginControllerTest {

    @Mock
    private UserLoginService userLoginService;

    @InjectMocks
    private AuthLoginController authLoginController;

    private MockMvc mockMvc;

    private UserLoginDto validUserLoginDto;
    private UserLoginDto invalidUserLoginDto;
    private String token;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authLoginController).build();

        validUserLoginDto = UserLoginDto.builder()
                .email("validuser@example.com")
                .password("ValidPass123!")
                .build();

        invalidUserLoginDto = UserLoginDto.builder()
                .email("invaliduser@example.com")
                .password("WrongPass")
                .build();

        token = "dummy_token_12345";
    }

    @Test
    void shouldReturnTokenWhenUserLogsInSuccessfully() throws Exception {
        // arrange
        when(userLoginService.loginUser(validUserLoginDto)).thenReturn(token);
        // act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content("{\"email\": \"validuser@example.com\", \"password\": \"ValidPass123!\"}"))
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(content().string(token));
    }

    @Test
    void shouldReturnUnauthorizedWhenUserLogsInWithInvalidCredentials() throws Exception {
        // arrange
        when(userLoginService.loginUser(invalidUserLoginDto)).thenThrow(new RuntimeException("Invalid credentials"));

        // act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content("{\"email\": \"invaliduser@example.com\", \"password\": \"WrongPass\"}"))
                .andExpect(status().isUnauthorized())  // Expect HTTP 401 Unauthorized
                .andExpect(content().string("Invalid credentials: Invalid credentials"));
    }

//    @Test
//    void shouldReturnUnauthorizedWhenEmailIsMissing() throws Exception {
//        // arrange
//        UserLoginDto userLoginDto = UserLoginDto.builder()
//                .password("ValidPass123!")
//                .build();
//
//        when(userLoginService.loginUser(userLoginDto)).thenThrow(new RuntimeException("Invalid credentials"));
//
//        // act & assert
//        mockMvc.perform(post("/auth/login")
//                        .contentType("application/json")
//                        .content("{\"password\": \"ValidPass123\"}"))
//                .andExpect(status().isUnauthorized())  // Expect HTTP 401 Unauthorized
//                .andExpect(content().string("Invalid credentials: Invalid credentials"));  // Expect the error message
//    }
//
//    @Test
//    void shouldReturnUnauthorizedWhenPasswordIsMissing() throws Exception {
//        // arrange
//        UserLoginDto userLoginDto = UserLoginDto.builder()
//                .email("validuser@example.com")
//                .build();
//
//        when(userLoginService.loginUser(userLoginDto)).thenThrow(new RuntimeException("Invalid credentials"));
//
//        // act & assert
//        mockMvc.perform(post("/auth/login")
//                        .contentType("application/json")
//                        .content("{\"email\": \"validuser@example.com\"}"))
//                .andExpect(status().isUnauthorized())  // Expect HTTP 401 Unauthorized
//                .andExpect(content().string("Invalid credentials: Invalid credentials"));  // Expect the error message
//    }
}
