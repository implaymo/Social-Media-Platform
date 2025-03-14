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

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> registerComment(Comment comment, String postID, String userID) {
        if (comment == null || postID == null || userID == null) {
            return Optional.empty();
        }
        comment.setPostID(postID);
        comment.setUserID(userID);
        if(comment.getCommentID() == null) {
            commentRepository.save(comment);
            return Optional.of(comment);
        }
        return Optional.empty();
    }
}
