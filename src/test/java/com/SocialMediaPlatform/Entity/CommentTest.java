package com.SocialMediaPlatform.Entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CommentTest {

    @Test
    void testCommentBuilder() {
        // arrange & act
        Comment comment = Comment.builder()
                .commentID("comment123")
                .message("Great post!")
                .postID("post456")
                .userID("user789")
                .build();

        // assert
        assertEquals("comment123", comment.getCommentID());
        assertEquals("Great post!", comment.getMessage());
        assertEquals("post456", comment.getPostID());
        assertEquals("user789", comment.getUserID());
    }

    @Test
    void testCommentSetters() {
        // arrange
        Comment comment = Comment.builder().build();

        // act
        comment.setCommentID("comment123");
        comment.setMessage("Great post!");
        comment.setPostID("post456");
        comment.setUserID("user789");

        // assert
        assertEquals("comment123", comment.getCommentID());
        assertEquals("Great post!", comment.getMessage());
        assertEquals("post456", comment.getPostID());
        assertEquals("user789", comment.getUserID());
    }

    @Test
    void testEqualsAndHashCode() {
        // arrange
        Comment comment1 = Comment.builder()
                .commentID("comment123")
                .message("Great post!")
                .postID("post456")
                .userID("user789")
                .build();

        Comment comment2 = Comment.builder()
                .commentID("comment123")
                .message("Great post!")
                .postID("post456")
                .userID("user789")
                .build();
        // act & assert
        assertEquals(comment1, comment2);
        assertEquals(comment1.hashCode(), comment2.hashCode());
    }

    @Test
    void shouldReturnNotEqualIfCommendIDIsDifferent() {
        // arrange
        Comment differentComment = Comment.builder()
                .commentID("differentID")
                .message("Great post!")
                .postID("post456")
                .userID("user789")
                .build();

        Comment comment1 = Comment.builder()
                .commentID("comment123")
                .message("Great post!")
                .postID("post456")
                .userID("user789")
                .build();

        // act & assert
        assertNotEquals(comment1, differentComment);
        assertNotEquals(comment1.hashCode(), differentComment.hashCode());
    }

    @Test
    void testToString() {
        // arrange
        Comment comment = Comment.builder()
                .commentID("comment123")
                .message("Great post!")
                .postID("post456")
                .userID("user789")
                .build();

        // act
        String result = comment.toString();

        // assert
        assertTrue(result.contains("comment123"));
        assertTrue(result.contains("Great post!"));
        assertTrue(result.contains("post456"));
        assertTrue(result.contains("user789"));
    }
}