package com.SocialMediaPlatform.Entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "comment")
public class Comment {

    @Id
    private String commentID;


    private String message;
    private String postID;
    private String userID;
}
