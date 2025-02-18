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
    private Post post;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(postController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
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
    void shouldReturnBadRequestIfPostCreatedOnlyWithMedia() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .mediaUrl("example.mp4")
                .build();
        // act + assert
        mockMvc.perform(post("/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isBadRequest());

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

    //editPost

    @Test
    void shouldReturnOkIfEditContentAndMediaSuccessfully(){
        // arrange
        // act
        // assert
    }

    @Test
    void shouldReturnOkIfEditContentSuccessfully(){
        // arrange
        // act
        // assert
    }

    @Test
    void shouldReturnGoodRequestIfEditMediaSuccessfully() {
        // arrange
        // act
        // assert
    }

    @Test
    void shouldReturnBadRequestIfNotEditPost(){
        // arrange
        // act
        // assert
    }


}