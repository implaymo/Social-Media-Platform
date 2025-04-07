package com.SocialMediaPlatform.Service.Comment;

import com.SocialMediaPlatform.Domain.Comment;
import com.SocialMediaPlatform.Repository.ICommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ICommentService implements com.SocialMediaPlatform.Interface.Comment.ICommentService {

    private final ICommentRepository commentRepository;

    public ICommentService(ICommentRepository commentRepository) {
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
