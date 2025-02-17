package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostMapperTest {

    private PostMapper postMapper;

    @BeforeEach
    void setUp() {
        postMapper = new PostMapper();
    }

    @Test
    void shouldTransformDtoToEntity() {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello World")
                .mediaUrl("http://exampleimage.jpg")
                .build();
        // act
        Post post = postMapper.toEntity(postDto);
        // assert
        assertNotNull(postMapper);
        assertEquals(post.getContent(), postDto.getContent());
        assertEquals(post.getMediaUrl(), postDto.getMediaUrl());
    }

    @Test
    void shouldNotTransformDtoToEntityIfDtoNull() {
        // arrange
        PostDto postDto = null;
        // act
        Post post = postMapper.toEntity(postDto);
        // assert
        assertNull(post);
    }

}