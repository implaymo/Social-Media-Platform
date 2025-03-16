package com.SocialMediaPlatform.Dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {

    @NotBlank(message = "Comment can't be null or blank")
    private String comment;
}
