package com.SocialMediaPlatform.ValueObjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MessageTest {

    @Test
    void shouldCreateMessageVO() {
        // arrange 
        String message = "new message";
        // act
        Message messageVO = new Message(message);
        // assert
        assertNotNull(messageVO);
    }

    @Test
    void shouldNotCreateMessageVOIfMessageIsNull() {
        // arrange
        // act && assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Message(null);
        });
    }

    @Test
    void shouldReturnMessage() {
        // arrange
        String message = "new message";
        Message messageVO = new Message(message);
        // act
        String result = messageVO.getMessage();
        // assert
        assertEquals(message, result);
    }
}
