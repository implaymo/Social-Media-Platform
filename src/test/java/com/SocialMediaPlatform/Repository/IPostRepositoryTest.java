package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Domain.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class IPostRepositoryTest {

    @Autowired
    private IPostRepository postRepository;

    private Post post;

    @BeforeEach
    void setUp() {
        post = Post.builder()
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();

        post = postRepository.save(post);
    }

    @AfterEach
    void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    void shouldReturnOptionalPresentIfFoundPost() {
        // arrange
        // act
        Optional<Post> foundPost = postRepository.findById(post.getPostId());
        // assert
        assertTrue(foundPost.isPresent());
        assertEquals(post.getContent(), foundPost.get().getContent());
        assertEquals(post.getMediaUrl(), foundPost.get().getMediaUrl());
        assertNotNull(foundPost.get().getCreatedAt());
        assertNotNull(foundPost.get().getUpdatedAt());
    }

    @Test
    void shouldReturnOptionalEmptyIfNotFoundPost() {
        // arrange
        String nonExistentPostId = "nonExistentId";
        // act
        Optional<Post> foundPost = postRepository.findById(nonExistentPostId);
        // assert
        assertTrue(foundPost.isEmpty());
    }
}
