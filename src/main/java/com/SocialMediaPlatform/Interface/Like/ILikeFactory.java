package com.SocialMediaPlatform.Interface.Like;

import com.SocialMediaPlatform.Domain.Like;


public interface ILikeFactory {
    Like createLike(String postID, String userID);
}
