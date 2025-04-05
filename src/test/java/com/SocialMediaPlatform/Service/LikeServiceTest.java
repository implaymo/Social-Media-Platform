package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.Like;
import com.SocialMediaPlatform.Interface.ILikeFactory;
import com.SocialMediaPlatform.Repository.ILikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    private ILikeRepository likeRepository;

    @Mock
    private ILikeFactory iLikeFactory;

    private String postID;
    private String userID;
    private LikeService likeService;

    @BeforeEach
    void setUp(){
        likeService = new LikeService(likeRepository, iLikeFactory);
        postID = "postID";
        userID = "userID";
    }

    @Test
    void shouldRegisterLike(){
        // arrange
        Like like = mock(Like.class);
        String postID = "postID";
        String userID = "userID";
        when(likeRepository.findByPostIDAndUserID(postID, userID)).thenReturn(Optional.empty());
        when(iLikeFactory.createLike(postID, userID)).thenReturn(like);
        when(likeRepository.save(like)).thenReturn(like);
        // act
        Optional<Like> likeRegistered = likeService.registerLike(postID, userID);
        // assert
        assertTrue(likeRegistered.isPresent());
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