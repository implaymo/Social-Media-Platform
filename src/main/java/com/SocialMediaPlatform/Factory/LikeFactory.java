package com.SocialMediaPlatform.Factory;

import com.SocialMediaPlatform.Entity.Like;
import com.SocialMediaPlatform.Repository.LikeRepository;

import java.util.Optional;

public class LikeFactory {

    private final LikeRepository likeRepository;

    public LikeFactory(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Optional<Like> createLike(String postID, String userID) {
        if(postID == null || userID == null){
            throw new IllegalArgumentException("Parameters can't be null");
        }
        Like like = Like.builder()
                .postID(postID)
                .userID(userID)
                .build();
        return Optional.of(likeRepository.save(like));
    }
}
