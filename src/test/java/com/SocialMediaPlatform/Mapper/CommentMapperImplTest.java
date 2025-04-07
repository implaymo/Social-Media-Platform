package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Domain.Comment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentMapperImplTest {

    @Test
    void shouldTransformCommentDtoIntoCommentEntity() {
        // arrange
        CommentDto commentDto = mock(CommentDto.class);
        when(commentDto.getComment()).thenReturn("Test comment");

        CommentMapperImpl commentMapperImpl = new CommentMapperImpl();

        // act
        Comment result = commentMapperImpl.toEntity(commentDto);

        // assert
        assertNotNull(result);
        assertEquals("Test comment", result.getMessage());
    }

    @Test
    void shouldNotTransformCommentDtoIntoCommentEntityIfCommentDtoIsNull(){
        // arrange
        CommentMapperImpl commentMapperImpl = new CommentMapperImpl();
        CommentDto commentDto = null;
        // act
        Comment result = commentMapperImpl.toEntity(commentDto);
        // assert
        assertNull(result);
    }
}