package com.SocialMediaPlatform.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    private LocalDateTime specificDateTime;

    @BeforeEach
    void setUp() {
        specificDateTime = LocalDateTime.of(2025, 2, 17, 9, 36, 0);
        Post post = Post.builder()
                .content("Hello World")
                .mediaUrl("http://exampleimage.jpg")
                .timestamp(specificDateTime)
                .build();
    }


    @Test
    void shouldReturnValidPost(){
        // arrange
        // act
        // assert
    }
}