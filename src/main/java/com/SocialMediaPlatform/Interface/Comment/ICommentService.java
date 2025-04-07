package com.SocialMediaPlatform.Interface.Comment;

import com.SocialMediaPlatform.Domain.Comment;

import java.util.Optional;

public interface ICommentService {
    Optional<Comment> registerComment(Comment comment, String postID, String userID);
}
