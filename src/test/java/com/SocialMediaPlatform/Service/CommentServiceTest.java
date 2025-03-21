package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.Comment;
import com.SocialMediaPlatform.Repository.CommentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentServiceTest {


    @Test
    void shouldRegisterLikeInDatabase() {
        // arrange
        String postID = "postId";
        String userID = "userID";
        Comment comment = mock(Comment.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(comment.getCommentID()).thenReturn(null);
        when(commentRepository.save(comment)).thenReturn(comment);
        CommentService commentService = new CommentService(commentRepository);
        // act
        Optional <Comment> result = commentService.registerComment(comment, postID, userID);
        // assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotRegisterCommentIfAlreadyRegistered() {
        // arrange
        String postID = "postId";
        String userID = "userID";
        Comment comment = mock(Comment.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        CommentService commentService = new CommentService(commentRepository);
        when(comment.getCommentID()).thenReturn("commentID");
        // act
        Optional<Comment> result = commentService.registerComment(comment, postID, userID);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotRegisterLikeInDatabaseIfPostIDIsNull() {
        // arrange
        String userID = "userID";
        Comment comment = mock(Comment.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        CommentService commentService = new CommentService(commentRepository);
        // act
        Optional<Comment> result = commentService.registerComment(comment, null, userID);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotRegisterLikeInDatabaseIfUserIDIsNull() {
        // arrange
        String postID = "postId";
        Comment comment = mock(Comment.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        CommentService commentService = new CommentService(commentRepository);
        // act
        Optional<Comment> result = commentService.registerComment(comment, postID, null);
        // assert
        assertTrue(result.isEmpty());
    }
}