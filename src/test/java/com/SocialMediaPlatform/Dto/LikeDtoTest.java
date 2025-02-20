package com.SocialMediaPlatform.Dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LikeDtoTest {


    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateValidLikeDto() {
        // arrange
        LikeDto likeDto = LikeDto.builder()
                .userID("userID")
                .build();
        // act
        Set<ConstraintViolation<LikeDto>> violations = validator.validate(likeDto);
        // assert
        assertTrue(violations.isEmpty());
        assertEquals("userID", likeDto.getUserID());
    }

    @Test
    void shouldNotCreateLikeDtoWithNullUserId() {
        // arrange
        LikeDto likeDto = LikeDto.builder()
                .userID(null)
                .build();
        // act
        Set<ConstraintViolation<LikeDto>> violations = validator.validate(likeDto);
        // assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldNotCreateLikeDtoWithBlankUserId() {
        // arrange
        LikeDto likeDto = LikeDto.builder()
                .userID("")
                .build();
        // act
        Set<ConstraintViolation<LikeDto>> violations = validator.validate(likeDto);
        // assert
        assertFalse(violations.isEmpty());
    }
}