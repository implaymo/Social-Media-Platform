package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Config.MongoConfig;
import com.SocialMediaPlatform.Config.TestSecurityConfig;
import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Mapper.PostMapper;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import com.SocialMediaPlatform.Service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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

    @MockitoBean
    private PostMapper postMapper;

    @Test
    @WithMockUser(username = "john@example.com", roles = "USER")
    void shouldReturnCreatedIfPostCreated() throws Exception {
        // arrange
        User mockUser = mock(User.class);

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        PostDto postDto = PostDto.builder()
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();

        Post post = Post.builder()
                .postId("postID")
                .userId("userID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postMapper.toEntity(postDto)).thenReturn(post);
        when(postService.createPost(post, userDetails))
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
        User mockUser = mock(User.class);

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        PostDto postDto = PostDto.builder()
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .mediaUrl("example.mp4")
                .build();
        when(postMapper.toEntity(postDto)).thenReturn(post);
        when(postService.createPost(post, userDetails))
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
    void shouldReturnOkIfUpdatePost() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post updatedPost = Post.builder()
                .postId("postID")
                .content("New content")
                .mediaUrl("exampleNewMedia.jpg")
                .build();
        when(postMapper.toEntity(postDto)).thenReturn(updatedPost);
        when(postService.updatePost(updatedPost)).thenReturn(Optional.of(updatedPost));

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
        PostDto postDto = mock(PostDto.class);
        Post post = mock(Post.class);
        when(postMapper.toEntity(postDto)).thenReturn(post);
        when(postService.updatePost(post)).thenReturn(Optional.empty());

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
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postMapper.toEntity(postDto)).thenReturn(post);
        when(postService.deletePost(post)).thenReturn(Optional.of(post));
        // act & assert
        mockMvc.perform(post("/post/delete")
                .with(csrf())
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
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postMapper.toEntity(postDto)).thenReturn(post);
        when(postService.deletePost(post)).thenReturn(Optional.empty());
        // act & assert
        mockMvc.perform(post("/post/delete")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("false"));
    }

    @Test
    void shouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();

        // act & assert
        mockMvc.perform(post("/post/create")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDto)))
                .andExpect(status().isUnauthorized());
    }
}

