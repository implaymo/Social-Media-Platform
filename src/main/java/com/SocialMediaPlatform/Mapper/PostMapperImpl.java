package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Interface.Post.IPostMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements IPostMapper {

    @Override
    public Post toEntity(PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        return Post.builder()
                .postId(postDto.getPostId())
                .content(postDto.getContent())
                .mediaUrl(postDto.getMediaUrl())
                .build();
    }
}
