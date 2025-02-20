package com.SocialMediaPlatform.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class LikeDto {

    @Id
    private String likeID;

    @NotBlank(message = "User ID cannot be empty")
    private String userID;
}
