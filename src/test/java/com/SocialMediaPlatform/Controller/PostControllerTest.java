package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Config.MongoConfig;
import com.SocialMediaPlatform.Config.TestSecurityConfig;
import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import com.SocialMediaPlatform.Service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@Import({MongoConfig.class, TestSecurityConfig.class})
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    @Test
    @WithMockUser(username = "john@example.com", roles = "USER")
    void shouldReturnCreatedIfPostCreatedWithBothContentAndMedia() throws Exception {
        // arrange
        User mockUser = mock(User.class);

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        PostDto postDto = PostDto.builder()
                .content("Hello world")
                .mediaUrl("example.mp4")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .userId("userID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postService.createPost(any(PostDto.class), any(CustomUserDetails.class)))
                .thenReturn(Optional.of(post));

        // act + assert
        mockMvc.perform(post("/post/create")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("true"));
    }

    @Test
    @WithMockUser(username = "john@example.com", roles = "USER")
    void shouldReturnOkIfPostCreatedWithContent() throws Exception {
        // arrange
        User mockUser = mock(User.class);

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        PostDto postDto = PostDto.builder()
                .content("Hello world")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .build();
        when(postService.createPost(any(PostDto.class), any(CustomUserDetails.class)))
                .thenReturn(Optional.of(post));

        // act + assert
        mockMvc.perform(post("/post/create")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("true"));
    }

    @Test
    @WithMockUser(username = "john@example.com", roles = "USER")
    void shouldReturnOkIfPostCreatedOnlyWithMedia() throws Exception {
        // arrange
        User mockUser = mock(User.class);

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        PostDto postDto = PostDto.builder()
                .mediaUrl("example.mp4")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .mediaUrl("example.mp4")
                .build();
        when(postService.createPost(any(PostDto.class), any(CustomUserDetails.class)))
                .thenReturn(Optional.of(post));

        // act + assert
        mockMvc.perform(post("/post/create")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("true"));

    }

    @Test
    @WithMockUser(username = "john@example.com", roles = "USER")
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
    @WithMockUser(username = "john@example.com", roles = "USER")
    void shouldReturnBadRequestIfPostAlreadyExistsInDatabase() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello world")
                .mediaUrl("example.mp4")
                .build();
        when(postService.createPost(any(PostDto.class), any(CustomUserDetails.class)))
                .thenReturn(Optional.empty());

        // act + assert
        mockMvc.perform(post("/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isBadRequest());
    }

    //////////////////updatePost Test //////////////

    @Test
    @WithMockUser(username = "john@example.com", roles = "USER")
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
    @WithMockUser(username = "john@example.com", roles = "USER")
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
    @WithMockUser(username = "john@example.com", roles = "USER")
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
    @WithMockUser(username = "john@example.com", roles = "USER")
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
    @WithMockUser(username = "john@example.com", roles = "USER")
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
    @WithMockUser(username = "john@example.com", roles = "USER")
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

    //////////////////////////// deletePost tests ///////////////////////////////////////////


    @Test
    @WithMockUser(username = "john@example.com", roles = "USER")
    void shouldReturnOkIfDeletePost() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .mediaUrl("example.jpg")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postService.deletePost(any(PostDto.class))).thenReturn(Optional.of(post));
        // act & assert
        mockMvc.perform(post("/post/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @WithMockUser(username = "john@example.com", roles = "USER")
    void shouldReturnBadRequestIfPostNotExist() throws Exception{
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .mediaUrl("example.jpg")
                .build();
        when(postService.deletePost(any(PostDto.class))).thenReturn(Optional.empty());
        // act & assert
        mockMvc.perform(post("/post/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("false"));
    }
}