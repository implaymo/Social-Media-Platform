package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Mapper.PostMapper;
import com.SocialMediaPlatform.Repository.PostRepository;
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
    private PostRepository postRepository;

    private PostMapper postMapper;
    private PostService postService;
    private PostDto postDto;
    private Post post;

    @BeforeEach
    void setUp(){
        postMapper = new PostMapper();
        postService = new PostService(postRepository, postMapper);
        post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
    }

    @Test
    void shouldReturnTrueIfCreateNewPostWithContentSuccessfully(){
        // arrange
        postDto = PostDto.builder()
                .content("Hello World")
                .build();
        // act
        boolean postCreated = postService.createPost(postDto);
        // assert
        assertTrue(postCreated);
    }

    @Test
    void shouldReturnTrueIfCreateNewPostWithContentAndMediaSuccessfully(){
        // arrange
        postDto = PostDto.builder()
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        // act
        boolean postCreated = postService.createPost(postDto);
        // assert
        assertTrue(postCreated);
    }

    @Test
    void shouldReturnFalseIfCreatePostDtoNull(){
        // arrange
        postDto = PostDto.builder()
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        // act
        boolean postCreated = postService.createPost(null);
        // assert
        assertFalse(postCreated);
    }

    @Test
    void shouldReturnTrueIfUpdatePostSuccessfully(){
        // arrange
        postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World IS LIVE")
                .mediaUrl("exampleOfChange.jpg")
                .build();
        when(postRepository.findById(post.getPostId())).thenReturn(Optional.of(post));
        // act
        boolean postEdit = postService.updatePost(postDto);
        // assert
        assertTrue(postEdit);
    }

    @Test
    void shouldReturnFalseIfUpdatePostDtoIsNull(){
        // arrange
        // act
        boolean postEdit = postService.updatePost(null);
        // assert
        assertFalse(postEdit);
    }

    @Test
    void shouldReturnTrueIfDeletePostSuccessfully(){
        // arrange
        postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postRepository.findById(post.getPostId())).thenReturn(Optional.of(post));
        // act
        boolean postDeleted = postService.deletePost(postDto);
        // assert
        assertTrue(postDeleted);
    }

    @Test
    void shouldReturnFalseIfDeletePostDtoIsNull(){
        // arrange
        // act
        boolean postDeleted = postService.deletePost(null);
        // assert
        assertFalse(postDeleted);
    }

    @Test
    void shouldReturnFalseIfPostDoesNotExist() {
        // arrange
        PostDto nonExistentPostDto = PostDto.builder()
                .postId("nonExistentId")
                .content("This post doesn't exist")
                .build();
        when(postRepository.findById("nonExistentId")).thenReturn(Optional.empty());

        // act
        boolean postDeleted = postService.deletePost(nonExistentPostDto);

        // assert
        assertFalse(postDeleted);
        verify(postRepository, never()).delete(any(Post.class));
    }

}