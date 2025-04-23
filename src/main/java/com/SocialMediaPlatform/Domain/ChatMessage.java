package com.SocialMediaPlatform.Domain;

import com.SocialMediaPlatform.Enum.MessageType;
import com.SocialMediaPlatform.ValueObjects.Content;
import com.SocialMediaPlatform.ValueObjects.Sender;
import jakarta.validation.Valid;
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

    @Valid
    @NotNull
    private Content content;

    @Valid
    @NotNull
    private Sender sender;
}