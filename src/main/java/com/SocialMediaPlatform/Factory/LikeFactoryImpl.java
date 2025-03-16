package com.SocialMediaPlatform.Factory;

import com.SocialMediaPlatform.Entity.Like;
import com.SocialMediaPlatform.Interface.ILikeFactory;
import org.springframework.stereotype.Component;

@Component
public class LikeFactoryImpl implements ILikeFactory {

    @Override
    public Like createLike(String postID, String userID) {
        if(postID == null || userID == null){
            throw new IllegalArgumentException("Parameters can't be null");
        }
        return Like.builder()
                .postID(postID)
                .userID(userID)
                .build();
    }

}

