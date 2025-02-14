package com.SocialMediaPlatform.Entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

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
