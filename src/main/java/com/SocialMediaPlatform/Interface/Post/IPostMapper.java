package com.SocialMediaPlatform.Interface.Post;

import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Dto.PostDto;

public interface IPostMapper {
    Post toEntity(PostDto postDto);
}
