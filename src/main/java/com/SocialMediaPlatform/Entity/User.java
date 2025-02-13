package com.SocialMediaPlatform.Entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
    private String name;

    @NonNull
    @NotBlank(message = "Email can't be null or blank")
    private String email;

    @NonNull
    @NotBlank(message = "Password can't be null or blank")
    private String password;

}
