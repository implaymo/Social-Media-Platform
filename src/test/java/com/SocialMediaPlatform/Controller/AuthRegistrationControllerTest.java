package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Service.UserRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class AuthRegistrationControllerTest {


    private MockMvc mockMvc;

    @Mock
    private UserRegistrationService userRegistrationService;

    private AuthRegistrationController authRegistrationController;

    @BeforeEach
    void setUp() {
        authRegistrationController = new AuthRegistrationController(userRegistrationService);
        mockMvc = MockMvcBuilders.standaloneSetup(authRegistrationController).build();
    }

    @Test
    public void shouldReturnTrueIfValidUserRegistration() throws Exception {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        when(userRegistrationService.registerUser(any(UserRegisterDto.class)))
                .thenReturn(true);

        // act & assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                .andExpect(status().isOk())  // Check status code
                .andExpect(content().string("true"));
    }

    @Test
    public void shouldReturnBadRequestIfUserRegisterDtoNull() throws Exception {
        // act & assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestIfMissingRequiredFields() throws Exception {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .email("john123@gmail.com")
                .password("John@12345")
                .build(); // Missing name

        // act & assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnInternalServerErrorIfServiceFails() throws Exception {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        when(userRegistrationService.registerUser(any(UserRegisterDto.class)))
                .thenThrow(new RuntimeException("Service failure"));

        // act & assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnConflictIfUserAlreadyExists() throws Exception {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        when(userRegistrationService.registerUser(any(UserRegisterDto.class)))
                .thenReturn(false);

        // act & assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                .andExpect(status().isConflict());
    }


}