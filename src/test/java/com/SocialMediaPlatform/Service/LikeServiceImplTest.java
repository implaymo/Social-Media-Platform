package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Domain.Like;
import com.SocialMediaPlatform.Interface.Like.ILikeFactory;
import com.SocialMediaPlatform.Repository.ILikeRepository;
import com.SocialMediaPlatform.Service.Like.LikeServiceImpl;
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
class LikeServiceImplTest {

    @Mock
    private ILikeRepository likeRepository;

    @Mock
    private ILikeFactory iLikeFactory;

    private String postID;
    private String userID;
    private LikeServiceImpl likeServiceImpl;

    @BeforeEach
    void setUp(){
        likeServiceImpl = new LikeServiceImpl(likeRepository, iLikeFactory);
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
        Optional<Like> likeRegistered = likeServiceImpl.registerLike(postID, userID);
        // assert
        assertTrue(likeRegistered.isPresent());
    }

    @Test
    void shouldNotRegisterLikeIfPostIDNull() {
        // arrange
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> likeServiceImpl.registerLike(null, userID));
    }

    @Test
    void shouldNotRegisterLikeIfUserIDNull() {
        // arrange
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> likeServiceImpl.registerLike(postID, null));
    }

    @Test
    void shouldNotRegisterLikeIfUserIDNullAndPostIDNull() {
        // arrange
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> likeServiceImpl.registerLike(null, null));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfLikeRepositoryIsNull(){
        // arrange
        ILikeFactory iLikeFactory = mock(ILikeFactory.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new LikeServiceImpl(null, iLikeFactory));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfLikeFactoryIsNull(){
        // arrange
        ILikeRepository iLikeRepository = mock(ILikeRepository.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new LikeServiceImpl(iLikeRepository, null));
    }
}