package com.SocialMediaPlatform.Service.Post;

import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Repository.IPostRepository;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements com.SocialMediaPlatform.Interface.Post.IPostService {

    private final IPostRepository postRepository;

    public PostServiceImpl(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> createPost(Post post, CustomUserDetails customUserDetails) {
        if(post == null || customUserDetails == null) {
            return Optional.empty();
        }
        post.setUserId(customUserDetails.getId());
        postRepository.save(post);
        return Optional.of(post);
    }

    @Override
    public Optional<Post> updatePost(Post post) {
        if (post == null) {
            return Optional.empty();
        }
        Optional<Post> databasePostOptional = postRepository.findById(post.getPostId());
        if (databasePostOptional.isPresent()) {
            Post databasePost = databasePostOptional.get();
            if (post.getContent() != null) {
                databasePost.setContent(post.getContent());
            }
            if (post.getMediaUrl() != null) {
                databasePost.setMediaUrl(post.getMediaUrl());
            }
            postRepository.save(databasePost);
            return Optional.of(databasePost);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Post> deletePost(Post post) {
        if (post == null) {
            return Optional.empty();
        }
        Optional<Post> databasePostOptional = postRepository.findById(post.getPostId());
        if (databasePostOptional.isPresent()) {
            postRepository.delete(databasePostOptional.get());
            return databasePostOptional;
        }
        return Optional.empty();
    }
}
