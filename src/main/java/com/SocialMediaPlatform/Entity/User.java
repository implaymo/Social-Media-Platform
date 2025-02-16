package com.SocialMediaPlatform.Entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
@Builder
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String salt;

}
