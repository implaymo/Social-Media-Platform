package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Domain.Comment;
import com.SocialMediaPlatform.Interface.Comment.ICommentMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements ICommentMapper {

    @Override
    public Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        return Comment.builder()
                .message(commentDto.getComment())
                .build();
    }

}
