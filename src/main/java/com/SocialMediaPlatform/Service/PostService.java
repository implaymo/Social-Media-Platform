package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.PostMapper;
import com.SocialMediaPlatform.Repository.PostRepository;
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


    public Optional<Post> createPost(PostDto postDto, User user){
        if(postDto == null || user == null) {
            return Optional.empty();
        }
        Post post = postMapper.toEntity(postDto);
        post.setUserId(user.getId());
        postRepository.save(post);
        return Optional.of(post);
    }

    public Optional<Post> updatePost(PostDto postDto) {
        if (postDto == null) {
            return Optional.empty();
        }
        Post post = postMapper.toEntity(postDto);
        Optional<Post> existingPostOptional = postRepository.findById(post.getPostId());
        if (existingPostOptional.isPresent()) {
            Post existingPost = existingPostOptional.get();
            if (postDto.getContent() != null) {
                existingPost.setContent(postDto.getContent());
            }
            if (postDto.getMediaUrl() != null) {
                existingPost.setMediaUrl(postDto.getMediaUrl());
            }
            postRepository.save(existingPost);
            return Optional.of(existingPost);
        }
        return Optional.empty();
    }

    public Optional<Post> deletePost(PostDto postDto) {
        if (postDto == null) {
            return Optional.empty();
        }
        Post post = postMapper.toEntity(postDto);
        Optional<Post> existingPostOptional = postRepository.findById(post.getPostId());
        if (existingPostOptional.isPresent()) {
            postRepository.delete(existingPostOptional.get());
            return existingPostOptional;
        }
        return Optional.empty();
    }
}
