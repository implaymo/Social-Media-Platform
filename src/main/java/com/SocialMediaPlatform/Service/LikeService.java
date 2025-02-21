package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.Like;
import com.SocialMediaPlatform.Repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Optional<Like> registerLike(String postID, String userID) {
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
