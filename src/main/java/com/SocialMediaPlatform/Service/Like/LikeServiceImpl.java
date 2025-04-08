package com.SocialMediaPlatform.Service.Like;

import com.SocialMediaPlatform.Domain.Like;
import com.SocialMediaPlatform.Interface.Like.ILikeFactory;
import com.SocialMediaPlatform.Interface.Like.ILikeService;
import com.SocialMediaPlatform.Repository.ILikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements ILikeService {

    private final ILikeRepository iLikeRepository;
    private final ILikeFactory iLikeFactory;

    public LikeServiceImpl(ILikeRepository iLikeRepository, ILikeFactory iLikeFactory) {
        if(iLikeRepository == null || iLikeFactory == null) {
            throw new IllegalArgumentException("likeRepository and likeFactory is null");
        }
        this.iLikeRepository = iLikeRepository;
        this.iLikeFactory = iLikeFactory;
    }

    @Override
    public Optional<Like> registerLike(String postID, String userID) {
        if(postID == null || userID == null){
            throw new IllegalArgumentException("Parameters can't be null");
        }
        if(iLikeRepository.findByPostIDAndUserID(postID, userID).isPresent()){
            return Optional.empty();
        }
        Like like =  iLikeFactory.createLike(postID, userID);
        return Optional.of(iLikeRepository.save(like));
    }

}
