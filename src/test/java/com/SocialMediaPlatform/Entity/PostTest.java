//package com.SocialMediaPlatform.Entity;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PostTest {
//
//    private Instant createdAt;
//    private Post post;
//
//    @BeforeEach
//    void setUp() {
//        post = Post.builder()
//                .content("Hello World")
//                .mediaUrl("http://exampleimage.jpg")
//                .createdAt()
//                .build();
//    }
//
//
//    @Test
//    void shouldReturnValidPost(){
//        // arrange
//        // act + assert
//        assertNotNull(post);
//        assertEquals("Hello World", post.getContent());
//        assertEquals("http://exampleimage.jpg", post.getMediaUrl());
//        assertEquals(createdAt, post.getCreatedAt());
//    }
//}