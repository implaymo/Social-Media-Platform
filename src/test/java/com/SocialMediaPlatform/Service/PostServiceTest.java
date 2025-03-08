package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.PostMapper;
import com.SocialMediaPlatform.Repository.PostRepository;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @Mock
    PostMapper postMapper;

    private PostService postService;

    @BeforeEach
    void setUp(){
        postService = new PostService(postRepository, postMapper);

    }

    @Test
    void shouldReturnOptionalPresentIfCreateNewPostWithContentSuccessfully(){
        // arrange
        PostDto postDto = mock(PostDto.class);
        Post postMapped = mock(Post.class);
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);

        when(postMapper.toEntity(postDto)).thenReturn(postMapped);
        when(customUserDetails.getId()).thenReturn("userID");
        when(postRepository.save(any(Post.class))).thenReturn(postMapped);
        // act
        Optional<Post> postCreated = postService.createPost(postDto, customUserDetails);
        // assert
        assertTrue(postCreated.isPresent());
    }


    @Test
    void shouldReturnOptionalEmptyIfCreatePostDtoNull(){
        // arrange
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);
        // act
        Optional<Post> postCreated = postService.createPost(null, customUserDetails);
        // assert
        assertTrue(postCreated.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfCreateUserNull(){
        // arrange
        PostDto postDto = mock(PostDto.class);
        // act
        Optional<Post> postCreated = postService.createPost(postDto, null);
        // assert
        assertTrue(postCreated.isEmpty());
    }

    @Test
    void shouldReturnOptionalPresentIfUpdatePostSuccessfully(){
        // arrange
        PostDto postDto = mock(PostDto.class);
        Post postMapped = mock(Post.class);
        when(postMapper.toEntity(postDto)).thenReturn(postMapped);
        when(postRepository.findById(postMapped.getPostId())).thenReturn(Optional.of(postMapped));

        // act
        Optional<Post> updatePost = postService.updatePost(postDto);
        // assert
        assertTrue(updatePost.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyIfUpdatePostDtoIsNull(){
        // arrange
        // act
        Optional<Post> updatePost = postService.updatePost(null);
        // assert
        assertTrue(updatePost.isEmpty());
    }

    @Test
    void shouldReturnOptionalPresentIfDeletePostSuccessfully(){
        // arrange
        PostDto postDto = mock(PostDto.class);
        Post postMapped = mock(Post.class);
        when(postMapper.toEntity(postDto)).thenReturn(postMapped);
        when(postRepository.findById(postMapped.getPostId())).thenReturn(Optional.of(postMapped));
        // act
        Optional<Post> postDeleted = postService.deletePost(postDto);
        // assert
        assertTrue(postDeleted.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyIfDeletePostDtoIsNull(){
        // arrange
        // act
        Optional<Post> postDeleted = postService.deletePost(null);
        // assert
        assertTrue(postDeleted.isEmpty());
    }

}