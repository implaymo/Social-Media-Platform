package com.SocialMediaPlatform.Interface.Like;

import com.SocialMediaPlatform.Domain.Like;

import java.util.Optional;

public interface ILikeService {
    Optional<Like> registerLike(String postID, String userID);
}
