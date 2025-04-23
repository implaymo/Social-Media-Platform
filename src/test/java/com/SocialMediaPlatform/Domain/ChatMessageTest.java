package com.SocialMediaPlatform.Domain;

import com.SocialMediaPlatform.Enum.MessageType;
import com.SocialMediaPlatform.ValueObjects.Content;
import com.SocialMediaPlatform.ValueObjects.Sender;

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
        Content content = mock(Content.class);
        Sender sender = mock(Sender.class);
        MessageType messageType = mock(MessageType.class);
        // act
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content(content).sender(sender).build();
        // assert
        assertNotNull(chatMessage);
    }

    @Test
    void shouldFailValidationWhenTypeIsNull() {
        // arrange
        Content content = mock(Content.class);
        Sender sender = mock(Sender.class);
        ChatMessage chatMessage = ChatMessage.builder().type(null).content(content).sender(sender).build();
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
        Sender sender = mock(Sender.class);
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content(null).sender(sender).build();
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
        Sender sender = mock(Sender.class);
        Content emptyContent = mock(Content.class);
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content(emptyContent).sender(sender).build();
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
        Content content = mock(Content.class);
        Sender emptySender = mock(Sender.class);
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content(content).sender(emptySender).build();
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
        Content content = mock(Content.class);
        ChatMessage chatMessage = ChatMessage.builder().type(messageType).content(content).sender(null).build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // act
        Set<ConstraintViolation<ChatMessage>> violations = validator.validate(chatMessage);

        // assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }
}