package com.SocialMediaPlatform.PasswordEncryption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordSaltTest {

    private PasswordSalt passwordSalt;

    @BeforeEach
    void setUp() {
        passwordSalt = new PasswordSalt();
    }

    @Test
    void shouldGenerateRandomSalt() {
        // arrange
        // act
        byte[] salt = passwordSalt.generateRandomSalt();
        // assert
        assertNotNull(salt);
        assertEquals(16, salt.length);
    }

    @Test
    void shouldGenerateDifferentSalts() {
        // arrange
        // act
        byte[] salt1 = passwordSalt.generateRandomSalt();
        byte[] salt2 = passwordSalt.generateRandomSalt();
        // assert
        assertNotNull(salt1);
        assertNotNull(salt2);
        assertNotEquals(salt1, salt2);
    }
}