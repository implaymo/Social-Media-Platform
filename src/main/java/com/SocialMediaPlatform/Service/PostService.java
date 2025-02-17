package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
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


    public boolean createPost(PostDto postDto){
        if(postDto == null) {
            return false;
        }
        Post post = postMapper.toEntity(postDto);
        postRepository.save(post);
        return true;
    }

    public boolean updatePost(PostDto postDto) {
        if (postDto == null) {
            return false;
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
            return true;
        }
        return false;
    }

    public boolean deletePost(PostDto postDto) {
        if (postDto == null) {
            return false;
        }
        Post post = postMapper.toEntity(postDto);
        Optional<Post> existingPostOptional = postRepository.findById(post.getPostId());
        if (existingPostOptional.isPresent()) {
            postRepository.delete(existingPostOptional.get());
            return true;
        }
        return false;
    }
}
