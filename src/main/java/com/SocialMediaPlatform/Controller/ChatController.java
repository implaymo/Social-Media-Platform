package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Domain.ChatMessage;
import com.SocialMediaPlatform.Enum.MessageType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
public class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Validated @Payload ChatMessage chatMessage) {

        return chatMessage;

    }

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Validated @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        if(chatMessage == null || headerAccessor == null) {
            throw new IllegalArgumentException("ChatMessage or headerAccessor is null ");
        }
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return ChatMessage.builder()
                .type(MessageType.JOIN)
                .sender("System")
                .content(chatMessage.getSender() + " joined!")
                .build();
    }
}
