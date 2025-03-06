package com.SocialMediaPlatform.Entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {

    private String message;
    private String postID;
    private String userID;
}
