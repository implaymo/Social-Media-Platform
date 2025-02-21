package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.Like;
import com.SocialMediaPlatform.Repository.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    private String postID;
    private String userID;
    private LikeService likeService;

    @BeforeEach
    void setUp(){
        likeService = new LikeService(likeRepository);
        postID = "postID";
        userID = "userID";
    }

    @Test
    void shouldRegisterLike(){
        // arrange
        Like like = Like.builder()
                .postID(postID)
                .userID(userID)
                .build();
        when(likeRepository.save(any(Like.class))).thenReturn(like);
        // act
        Optional<Like> likeRegistered = likeService.registerLike(postID, userID);
        // assert
        assertTrue(likeRegistered.isPresent());
        assertEquals(postID, likeRegistered.get().getPostID());
        assertEquals(userID, likeRegistered.get().getUserID());
    }

    @Test
    void shouldNotRegisterLikeIfPostIDNull() {
        // arrange
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> likeService.registerLike(null, userID));
    }

    @Test
    void shouldNotRegisterLikeIfUserIDNull() {
        // arrange
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> likeService.registerLike(postID, null));
    }

    @Test
    void shouldNotRegisterLikeIfUserIDNullAndPostIDNull() {
        // arrange
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> likeService.registerLike(null, null));
    }
}