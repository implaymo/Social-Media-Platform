package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Interface.IUserRegistrationMapper;
import com.SocialMediaPlatform.Interface.IUserRegistrationService;
import com.SocialMediaPlatform.Mapper.UserRegisterMapperImpl;
import com.SocialMediaPlatform.Service.UserRegistrationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class AuthRegistrationControllerTest {



    @Mock
    private IUserRegistrationService iUserRegistrationService;

    @Mock
    private IUserRegistrationMapper iUserRegistrationMapper;

    @InjectMocks
    private AuthRegistrationController authRegistrationController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        authRegistrationController = new AuthRegistrationController(iUserRegistrationMapper, iUserRegistrationService);
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

        User user = User.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        when(iUserRegistrationService.registerUser(any(User.class)))
                .thenReturn(Optional.of(user));

        when(iUserRegistrationMapper.toEntityForRegistration(userRegisterDto)).thenReturn(user);

        // act & assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                .andExpect(status().isOk())  // Check status code
                .andExpect(content().string("true"));
    }

    @Test
    public void shouldReturnBadRequestIfUserRegisterDtoNull() throws Exception {
        // act & assert
        mockMvc.perform(post("/auth/register")
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
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnConflictIfUserAlreadyExists() throws Exception {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        User user = User.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        when(iUserRegistrationMapper.toEntityForRegistration(userRegisterDto)).thenReturn(user);
        when(iUserRegistrationService.registerUser(any(User.class)))
                .thenReturn(Optional.empty());

        // act & assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRegisterDto)))
                .andExpect(status().isConflict());
    }


}