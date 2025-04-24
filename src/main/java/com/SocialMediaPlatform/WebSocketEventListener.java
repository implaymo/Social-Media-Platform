package com.SocialMediaPlatform;

import com.SocialMediaPlatform.Domain.ChatMessage;
import com.SocialMediaPlatform.Enum.MessageType;
import com.SocialMediaPlatform.ValueObjects.Content;
import com.SocialMediaPlatform.ValueObjects.Sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection");

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        Sender sender = new Sender("System");
        Content content = new Content(username + " left!");
        if (username != null) {

            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(sender)
                    .content(content)
                    .build();

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
