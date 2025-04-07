package com.SocialMediaPlatform.Interface.Comment;

import com.SocialMediaPlatform.Domain.Comment;
import com.SocialMediaPlatform.Dto.CommentDto;

public interface ICommentMapper {
    Comment toEntity(CommentDto commentDto);
}
