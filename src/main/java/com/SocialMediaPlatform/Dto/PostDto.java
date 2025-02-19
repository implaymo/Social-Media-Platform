package com.SocialMediaPlatform.Dto;


import com.SocialMediaPlatform.CustomAnnotation.ContentOrMediaUrlRequired;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ContentOrMediaUrlRequired
public class PostDto {

    private String postId;

    private String content;
    private String mediaUrl;
}
