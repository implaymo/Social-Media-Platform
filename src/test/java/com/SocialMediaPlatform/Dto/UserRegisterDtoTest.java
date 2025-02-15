package com.SocialMediaPlatform.Dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldReturnUserObjectAndFields() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);

        // assert - validate that there are no constraint violations
        assertTrue(violations.isEmpty(), "Expected no validation errors, but found: " + violations);

        // assert - validate user fields
        assertNotNull(userRegisterDto);
        assertEquals("John Cena", userRegisterDto.getName());
        assertEquals("john123@gmail.com", userRegisterDto.getEmail());
        assertEquals("John@12345", userRegisterDto.getPassword());
    }

    @Test
    void shouldReturnNullIfUserProvideNullNameField() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name(null)
                .email("john123@gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name can't be null or blank")));
    }

    @Test
    void shouldThrowExceptionIfEmailIsNull() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John")
                .email(null)
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Email can't be null or blank")));
    }

    @Test
    void shouldThrowExceptionIfPasswordIsNull() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John")
                .email("john123@gmail.com")
                .password(null)
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Your password must be at least 8 characters long and include a mix of uppercase," +
                        " lowercase, numbers, and special characters.")));
    }

    @Test
    void shouldReturnConstraintViolationIfNameIsBlank() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("")
                .email("john123@gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name can't be null or blank")));
    }

    @Test
    void shouldReturnConstraintViolationIfEmailIsBlank() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John")
                .email("")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Email can't be null or blank")));
    }

    @Test
    void shouldReturnConstraintViolationIfPasswordIsBlank() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John")
                .email("john123@gmail")
                .password("")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Your password must be at least 8 characters long and include a mix of uppercase, " +
                        "lowercase, numbers, and special characters.")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesNameWithSpecialCharacters() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John1010010!**")
                .email("john123@gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name can only contain letters and spaces")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesNameWithMoreThanMaximumSize() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("Johnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn")
                .email("john123@gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name must be between 2 and 50 characters")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesNameWithLessThanMinimumSize() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("J")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Name must be between 2 and 50 characters")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesInvalidEmailMissingAtSign() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John")
                .email("john123gmail.com")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Invalid email format")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesInvalidEmailMissingTopLevelDomain() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John")
                .email("john123@gmail")
                .password("john12345")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Invalid email format")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesPasswordLengthLessThan8() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John")
                .email("john123@gmail")
                .password("john123")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Your password must be at least 8 characters long and include a mix of uppercase, " +
                        "lowercase, numbers, and special characters.")));
    }

    @Test
    void shouldReturnTrueIfUserProvidesPasswordThatNotFollowAllCharacterRules() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John")
                .email("john123@gmail")
                .password("john@1234")
                .build();
        // act
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(userRegisterDto);
        // assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Your password must be at least 8 characters long and include a mix of uppercase, " +
                        "lowercase, numbers, and special characters.")));
    }
}