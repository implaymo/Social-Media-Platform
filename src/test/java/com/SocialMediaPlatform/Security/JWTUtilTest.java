package com.SocialMediaPlatform.Security;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JWTUtilTest {

    private JWTUtil jwtUtil;
    private UserLoginDto userLoginDto;

    @BeforeEach
    void setUp() {
        jwtUtil = new JWTUtil();
        userLoginDto = UserLoginDto.builder()
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
    }

    @Test
    void shouldReturnNonNullToken() {
        // arrange
        // act
        String token = jwtUtil.generateToken(userLoginDto.getEmail());
        // assert
        assertNotNull(token);
    }

    @Test
    void shouldReturnEmailUsedToCreateToken() {
        // arrange
        String token = jwtUtil.generateToken(userLoginDto.getEmail());
        // act
        DecodedJWT decodedJWT = JWT.decode(token);
        // assert
        assertEquals(userLoginDto.getEmail(), decodedJWT.getClaim("email").asString());
    }


}