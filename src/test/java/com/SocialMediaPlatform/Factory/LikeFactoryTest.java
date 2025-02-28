package com.SocialMediaPlatform.Factory;

import com.SocialMediaPlatform.Entity.Like;
import com.SocialMediaPlatform.Repository.LikeRepository;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LikeFactoryTest {

    @Test
    void shouldCreateLike() {
        // arrange
        LikeRepository likeRepository = mock(LikeRepository.class);
        LikeFactory likeFactory = new LikeFactory(likeRepository);
        String postID = "postId";
        String userId = "userId";
        Like likeToReturn = Like.builder().postID(postID).userID(userId).build();
        when(likeRepository.save(any(Like.class))).thenReturn(likeToReturn);
        // act
        Optional<Like> like = likeFactory.createLike(postID, userId);
        // assert
        assertTrue(like.isPresent());
    }


}