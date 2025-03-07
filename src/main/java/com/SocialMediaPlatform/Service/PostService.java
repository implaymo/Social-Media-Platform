package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.PostMapper;
import com.SocialMediaPlatform.Repository.PostRepository;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    public Optional<Post> createPost(PostDto postDto, CustomUserDetails customUserDetails) {
        if(postDto == null || customUserDetails == null) {
            return Optional.empty();
        }
        Post post = postMapper.toEntity(postDto);
        post.setUserId(customUserDetails.getId());
        postRepository.save(post);
        return Optional.of(post);
    }

    public Optional<Post> updatePost(PostDto postDto) {
        if (postDto == null) {
            return Optional.empty();
        }
        Post mappedPost = postMapper.toEntity(postDto);
        Optional<Post> databasePostOptional = postRepository.findById(mappedPost.getPostId());
        if (databasePostOptional.isPresent()) {
            Post databasePost = databasePostOptional.get();
            if (postDto.getContent() != null) {
                databasePost.setContent(postDto.getContent());
            }
            if (postDto.getMediaUrl() != null) {
                databasePost.setMediaUrl(postDto.getMediaUrl());
            }
            postRepository.save(databasePost);
            return Optional.of(databasePost);
        }
        return Optional.empty();
    }

    public Optional<Post> deletePost(PostDto postDto) {
        if (postDto == null) {
            return Optional.empty();
        }
        Post mappedPost = postMapper.toEntity(postDto);
        Optional<Post> databasePostOptional = postRepository.findById(mappedPost.getPostId());
        if (databasePostOptional.isPresent()) {
            postRepository.delete(databasePostOptional.get());
            return databasePostOptional;
        }
        return Optional.empty();
    }
}
