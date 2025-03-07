package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Entity.Comment;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Mapper.CommentMapper;
import com.SocialMediaPlatform.Repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public CommentService(CommentMapper commentMapper, CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> registerComment(CommentDto commentDto, String postID) {
        if (commentDto == null || postID == null) {
            return Optional.empty();
        }
        commentDto.setPostID(postID);
        Comment comment = commentMapper.toEntity(commentDto);
        if (comment != null) {
            commentRepository.save(comment);
            return Optional.of(comment);
        }
        return Optional.empty();
    }
}
