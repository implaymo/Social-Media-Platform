package com.SocialMediaPlatform.Domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "user")
@Data
@Builder
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String salt;

}
