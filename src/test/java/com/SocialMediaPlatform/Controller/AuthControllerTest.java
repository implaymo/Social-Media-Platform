//package com.SocialMediaPlatform.Controller;
//
//import com.SocialMediaPlatform.Dto.UserRegisterDto;
//import com.SocialMediaPlatform.Entity.User;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AuthControllerTest {
//
//    @Test
//    void shouldReturnTrueIfRegisterUser() {
//        // arrange
//        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
//                .name("John Cena")
//                .email("john123@gmail.com")
//                .password("John@12345")
//                .build();
//        AuthController authController = new AuthController();
//        // act
//        boolean registerUser = authController.registerUser(userRegisterDto);
//        // assert
//        assertTrue(registerUser);
//    }
//
//    @Test
//    void shouldReturnFalseIfNameWrongAndNotRegisterUser() {
//        // arrange
//        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
//                .name("J")
//                .email("john123@gmail.com")
//                .password("John@12345")
//                .build();
//        AuthController authController = new AuthController();
//        // act
//        boolean registerUser = authController.registerUser(userRegisterDto);
//        // assert
//        assertFalse(registerUser);
//    }
//
//    @Test
//    void shouldReturnFalseIfEmailWrongAndNotRegisterUser() {
//        // arrange
//        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
//                .name("John Cena")
//                .email("john123gmail.com")
//                .password("John@12345")
//                .build();
//        AuthController authController = new AuthController();
//        // act
//        boolean registerUser = authController.registerUser(userRegisterDto);
//        // assert
//        assertFalse(registerUser);
//    }
//
//    @Test
//    void shouldReturnFalseIfPasswordWrongAndNotRegisterUser() {
//        // arrange
//        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
//                .name("John Cena")
//                .email("john123@gmail.com")
//                .password("john@12345")
//                .build();
//        AuthController authController = new AuthController();
//        // act
//        boolean registerUser = authController.registerUser(userRegisterDto);
//        // assert
//        assertFalse(registerUser);
//    }
//
//
//}