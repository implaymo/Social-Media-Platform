package com.SocialMediaPlatform.Interface.Post;

import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;

import java.util.Optional;

public interface IPostService {
    Optional<Post> createPost(Post post, CustomUserDetails customUserDetails);
    Optional<Post> updatePost(Post post);
    Optional<Post> deletePost(Post post);
}
