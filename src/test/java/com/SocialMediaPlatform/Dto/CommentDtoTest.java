package com.SocialMediaPlatform.Dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CommentDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateValidCommentDto(){
        // arrange
        String comment = "This is a comment";
        // act
        CommentDto commentDto = new CommentDto(comment);
        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
        // assert
        assertNotNull(commentDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldNotCreateCommentDtoWithCommentNull(){
        // arrange
        // act
        CommentDto commentDto = new CommentDto(null);
        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
        // assert
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Comment can't be null or blank")));
    }

    @Test
    void shouldNotCreateCommentDtoWithCommentBlank(){
        // arrange
        CommentDto commentDto = new CommentDto("");
        // act
        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
        // assert
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Comment can't be null or blank")));
    }
}