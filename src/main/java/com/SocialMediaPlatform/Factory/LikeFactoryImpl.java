package com.SocialMediaPlatform.Factory;

import com.SocialMediaPlatform.Entity.Like;
import com.SocialMediaPlatform.Interface.ILikeFactory;
import org.springframework.stereotype.Component;

@Component
public class LikeFactoryImpl implements ILikeFactory {

    @Override
    public Like createLike(String postID, String userID) {
        return Like.builder()
                .postID(postID)
                .userID(userID)
                .build();
    }

}

