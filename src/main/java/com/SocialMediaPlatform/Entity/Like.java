package com.SocialMediaPlatform.Entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Like {

    @Id
    private String id;

    @NotBlank(message =  "Post ID can't be null or blank")
    private String postID;

    @NotBlank(message =  "User ID can't be null or blank")
    private String userID;
}
