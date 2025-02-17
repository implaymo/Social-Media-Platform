package com.SocialMediaPlatform.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Data
@Builder
public class Post {

    @Id
    private String postId;

    private String content;
    private String mediaUrl;
    private LocalDateTime timestamp;
}
