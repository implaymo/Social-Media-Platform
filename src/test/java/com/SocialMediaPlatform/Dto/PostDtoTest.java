package com.SocialMediaPlatform.Dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostDtoTest {

    @Test
    void shouldCreateValidPostDto(){
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
    void shouldNotCreatePostDtoIfMissingContent(){
        // arrange
        PostDto postDto = null;
        // act
        // assert
        assertNull(postDto);
    }

    @Test
    void shouldCreatePostDtoIfHaveContentAndNotHaveMediaUrl(){
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello World")
                .build();
        // act
        // assert
        assertNotNull(postDto);
        assertEquals("Hello World", postDto.getContent());
        assertEquals(null, postDto.getMediaUrl());
    }
}