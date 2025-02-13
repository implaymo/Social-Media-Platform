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
                .password("John@12345")
                .build();

        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // assert - validate that there are no constraint violations
        assertTrue(violations.isEmpty(), "Expected no validation errors, but found: " + violations);

        // assert - validate user fields
        assertNotNull(user);
        assertEquals("John Cena", user.getName());
        assertEquals("john123@gmail.com", user.getEmail());
        assertEquals("John@12345", user.getPassword());
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
    void shouldReturnTrueIfUserProvidesNameWithSpecialCharacters() {
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
    void shouldReturnTrueIfUserProvidesNameWithMoreThanMaximumSize() {
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
                v.getMessage().equals("Name must be between 2 and 50 characters")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesNameWithLessThanMinimumSize() {
        // arrange
        User user = User.builder()
                .name("J")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name must be between 2 and 50 characters")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesInvalidEmailMissingAtSign() {
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
    void shouldReturnTrueIfUserProvidesInvalidEmailMissingTopLevelDomain() {
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

    @Test
    void shouldReturnTrueIfUserProvidesPasswordLengthLessThan8() {
        // arrange
        User user = User.builder()
                .name("John")
                .email("john123@gmail")
                .password("john123")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Password must have at least 8 characters")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesPasswordThatNotFollowAllCharacterRules() {
        // arrange
        User user = User.builder()
                .name("John")
                .email("john123@gmail")
                .password("john@1234")
                .build();
        // act
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Password must contain at least one uppercase letter," +
                        " one lowercase letter, one number, and one special character.")));
    }


}