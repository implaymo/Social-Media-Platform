package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Entity.Comment;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Mapper.CommentMapper;
import com.SocialMediaPlatform.Repository.CommentRepository;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentServiceTest {


    @Test
    void shouldRegisterLikeInDatabase() {
        // arrange
        String postID = "postId";
        String userID = "userID";
        CommentDto commentDto = mock(CommentDto.class);
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);
        CommentMapper commentMapper = mock(CommentMapper.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        Comment comment = mock(Comment.class);
        when(commentMapper.toEntity(commentDto)).thenReturn(comment);
        CommentService commentService = new CommentService(commentMapper, commentRepository);
        // act
        Optional <Comment> result = commentService.registerComment(commentDto, postID, userID);
        // assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotRegisterLikeInDatabaseIfCommentDtoIsNull() {
        // arrange
        String postID = "postId";
        String userID = "userID";
        CommentDto commentDto = mock(CommentDto.class);
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);
        CommentMapper commentMapper = mock(CommentMapper.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        CommentService commentService = new CommentService(commentMapper, commentRepository);
        // act
        Optional<Comment> result = commentService.registerComment(commentDto, postID, userID);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotRegisterLikeInDatabaseIfPostIDIsNull() {
        // arrange
        String postID = "postId";
        String userID = "userID";
        CommentDto commentDto = mock(CommentDto.class);
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);
        CommentMapper commentMapper = mock(CommentMapper.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        CommentService commentService = new CommentService(commentMapper, commentRepository);
        // act
        Optional<Comment> result = commentService.registerComment(commentDto, postID, userID);
        // assert
        assertTrue(result.isEmpty());
    }
}