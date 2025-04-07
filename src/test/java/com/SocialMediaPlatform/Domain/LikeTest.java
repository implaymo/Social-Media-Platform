package com.SocialMediaPlatform.Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LikeTest {


    @Test
    void shouldCreateValidLike(){
        // arrange
        Like like = Like.builder()
                .postID("postID1")
                .userID("userID1")
                .build();
        // act & assert
        assertNotNull(like);
    }

    @Test
    void shouldBuildLikeWithNullUserId(){
        // arrange
        Like like = Like.builder()
                .postID("postID1")
                .userID(null)
                .build();
        // act & assert
        assertNull(like.getUserID());
    }

    @Test
    void shouldBuildLikeWithNullPostId(){
        // arrange
        Like like = Like.builder()
                .postID(null)
                .userID("userID1")
                .build();
        // act & assert
        assertNull(like.getPostID());
    }

    @Test
    void shouldBuildLikeIfPostIdAndUserIdNull(){
        // arrange
        Like like = Like.builder()
                .postID(null)
                .userID(null)
                .build();
        // act & assert
        assertNull(like.getPostID());
        assertNull(like.getUserID());
    }

}