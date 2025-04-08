package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Domain.Comment;
import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Interface.Comment.ICommentMapper;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import com.SocialMediaPlatform.Service.Comment.ICommentService;
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
        ICommentService ICommentService = mock(ICommentService.class);
        ICommentMapper ICommentMapper = mock(ICommentMapper.class);
        CommentController commentController = new CommentController(ICommentService, ICommentMapper);
        when(ICommentMapper.toEntity(commentDto)).thenReturn(comment);
        when(ICommentService.registerComment(comment, postID, userID)).thenReturn(Optional.of(comment));

        // act
        ResponseEntity<Boolean> response = commentController.registerComment(commentDto, postID);

        // assert
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(Boolean.TRUE, response.getBody());
    }

    @Test
    void shouldNotRegisterCommentIfPostIdIsNull() {
        // arrange
        User mockUser = mock(User.class);

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String userID = "userID";
        Comment comment = mock(Comment.class);
        CommentDto commentDto = mock(CommentDto.class);
        ICommentService ICommentService = mock(ICommentService.class);
        ICommentMapper ICommentMapper = mock(ICommentMapper.class);
        CommentController commentController = new CommentController(ICommentService, ICommentMapper);
        when(ICommentMapper.toEntity(commentDto)).thenReturn(comment);
        when(ICommentService.registerComment(comment, null, userID)).thenReturn(Optional.empty());
        // act
        ResponseEntity<Boolean> response = commentController.registerComment(commentDto, null);
        // assert
        assertTrue(response.getStatusCode().is4xxClientError());
    }


    @Test
    void shouldNotRegisterCommentIfCommentDtoIsNull() {
        // arrange
        User mockUser = mock(User.class);

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String userID = "userID";
        String postID = "postID";
        Comment comment = mock(Comment.class);
        CommentDto commentDto = mock(CommentDto.class);
        ICommentService ICommentService = mock(ICommentService.class);
        ICommentMapper ICommentMapper = mock(ICommentMapper.class);
        CommentController commentController = new CommentController(ICommentService, ICommentMapper);
        when(ICommentMapper.toEntity(commentDto)).thenReturn(comment);
        when(ICommentService.registerComment(comment, null, userID)).thenReturn(Optional.empty());
        // act
        ResponseEntity<Boolean> response = commentController.registerComment(null, postID);
        // assert
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldNotRegisterCommentIfUserIdIsNull() {
        // arrange
        User mockUser = mock(User.class);

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String postID = "postID";
        Comment comment = mock(Comment.class);
        CommentDto commentDto = mock(CommentDto.class);
        ICommentService ICommentService = mock(ICommentService.class);
        ICommentMapper ICommentMapper = mock(ICommentMapper.class);
        CommentController commentController = new CommentController(ICommentService, ICommentMapper);
        when(ICommentMapper.toEntity(commentDto)).thenReturn(comment);
        when(ICommentService.registerComment(comment, postID, null)).thenReturn(Optional.empty());
        // act
        ResponseEntity<Boolean> response = commentController.registerComment(commentDto, null);
        // assert
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldRejectInvalidComment() throws Exception {
        String emptyJson = "{}";

        mockMvc.perform(MockMvcRequestBuilders.post("/comment/{postId}", "postId")
                        .secure(true)
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

    @Test
    void shouldThrowIllegalArgumentExceptionIfCommentServiceIsNull() throws Exception {
        // arrange
        ICommentMapper iCommentMapper = mock(ICommentMapper.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CommentController(null, iCommentMapper);
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfCommentMapperIsNull() throws Exception {
        // arrange
        ICommentService iCommentService = mock(ICommentService.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CommentController(iCommentService, null);
        });
    }

}