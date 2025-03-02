package com.SocialMediaPlatform.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserRegisterDto {

    @NotBlank(message = "Name can't be null or blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can only contain letters and spaces")
    private String name;

    @NotBlank(message = "Email can't be null or blank")
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email format")
    private String email;

    @NotBlank(message = "Your password must be at least 8 characters long and include a mix of uppercase, " +
            "lowercase, numbers, and special characters.")
    @Size(min = 8, message = "Your password must be at least 8 characters long and include a mix of uppercase, " +
            "lowercase, numbers, and special characters.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$", message =
            "Your password must be at least 8 characters long and include a mix of uppercase, " +
                    "lowercase, numbers, and special characters.")
    private String password;

}
