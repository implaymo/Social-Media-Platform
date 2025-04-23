package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Domain.ChatMessage;
import com.SocialMediaPlatform.Enum.MessageType;
import com.SocialMediaPlatform.ValueObjects.Sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChatControllerTest {


    @Test
    void shouldSendChatMessage() {
        // arrange
        ChatMessage chatMessage = mock(ChatMessage.class);
        ChatController chatController = new ChatController();
        // act
        ChatMessage result = chatController.sendMessage(chatMessage);
        // assert
        Assertions.assertEquals(chatMessage, result);
    }

    @Test
    void shouldNotSendChatMessage() {
        // arrange
        ChatController chatController = new ChatController();
        // act
        ChatMessage result = chatController.sendMessage(null);
        // assert
        Assertions.assertNull(result);
    }

    @Test
    void shouldAddUserToChat() {
            // arrange
            SimpMessageHeaderAccessor headerAccessor = mock(SimpMessageHeaderAccessor.class);
            ChatMessage chatMessage = mock(ChatMessage.class);
            Sender sender = mock(Sender.class);
            when(chatMessage.getSender()).thenReturn(sender);

            Map<String, Object> sessionAttributes = new HashMap<>();
            when(headerAccessor.getSessionAttributes()).thenReturn(sessionAttributes);

            ChatController chatController = new ChatController();

            // act
            ChatMessage result = chatController.addUser(chatMessage, headerAccessor);

            // assert
            Assertions.assertEquals(MessageType.JOIN, result.getType());
            Assertions.assertEquals("System", result.getSender());
            Assertions.assertEquals("testUser joined!", result.getContent());
            Assertions.assertEquals("testUser", sessionAttributes.get("username"));
    }

    @Test
    void shouldNotAddUserToChatIfChatMessageIsNull() {
        // arrange
        SimpMessageHeaderAccessor headerAccessor = mock(SimpMessageHeaderAccessor.class);
        ChatController chatController = new ChatController();
        // act + assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> chatController.addUser(null, headerAccessor));
    }

    @Test
    void shouldNotAddUserToChatIfHeaderIsNull() {
        // arrange
        ChatMessage chatMessage = mock(ChatMessage.class);
        ChatController chatController = new ChatController();
        // act & assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> chatController.addUser(chatMessage,null));
    }


}