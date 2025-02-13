package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserRegisterMapper userRegisterMapper;

    @BeforeEach
    void setUp() {
        userRegisterMapper = new UserRegisterMapper();
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
        UserService userService = new UserService(userRepository, userRegisterMapper);
        // act
        boolean registerUser = userService.registerUser(userRegisterDto);
        // assert
        assertTrue(registerUser);
    }

    @Test
    void shouldReturnFalseIfUserRegisterDtoIsNull() {
        // arrange
        UserRegisterDto userRegisterDto = null;
        UserService userService = new UserService(userRepository, userRegisterMapper);
        // act
        boolean registerUser = userService.registerUser(userRegisterDto);
        // assert
        assertFalse(registerUser);
    }


}