package com.SocialMediaPlatform.Domain;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@Builder
@Document(collection = "comment")
public class Comment {

    @Id
    private String commentID;


    private String message;
    private String postID;
    private String userID;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentID.equals(comment.commentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentID);
    }
}
