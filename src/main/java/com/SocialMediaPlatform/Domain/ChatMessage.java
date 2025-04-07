package com.SocialMediaPlatform.Domain;


import com.SocialMediaPlatform.Enum.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    @NotNull
    private MessageType type;

    @NotBlank
    private String content;

    @NotBlank
    private String sender;
}