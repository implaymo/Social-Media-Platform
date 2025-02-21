package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Entity.Like;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class LikeRepositoryTest {

    @Autowired
    LikeRepository likeRepository;

    private Like like;

    @BeforeEach
    void setUp(){
        like = Like.builder()
                .postID("postID")
                .userID("userID")
                .build();
        like = likeRepository.save(like);
    }

    @AfterEach
    void tearDown() {
        likeRepository.deleteAll();
    }


    @Test
    void shouldSaveLikeInRepository(){
        // arrange
        // act
        Optional<Like> foundLike = likeRepository.findById(like.getLikeID());
        // assert

        assertTrue(foundLike.isPresent());
        assertEquals(like.getPostID(), foundLike.get().getPostID());
        assertEquals(like.getUserID(), foundLike.get().getUserID());
        assertNotNull(foundLike.get().getUpdatedAt());
        assertNotNull(foundLike.get().getCreatedAt());
    }

    @Test
    void shouldReturnEmptyIfLikeNotInRepository(){
        // arrange
        // act
        Optional<Like> savedLike = likeRepository.findById("nonSavedID");
        // assert
        assertTrue(savedLike.isEmpty());
    }



}