package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Exception.GlobalExceptionHandler;
import com.SocialMediaPlatform.Service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(postController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }


    // createPost method
    @Test
    void shouldReturnOkIfPostCreatedWithBothContentAndMedia() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello world")
                .mediaUrl("example.mp4")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postService.createPost(any(PostDto.class)))
                .thenReturn(Optional.of(post));
        // act + assert
        mockMvc.perform(post("/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isOk())  // Check status code
                .andExpect(content().string("true"));

    }

    @Test
    void shouldReturnOkIfPostCreatedWithContent() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello world")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .build();
        when(postService.createPost(any(PostDto.class)))
                .thenReturn(Optional.of(post));
        // act + assert
        mockMvc.perform(post("/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isOk())  // Check status code
                .andExpect(content().string("true"));
    }

    @Test
    void shouldReturnOkIfPostCreatedOnlyWithMedia() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .mediaUrl("example.mp4")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .mediaUrl("example.mp4")
                .build();
        when(postService.createPost(any(PostDto.class)))
                .thenReturn(Optional.of(post));
        // act + assert
        mockMvc.perform(post("/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    @Test
    void shouldReturnFalseIfDtoIsNull() throws Exception {
        // arrange
        PostDto postDto = null;
        // act + assert
        mockMvc.perform(post("/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnInternalServerErrorForUnhandledException() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello world")
                .mediaUrl("example.mp4")
                .build();
        when(postService.createPost(any(PostDto.class)))
                .thenThrow(new RuntimeException("Unexpected Error"));
        // act + assert
        mockMvc.perform(post("/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnBadRequestIfPostAlreadyExistsInDatabase() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello world")
                .mediaUrl("example.mp4")
                .build();
        when(postService.createPost(any(PostDto.class)))
                .thenReturn(Optional.empty());

        // act + assert
        mockMvc.perform(post("/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isBadRequest());
    }

    //updatePost Test

    @Test
    void shouldReturnOkIfUpdatePostContentAndMedia() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post updatedPost = Post.builder()
                .postId("postID")
                .content("New content")
                .mediaUrl("exampleNewMedia.jpg")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.of(updatedPost));

        // act + assert
        mockMvc.perform(post("/post/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void shouldReturnOkIfUpdatePostContentOnly() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post updatedPost = Post.builder()
                .postId("postID")
                .content("New content")
                .mediaUrl("example.jpg")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.of(updatedPost));

        // act + assert
        mockMvc.perform(post("/post/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    @Test
    void shouldReturnOkIfUpdatePostContentOnlyAndNoMedia() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .build();
        Post updatedPost = Post.builder()
                .postId("postID")
                .content("New content")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.of(updatedPost));

        // act + assert
        mockMvc.perform(post("/post/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    @Test
    void shouldReturnOkIfUpdatePostMediaOnly() throws Exception{
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post updatedPost = Post.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("exampleIsKing.jpg")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.of(updatedPost));

        // act + assert
        mockMvc.perform(post("/post/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    @Test
    void shouldReturnBadRequestIfNotUpdatePost() throws Exception{
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.empty());

        // act + assert
        mockMvc.perform(post("/post/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldReturnBadRequestIfTryUpdatePostWithNoContent() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .mediaUrl("example.jpg")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.empty());

        // act + assert
        mockMvc.perform(post("/post/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isBadRequest());
    }


}