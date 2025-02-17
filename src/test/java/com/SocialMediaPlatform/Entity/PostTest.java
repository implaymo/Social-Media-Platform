package com.SocialMediaPlatform.Entity;

import com.SocialMediaPlatform.Repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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