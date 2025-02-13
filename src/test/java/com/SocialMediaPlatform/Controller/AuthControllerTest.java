package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.Repository.UserRepository;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import com.SocialMediaPlatform.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    private UserService userService;
    private UserRegisterMapper userRegisterMapper;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRegisterMapper = new UserRegisterMapper();
        userService = new UserService(userRepository, userRegisterMapper);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrueIfUserRegistered() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        // Create the User entity (without id)
        User user = userRegisterMapper.toEntity(userRegisterDto);

        // Mock the repository's save method to return the user with a generated id
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User savedUser = invocation.getArgument(0);
                    savedUser.setId(UUID.randomUUID().toString()); // Manually set the id for the mock
                    return savedUser;
                });

        // Mock the UserService and inject the mocked repository
        UserService userService = new UserService(userRepository, userRegisterMapper);
        AuthController authController = new AuthController(userService);

        // act
        boolean registerUser = authController.registerUser(userRegisterDto);

        // assert
        assertTrue(registerUser);
    }


    @Test
    void shouldReturnFalseIfUserRegisterDtoNull() {
        // arrange
        UserRegisterDto userRegisterDto = null;
        AuthController authController = new AuthController(userService);
        // act
        boolean registerUser = authController.registerUser(userRegisterDto);
        // assert
        assertFalse(registerUser);
    }


}