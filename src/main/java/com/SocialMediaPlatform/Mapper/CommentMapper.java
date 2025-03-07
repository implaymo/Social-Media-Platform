package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        Comment comment = Comment.builder()
                .message(commentDto.getComment())
                .userID(commentDto.getUserID())
                .postID(commentDto.getPostID())
                .build();

        return comment;
    }

}
