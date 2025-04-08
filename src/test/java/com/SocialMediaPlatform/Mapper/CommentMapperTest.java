package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Domain.Comment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentMapperTest {

    @Test
    void shouldTransformCommentDtoIntoCommentEntity() {
        // arrange
        CommentDto commentDto = mock(CommentDto.class);
        when(commentDto.getComment()).thenReturn("Test comment");

        CommentMapper ICommentMapper = new CommentMapper();

        // act
        Comment result = ICommentMapper.toEntity(commentDto);

        // assert
        assertNotNull(result);
        assertEquals("Test comment", result.getMessage());
    }

    @Test
    void shouldNotTransformCommentDtoIntoCommentEntityIfCommentDtoIsNull(){
        // arrange
        CommentMapper ICommentMapper = new CommentMapper();
        CommentDto commentDto = null;
        // act
        Comment result = ICommentMapper.toEntity(commentDto);
        // assert
        assertNull(result);
    }
}