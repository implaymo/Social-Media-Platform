package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Entity.User;
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
        User user = mock(User.class);

        when(postMapper.toEntity(postDto)).thenReturn(postMapped);
        when(user.getId()).thenReturn("userID");
        doNothing().when(postMapped).setUserId(anyString());
        when(postRepository.save(any(Post.class))).thenReturn(postMapped);
        // act
        Optional<Post> postCreated = postService.createPost(postDto, user);
        // assert
        assertTrue(postCreated.isPresent());
        verify(postMapped).setUserId(user.getId());
        assertEquals(postMapped, postCreated.get());
    }

//    @Test
//    void shouldReturnOptionalPresentIfCreateNewPostWithContentAndMediaSuccessfully(){
//        // arrange
//        postDto = PostDto.builder()
//                .content("Hello World")
//                .mediaUrl("example.jpg")
//                .build();
//        when(postRepository.save(any(Post.class))).thenReturn(post);
//        // act
//        Optional<Post> postCreated = postService.createPost(postDto, user);
//        // assert
//        assertTrue(postCreated.isPresent());
//    }
//
//    @Test
//    void shouldReturnOptionalEmptyIfCreatePostDtoNull(){
//        // arrange
//        // act
//        Optional<Post> postCreated = postService.createPost(null, user);
//        // assert
//        assertTrue(postCreated.isEmpty());
//    }
//
//    @Test
//    void shouldReturnOptionalEmptyIfCreateUserNull(){
//        // arrange
//        postDto = PostDto.builder()
//                .content("Hello World")
//                .mediaUrl("example.jpg")
//                .build();
//        // act
//        Optional<Post> postCreated = postService.createPost(postDto, null);
//        // assert
//        assertTrue(postCreated.isEmpty());
//    }
//
//    @Test
//    void shouldReturnOptionalPresentIfUpdatePostSuccessfully(){
//        // arrange
//        postDto = PostDto.builder()
//                .postId("postID")
//                .content("Hello World IS LIVE")
//                .mediaUrl("exampleOfChange.jpg")
//                .build();
//        when(postRepository.findById(post.getPostId())).thenReturn(Optional.of(post));
//        // act
//        Optional<Post> postEdit = postService.updatePost(postDto);
//        // assert
//        assertTrue(postEdit.isPresent());
//    }
//
//    @Test
//    void shouldReturnOptionalEmptyIfUpdatePostDtoIsNull(){
//        // arrange
//        // act
//        Optional<Post> postEdit = postService.updatePost(null);
//        // assert
//        assertTrue(postEdit.isEmpty());
//    }
//
//    @Test
//    void shouldReturnOptionalPresentIfDeletePostSuccessfully(){
//        // arrange
//        postDto = PostDto.builder()
//                .postId("postID")
//                .content("Hello World")
//                .mediaUrl("example.jpg")
//                .build();
//        when(postRepository.findById(post.getPostId())).thenReturn(Optional.of(post));
//        // act
//        Optional<Post> postDeleted = postService.deletePost(postDto);
//        // assert
//        assertTrue(postDeleted.isPresent());
//    }
//
//    @Test
//    void shouldReturnOptionalEmptyIfDeletePostDtoIsNull(){
//        // arrange
//        // act
//        Optional<Post> postDeleted = postService.deletePost(null);
//        // assert
//        assertTrue(postDeleted.isEmpty());
//    }
//
//    @Test
//    void shouldReturnOptionalEmptyIfPostDoesNotExist() {
//        // arrange
//        PostDto nonExistentPostDto = PostDto.builder()
//                .postId("nonExistentId")
//                .content("This post doesn't exist")
//                .build();
//        when(postRepository.findById("nonExistentId")).thenReturn(Optional.empty());
//
//        // act
//        Optional<Post> postDeleted = postService.deletePost(nonExistentPostDto);
//
//        // assert
//        assertTrue(postDeleted.isEmpty());
//        verify(postRepository, never()).delete(any(Post.class));
//    }

}