package com.SocialMediaPlatform.Controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.Service.UserLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AuthLoginControllerTest {

    @Mock
    private UserLoginService userLoginService;

    @Mock
    private UserLoginMapper userLoginMapper;

    @InjectMocks
    private AuthLoginController authLoginController;

    private MockMvc mockMvc;
    private UserLoginDto validUserLoginDto;
    private UserLoginDto invalidPasswordUserLoginDto;
    private UserLoginDto invalidEmailUserLoginDto;
    private String token;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authLoginController).build();

        validUserLoginDto = UserLoginDto.builder()
                .email("validuser@example.com")
                .password("ValidPass123!")
                .build();

        invalidPasswordUserLoginDto = UserLoginDto.builder()
                .email("invaliduser@example.com")
                .password("WrongPass")
                .build();

        invalidEmailUserLoginDto = UserLoginDto.builder()
                .email("invaliduserexample.com")
                .password("ValidPass123!")
                .build();
        token = "dummy_token_12345";
    }

    @Test
    void shouldReturnTokenWhenUserLogsInSuccessfully() throws Exception {
        // arrange
        User user = mock(User.class);
        when(userLoginMapper.toEntityForLogin(validUserLoginDto)).thenReturn(user);
        when(userLoginService.loginUser(user)).thenReturn(token);
        // act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content("{\"email\": \"validuser@example.com\", \"password\": \"ValidPass123!\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(token));
    }

    @Test
    void shouldReturnUnauthorizedWhenUserLogsInWithInvalidPassword() throws Exception {
        // arrange
        when(userLoginMapper.toEntityForLogin(invalidPasswordUserLoginDto)).thenReturn(null);
        when(userLoginService.loginUser(null)).thenThrow(new RuntimeException("Invalid credentials"));

        // act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content("{\"email\": \"invaliduser@example.com\", \"password\": \"WrongPass\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials: Invalid credentials"));
    }

    @Test
    void shouldReturnUnauthorizedWhenUserLogsInWithInvalidEmail() throws Exception {
        // arrange
        when(userLoginMapper.toEntityForLogin(invalidEmailUserLoginDto)).thenReturn(null);
        when(userLoginService.loginUser(null)).thenThrow(new RuntimeException("Invalid credentials"));

        // act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content("{\"email\": \"invaliduserexample.com\", \"password\": \"ValidPass123!\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials: Invalid credentials"));
    }


    @Test
    void shouldReturnBadRequestWhenEmailIsMissing() throws Exception {
        // arrange
        // act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\": \"ValidPass123!\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenPasswordIsMissing() throws Exception {
        // arrange
        // act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"invaliduser@example.com\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenCredentialsMissing() throws Exception {
        // arrange
        // act & assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}
