package com.SocialMediaPlatform.Dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {

    @NotBlank(message = "Post ID can't be null or blank")
    private String postID;

    @NotBlank(message = "User ID can't be null or blank")
    private String userID;

    @NotBlank(message = "Comment can't be null or blank")
    private String comment;
}
