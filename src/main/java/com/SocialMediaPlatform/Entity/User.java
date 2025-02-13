package com.SocialMediaPlatform.Entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
@Builder
public class User {

    @Id
    private String id;


    @NonNull
    @NotBlank(message = "Name can't be null or blank")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can only contain letters and spaces")
    private String name;

    @NonNull
    @NotBlank(message = "Email can't be null or blank")
    private String email;

    @NonNull
    @NotBlank(message = "Password can't be null or blank")
    private String password;

}
