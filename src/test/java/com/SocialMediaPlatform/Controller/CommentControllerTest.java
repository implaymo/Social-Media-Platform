package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Entity.Comment;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import com.SocialMediaPlatform.Service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRegisterComment() {
        // arrange
        User user = User.builder()
                .id("userId")
                .name("John")
                .email("test@example.com")
                .password("John@1234")
                .build();
        Comment comment = Comment.builder().commentID("commentId").message("message").build();
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String postID = "postId";
        String userID = "userId";
        CommentDto commentDto = mock(CommentDto.class);
        CommentService commentService = mock(CommentService.class);
        CommentController commentController = new CommentController(commentService);

        when(commentService.registerComment(commentDto, postID, userID)).thenReturn(Optional.of(comment));

        // act
        ResponseEntity<Boolean> response = commentController.registerComment(commentDto, postID);

        // assert
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    void shouldNotRegisterCommentIfPostIdIsNull() {
        // arrange
        User mockUser = mock(User.class);

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String postID = "postId";
        String userID = "userID";
        CommentDto commentDto = mock(CommentDto.class);
        CommentService commentService = mock(CommentService.class);
        CommentController commentController = new CommentController(commentService);
        when(commentService.registerComment(commentDto, postID, userID)).thenReturn(Optional.empty());
        // act
        ResponseEntity response = commentController.registerComment(commentDto, postID);
        // assert
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldRejectInvalidComment() throws Exception {
        String emptyJson = "{}";

        mockMvc.perform(MockMvcRequestBuilders.post("/comment/{postId}", "postId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emptyJson)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .with(SecurityMockMvcRequestPostProcessors.user(
                                new CustomUserDetails(User.builder()
                                        .id("userId")
                                        .email("test@example.com")
                                        .password("password")
                                        .build())))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}