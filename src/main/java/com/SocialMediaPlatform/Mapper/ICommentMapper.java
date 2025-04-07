package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Domain.Comment;
import org.springframework.stereotype.Component;

@Component
public class ICommentMapper implements com.SocialMediaPlatform.Interface.Comment.ICommentMapper {

    public Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        return Comment.builder()
                .message(commentDto.getComment())
                .build();
    }

}
