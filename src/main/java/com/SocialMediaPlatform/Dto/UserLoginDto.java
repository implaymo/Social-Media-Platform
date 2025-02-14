package com.SocialMediaPlatform.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDto {

    @NotBlank(message = "Email can't be null or blank")
    private String email;

    @NotBlank(message = "Password can't be null or blank")
    private String password;
}
