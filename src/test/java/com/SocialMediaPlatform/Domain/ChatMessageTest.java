package com.SocialMediaPlatform.Domain;

import com.SocialMediaPlatform.Enum.MessageType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChatMessageTest {

    @Test
    void shouldCreateChatMessage() {
        // arrange
        MessageType messageType = mock(MessageType.class);
        // act
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content("Hello").sender("user").build();
        // assert
        assertNotNull(chatMessage);
    }

    @Test
    void shouldFailValidationWhenTypeIsNull() {
        // arrange
        ChatMessage chatMessage = ChatMessage.builder().type(null).content("Hello").sender("user").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // act
        Set<ConstraintViolation<ChatMessage>> violations = validator.validate(chatMessage);

        // assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void shouldNotCreateChatMessageIfMessageContentIsNull() {
        // arrange
        MessageType messageType = mock(MessageType.class);
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content(null).sender("user").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // act
        Set<ConstraintViolation<ChatMessage>> violations = validator.validate(chatMessage);

        // assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    void shouldNotCreateChatMessageIfMessageContentIsEmpty() {
        // arrange
        MessageType messageType = mock(MessageType.class);
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content("").sender("user").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // act
        Set<ConstraintViolation<ChatMessage>> violations = validator.validate(chatMessage);

        // assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    void shouldNotCreateChatMessageIfSenderIsEmpty() {
        // arrange
        MessageType messageType = mock(MessageType.class);
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content("Hello").sender("").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // act
        Set<ConstraintViolation<ChatMessage>> violations = validator.validate(chatMessage);

        // assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    void shouldNotCreateChatMessageIfSenderIsNull() {
        // arrange
        MessageType messageType = mock(MessageType.class);
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content("Hello").sender(null).build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // act
        Set<ConstraintViolation<ChatMessage>> violations = validator.validate(chatMessage);

        // assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }
}