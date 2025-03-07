package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Entity.Comment;
import com.SocialMediaPlatform.Mapper.CommentMapper;
import com.SocialMediaPlatform.Repository.CommentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentServiceTest {


    @Test
    void shouldRegisterLikeInDatabase() {
        // arrange
        CommentDto commentDto = mock(CommentDto.class);
        CommentMapper commentMapper = mock(CommentMapper.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        Comment comment = mock(Comment.class);
        CommentService commentService = new CommentService(commentMapper, commentRepository);
        when(commentMapper.toEntity(commentDto)).thenReturn(comment);
        // act
        Optional <Comment> result = commentService.registerComment(commentDto);
        // assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotRegisterLikeInDatabaseIfCommentDtoIsNull() {
        // arrange
        CommentDto commentDto = null;
        CommentMapper commentMapper = mock(CommentMapper.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        CommentService commentService = new CommentService(commentMapper, commentRepository);
        // act
        Optional<Comment> result = commentService.registerComment(commentDto);
        // assert
        assertTrue(result.isEmpty());
    }
}