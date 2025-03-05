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
        String postID = "12345";
        String userID = "userID";
        // act
        CommentDto commentDto = new CommentDto(postID, userID, comment);
        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
        // assert
        assertNotNull(commentDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldNotCreateCommentDtoWithCommentNull(){
        // arrange
        String postID = "12345";
        String userID = "userID";
        // act
        CommentDto commentDto = new CommentDto(postID, userID, null);
        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
        // assert
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Comment can't be null or blank")));
    }

    @Test
    void shouldNotCreateCommentDtoWithCommentBlank(){
        // arrange
        String postID = "12345";
        String userID = "userID";
        CommentDto commentDto = new CommentDto(postID, userID, "");
        // act
        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
        // assert
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("Comment can't be null or blank")));
    }

    @Test
    void shouldNotCreateCommentDtoWithUserIdNull(){
        // arrange
        String comment = "This is a comment";
        String postID = "12345";
        CommentDto commentDto = new CommentDto(postID, null, comment);
        // act
        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
        // assert
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("User ID can't be null or blank")));
    }

    @Test
    void shouldNotCreateCommentDtoWithUserIdBlank(){
        // arrange
        String comment = "This is a comment";
        String postID = "12345";
        CommentDto commentDto = new CommentDto(postID, "", comment);
        // act
        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);
        // assert
        assertTrue(violations.stream().anyMatch(v ->
                v.getMessage().equals("User ID can't be null or blank")));
    }


}