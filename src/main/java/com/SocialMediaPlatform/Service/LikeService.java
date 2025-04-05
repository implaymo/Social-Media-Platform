package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.Like;
import com.SocialMediaPlatform.Interface.ILikeFactory;
import com.SocialMediaPlatform.Repository.ILikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private final ILikeRepository likeRepository;
    private final ILikeFactory iLikeFactory;

    public LikeService(ILikeRepository likeRepository, ILikeFactory iLikeFactory) {
        this.likeRepository = likeRepository;
        this.iLikeFactory = iLikeFactory;
    }

    public Optional<Like> registerLike(String postID, String userID) {
        if(postID == null || userID == null){
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if(likeRepository.findByPostIDAndUserID(postID, userID).isPresent()){
            return Optional.empty();
        }
        Like like =  iLikeFactory.createLike(postID, userID);
        return Optional.of(likeRepository.save(like));
    }

}
