package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;
import com.SocialMediaPlatform.PasswordEncryption.PasswordSalt;
import com.SocialMediaPlatform.Repository.UserRepository;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.SocialMediaPlatform.Service.UserRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    private UserRegistrationService userRegistrationService;
    private UserRegisterMapper userRegisterMapper;
    private PasswordHash passwordHash;
    private PasswordSalt passwordSalt;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRegisterMapper = new UserRegisterMapper();
        passwordHash = new PasswordHash();
        passwordSalt = new PasswordSalt();
        userRegistrationService = new UserRegistrationService(userRepository, userRegisterMapper,passwordHash, passwordSalt);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrueIfValidUserRegistration() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        // Create the User entity (without id)
        User user = userRegisterMapper.toEntityForRegistration(userRegisterDto);

        // Mock the repository's save method to return the user with a generated id
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User savedUser = invocation.getArgument(0);
                    savedUser.setId(UUID.randomUUID().toString());
                    return savedUser;
                });

        // Mock the UserService and inject the mocked repository
        UserRegistrationService userRegistrationService = new UserRegistrationService(userRepository, userRegisterMapper, passwordHash, passwordSalt);
        AuthController authController = new AuthController(userRegistrationService);

        // act
        boolean registerUser = authController.registerUser(userRegisterDto);

        // assert
        assertTrue(registerUser);
    }


    @Test
    void shouldReturnFalseIfUserRegisterDtoNull() {
        // arrange
        UserRegisterDto userRegisterDto = null;
        AuthController authController = new AuthController(userRegistrationService);
        // act
        boolean registerUser = authController.registerUser(userRegisterDto);
        // assert
        assertFalse(registerUser);
    }

//    @Test
//    void shouldReturnTrueIfUserLoginSuccessfully(){
//        // arrange
//        UserLoginDto userLoginDto = UserLoginDto.builder()
//                .email("john123@gmail.com")
//                .password("John@12345")
//                .build();
//        // act
//        String userLoginToken = authController.loginUser(userService);
//        // assert
//
//    }


}