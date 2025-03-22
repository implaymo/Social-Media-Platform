package com.SocialMediaPlatform.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {

    private final String secret;

    public JWTUtil(@Value("${JWT_SECRET}") String secret) {
        this.secret = secret;
    }

    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
        Date expirationDate = new Date(System.currentTimeMillis() + 3600 * 1000);
        String jwtId = UUID.randomUUID().toString();

        return JWT.create()
                .withSubject(email)
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer("SocialMediaPlatform")
                .withExpiresAt(expirationDate)
                .withJWTId(jwtId)
                .sign(Algorithm.HMAC256(secret));
    }

}