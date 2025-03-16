package com.SocialMediaPlatform.Interface;

import com.SocialMediaPlatform.Entity.Like;


public interface ILikeFactory {
    Like createLike(String postID, String userID);
}
