package com.SocialMediaPlatform.Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void shouldCreateValidPost(){
        // arrange
        Post post = Post.builder()
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        // act + assert
        assertNotNull(post);
        assertEquals("Hello World", post.getContent());
        assertEquals("example.jpg", post.getMediaUrl());
    }

    @Test
    void shouldCreateValidPostIfMediaUrlNull(){
        // arrange
        Post post = Post.builder()
                .content("Hello World")
                .build();
        // act + assert
        assertNotNull(post);
        assertEquals("Hello World", post.getContent());
        assertNull(post.getMediaUrl());
    }


}