package com.SocialMediaPlatform.Dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PostDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateValidPostDtoIfContentAndMediaProvided(){
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello World")
                .mediaUrl("http://exampleimage.jpg")
                .build();
        // act
        // assert
        assertNotNull(postDto);
        assertEquals("Hello World", postDto.getContent());
        assertEquals("http://exampleimage.jpg", postDto.getMediaUrl());
    }

    @Test
    void shouldCreatePostDtoIfOnlyMediaProvided() {
        // arrange
        PostDto postDto = PostDto.builder()
                .mediaUrl("http://exampleimage.jpg")
                .build();
        // act
        Set<ConstraintViolation<PostDto>> violations = validator.validate(postDto);
        // assert
        assertTrue(violations.isEmpty());
        assertNotNull(postDto);
        assertEquals("http://exampleimage.jpg", postDto.getMediaUrl());
        assertNull(postDto.getContent());
    }

    @Test
    void shouldCreatePostDtoIfOnlyContentProvided() {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello World")
                .build();
        // act
        Set<ConstraintViolation<PostDto>> violations = validator.validate(postDto);
        // assert
        assertTrue(violations.isEmpty());
        assertNotNull(postDto);
        assertEquals("Hello World", postDto.getContent());
        assertNull(postDto.getMediaUrl());
    }

    @Test
    void shouldNotCreatePostDtoIfNoContentOrMediaProvided() {
        // arrange
        PostDto postDto = PostDto.builder()
                .build();
        // act
        Set<ConstraintViolation<PostDto>> violations = validator.validate(postDto);
        // assert
        assertFalse(violations.isEmpty());
    }
}