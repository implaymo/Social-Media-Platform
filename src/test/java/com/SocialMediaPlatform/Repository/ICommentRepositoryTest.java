package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ICommentRepositoryTest {

    @Autowired
    ICommentRepository commentRepository;

    @Test
    void shouldSaveCommentInDatabase() {
        // arrange
        Comment comment = Comment.builder().message("Hello World").postID("postID").userID("userID").build();
        // act
        Comment result = commentRepository.save(comment);
        // assert
        assertNotNull(result);
        assertEquals("Hello World", result.getMessage());
        assertEquals("postID", result.getPostID());
        assertEquals("userID", result.getUserID());
    }

    @Test
    void shouldFindCommentByPostID() {
        // arrange
        Comment comment = Comment.builder().commentID("commentID")
                .message("Hello World")
                .postID("postID")
                .userID("userID")
                .build();
        commentRepository.save(comment);
        // act
        Optional<Comment> commentInDatabase = commentRepository.findById("commentID");
        // assert
        assertTrue(commentInDatabase.isPresent());
    }

    @Test
    void shouldNotFindCommentByPostID() {
        // arrange
        // act
        Optional<Comment> commentInDatabase = commentRepository.findById("NOT VALID POST ID");
        // assert
        assertTrue(commentInDatabase.isEmpty());
    }
}