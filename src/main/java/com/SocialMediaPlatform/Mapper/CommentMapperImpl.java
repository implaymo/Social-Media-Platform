package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Domain.Comment;
import com.SocialMediaPlatform.Interface.Comment.ICommentMapper;
import com.SocialMediaPlatform.ValueObjects.Message;

import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements ICommentMapper {

    @Override
    public Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        Message message = new Message(commentDto.getComment());
        return Comment.builder()
                .message(message)
                .build();
    }

}
