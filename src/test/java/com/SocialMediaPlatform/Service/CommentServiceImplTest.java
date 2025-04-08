package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Domain.Comment;
import com.SocialMediaPlatform.Repository.ICommentRepository;
import com.SocialMediaPlatform.Service.Comment.CommentServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentServiceImplTest {


    @Test
    void shouldRegisterLikeInDatabase() {
        // arrange
        String postID = "postId";
        String userID = "userID";
        Comment comment = mock(Comment.class);
        ICommentRepository commentRepository = mock(ICommentRepository.class);
        when(comment.getCommentID()).thenReturn(null);
        when(commentRepository.save(comment)).thenReturn(comment);
        CommentServiceImpl ICommentService = new CommentServiceImpl(commentRepository);
        // act
        Optional <Comment> result = ICommentService.registerComment(comment, postID, userID);
        // assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotRegisterCommentIfAlreadyRegistered() {
        // arrange
        String postID = "postId";
        String userID = "userID";
        Comment comment = mock(Comment.class);
        ICommentRepository commentRepository = mock(ICommentRepository.class);
        CommentServiceImpl ICommentService = new CommentServiceImpl(commentRepository);
        when(comment.getCommentID()).thenReturn("commentID");
        // act
        Optional<Comment> result = ICommentService.registerComment(comment, postID, userID);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotRegisterLikeInDatabaseIfPostIDIsNull() {
        // arrange
        String userID = "userID";
        Comment comment = mock(Comment.class);
        ICommentRepository commentRepository = mock(ICommentRepository.class);
        CommentServiceImpl ICommentService = new CommentServiceImpl(commentRepository);
        // act
        Optional<Comment> result = ICommentService.registerComment(comment, null, userID);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotRegisterLikeInDatabaseIfUserIDIsNull() {
        // arrange
        String postID = "postId";
        Comment comment = mock(Comment.class);
        ICommentRepository commentRepository = mock(ICommentRepository.class);
        CommentServiceImpl ICommentService = new CommentServiceImpl(commentRepository);
        // act
        Optional<Comment> result = ICommentService.registerComment(comment, postID, null);
        // assert
        assertTrue(result.isEmpty());
    }
}