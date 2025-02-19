package com.SocialMediaPlatform.Validator;

import com.SocialMediaPlatform.Dto.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentOrMediaUrlValidatorTest {

    private ContentOrMediaUrlValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ContentOrMediaUrlValidator();
    }

    @Test
    void shouldReturnTrueIfPostDtoHasContentAndMedia() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("New content")
                .mediaUrl("example.jpg")
                .build();
        // act
        boolean valid = validator.isValid(postDto, null);
        // assert
        assertTrue(valid);
    }

    @Test
    void shouldReturnTrueIfPostDtoHasOnlyContent() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("New content")
                .build();
        // act
        boolean valid = validator.isValid(postDto, null);
        // assert
        assertTrue(valid);
    }

    @Test
    void shouldReturnTrueIfPostDtoHasOnlyMedia() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .mediaUrl("example.jpg")
                .build();
        // act
        boolean valid = validator.isValid(postDto, null);
        // assert
        assertTrue(valid);
    }

    @Test
    void shouldReturnFalseIfPostDtoHasNoContentAndMedia() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .build();
        // act
        boolean valid = validator.isValid(postDto, null);
        // assert
        assertFalse(valid);
    }


}