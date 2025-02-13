package com.SocialMediaPlatform.Entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldReturnUserObjectAndFields() {
        // arrange
        User user = User.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("john12345")
                .build();
        // act + assert
        assertNotNull(user);
        assertEquals("John Cena", user.getName());
        assertEquals("john123@gmail.com", user.getEmail());
        assertEquals("john12345", user.getPassword());
    }

    @Test
    void shouldReturnNullIfUserProvideNullNameField() {
        // act + assert
        assertThrows(NullPointerException.class, () -> {
            User.builder()
                    .name(null)
                    .email("john123@gmail.com")
                    .password("john12345")
                    .build();
        });
    }

    @Test
    void shouldThrowExceptionIfEmailIsNull() {
        assertThrows(NullPointerException.class, () -> {
            User.builder()
                    .name("John")
                    .email(null)
                    .password("john12345")
                    .build();
        });
    }

    @Test
    void shouldThrowExceptionIfPasswordIsNull() {
        assertThrows(NullPointerException.class, () -> {
            User.builder()
                    .name("John")
                    .email("john123@gmail.com")
                    .password(null)
                    .build();
        });
    }

    @Test
    void shouldReturnConstraintViolationIfNameIsBlank() {
        // arrange
        User user = User.builder()
                .name("")
                .email("john123@gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name can't be null or blank")));
    }

    @Test
    void shouldReturnConstraintViolationIfEmailIsBlank() {
        // arrange
        User user = User.builder()
                .name("John")
                .email("")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Email can't be null or blank")));
    }

    @Test
    void shouldReturnConstraintViolationIfPasswordIsBlank() {
        // arrange
        User user = User.builder()
                .name("John")
                .email("john123@gmail.com")
                .password("")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Password can't be null or blank")));
    }

    @Test
    void shouldReturnFalseIfUserProvidesNameWithSpecialCharacters() {
        // arrange
        User user = User.builder()
                .name("John1010010!**")
                .email("john123@gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name can only contain letters and spaces")));
    }

    @Test
    void shouldReturnFalseIfUserProvidesNameWithMoreThanMaximumSize() {
        // arrange
        User user = User.builder()
                .name("Johnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn")
                .email("john123@gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name must be between 3 and 50 characters")));
    }

    @Test
    void shouldReturnFalseIfUserProvidesNameWithLessThanMinimumSize() {
        // arrange
        User user = User.builder()
                .name("Jo")
                .email("john123@gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name must be between 3 and 50 characters")));
    }

    @Test
    void shouldReturnFalseIfUserProvidesInvalidEmailMissingAtSign() {
        // arrange
        User user = User.builder()
                .name("John")
                .email("john123gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Invalid email format")));
    }

    @Test
    void shouldReturnFalseIfUserProvidesInvalidEmailMissingTopLevelDomain() {
        // arrange
        User user = User.builder()
                .name("John")
                .email("john123@gmail")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Invalid email format")));
    }


}