package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Repository.IPostRepository;
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
    IPostRepository postRepository;


    private PostService postService;

    @BeforeEach
    void setUp(){
        postService = new PostService(postRepository);

    }

    @Test
    void shouldReturnOptionalPresentIfCreateNewPostWithContentSuccessfully(){
        // arrange
        Post post = mock(Post.class);
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);

        when(customUserDetails.getId()).thenReturn("userID");
        when(postRepository.save(any(Post.class))).thenReturn(post);
        // act
        Optional<Post> postCreated = postService.createPost(post, customUserDetails);
        // assert
        assertTrue(postCreated.isPresent());
    }


    @Test
    void shouldReturnOptionalEmptyIfPostNull(){
        // arrange
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);
        // act
        Optional<Post> postCreated = postService.createPost(null, customUserDetails);
        // assert
        assertTrue(postCreated.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfUserNull(){
        // arrange
        Post post = mock(Post.class);
        // act
        Optional<Post> postCreated = postService.createPost(post, null);
        // assert
        assertTrue(postCreated.isEmpty());
    }

    @Test
    void shouldReturnOptionalPresentIfUpdatePostSuccessfully(){
        // arrange
        Post post = mock(Post.class);
        when(postRepository.findById(post.getPostId())).thenReturn(Optional.of(post));

        // act
        Optional<Post> updatePost = postService.updatePost(post);
        // assert
        assertTrue(updatePost.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyIfPostIsNull(){
        // arrange
        // act
        Optional<Post> updatePost = postService.updatePost(null);
        // assert
        assertTrue(updatePost.isEmpty());
    }

    @Test
    void shouldReturnOptionalPresentIfDeletePostSuccessfully(){
        // arrange
        Post post = mock(Post.class);
        when(postRepository.findById(post.getPostId())).thenReturn(Optional.of(post));
        // act
        Optional<Post> postDeleted = postService.deletePost(post);
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