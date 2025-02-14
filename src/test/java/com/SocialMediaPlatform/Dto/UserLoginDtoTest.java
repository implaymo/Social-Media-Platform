package com.SocialMediaPlatform.Dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldReturnUserObjectAndFields() {
        // arrange
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        // act
        Set<ConstraintViolation<UserLoginDto>> violations = validator.validate(userLoginDto);

        // assert - validate that there are no constraint violations
        assertTrue(violations.isEmpty(), "Expected no validation errors, but found: " + violations);

        // assert - validate user fields
        assertNotNull(userLoginDto);
        assertEquals("john123@gmail.com", userLoginDto.getEmail());
        assertEquals("John@12345", userLoginDto.getPassword());
    }

    @Test
    void shouldThrowExceptionIfEmailIsNull() {
        // arrange
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email(null)
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserLoginDto>> violations = validator.validate(userLoginDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Email can't be null or blank")));
    }

    @Test
    void shouldThrowExceptionIfPasswordIsNull() {
        // arrange
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("john123@gmail.com")
                .password(null)
                .build();
        // act
        Set<ConstraintViolation<UserLoginDto>> violations = validator.validate(userLoginDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Password can't be null or blank")));
    }

    @Test
    void shouldReturnConstraintViolationIfEmailIsBlank() {
        // arrange
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserLoginDto>> violations = validator.validate(userLoginDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Email can't be null or blank")));
    }

    @Test
    void shouldReturnConstraintViolationIfPasswordIsBlank() {
        // arrange
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("john123@gmail.com")
                .password("")
                .build();
        // act
        Set<ConstraintViolation<UserLoginDto>> violations = validator.validate(userLoginDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Password can't be null or blank")));
    }



}