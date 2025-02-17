package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post toEntity(PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        return Post.builder()
                .content(postDto.getContent())
                .mediaUrl(postDto.getMediaUrl())
                .build();
    }
}
