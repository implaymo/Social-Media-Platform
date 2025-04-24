package com.SocialMediaPlatform.Domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.SocialMediaPlatform.ValueObjects.Message;

class CommentTest {

    @Test
    void testCommentBuilder() {
        // arrange
        Message message = mock(Message.class);
        // act
        Comment comment = Comment.builder()
                .commentID("comment123")
                .message(message)
                .postID("post456")
                .userID("user789")
                .build();

        // assert
        assertNotNull(comment);
    }

    @Test
    void testCommentSetters() {
        // arrange
        Comment comment = Comment.builder().build();
        Message message = mock(Message.class);
        // act
        comment.setCommentID("comment123");
        comment.setMessage(message);
        comment.setPostID("post456");
        comment.setUserID("user789");

        // assert
        assertEquals("comment123", comment.getCommentID());
        assertEquals(message, comment.getMessage());
        assertEquals("post456", comment.getPostID());
        assertEquals("user789", comment.getUserID());
    }

    @Test
    void testEqualsAndHashCode() {
        // arrange
        Message message = mock(Message.class);
        Comment comment1 = Comment.builder()
                .commentID("comment123")
                .message(message)
                .postID("post456")
                .userID("user789")
                .build();

        Comment comment2 = Comment.builder()
                .commentID("comment123")
                .message(message)
                .postID("post456")
                .userID("user789")
                .build();
        // act & assert
        assertEquals(comment1, comment2);
        assertEquals(comment1.hashCode(), comment2.hashCode());
    }

    @Test
    void shouldReturnNotEqualIfCommentIDIsDifferent() {
        // arrange
        Message message = mock(Message.class);
        Comment differentComment = Comment.builder()
                .commentID("differentID")
                .message(message)
                .postID("post456")
                .userID("user789")
                .build();

        Comment comment1 = Comment.builder()
                .commentID("comment123")
                .message(message)
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
        Message message = mock(Message.class);
        Comment comment = Comment.builder()
                .commentID("comment123")
                .message(message)
                .postID("post456")
                .userID("user789")
                .build();

        // act
        String result = comment.toString();

        // assert
        assertTrue(result.contains("comment123"));
        assertTrue(result.contains("post456"));
        assertTrue(result.contains("user789"));
    }
}