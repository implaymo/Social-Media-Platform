package com.SocialMediaPlatform.Entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.List;

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
