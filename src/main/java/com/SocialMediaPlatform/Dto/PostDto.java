package com.SocialMediaPlatform.Dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {

    @NotBlank(message = "Content can't be null or blank")
    private String content;

    private String mediaUrl;
}
