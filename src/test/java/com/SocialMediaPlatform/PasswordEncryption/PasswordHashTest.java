package com.SocialMediaPlatform.PasswordEncryption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {

    private PasswordHash passwordHash;
    private PasswordSalt passwordSalt;
    private String password;

    @BeforeEach
    void setUp() {
        passwordHash = new PasswordHash();
        passwordSalt = new PasswordSalt();
        password = "Password123!";
    }


    @Test
    void shouldGenerateHashPassword() {
        // arrange
        byte[] salt = passwordSalt.generateRandomSalt();
        // act
        String hashedPassword1 = passwordHash.generateHashPassword(password, salt);
        // assert
        assertNotNull(hashedPassword1);
    }

    @Test
    void shouldGenerateSamePasswordIfSaltIsEqual() {
        // arrange
        byte[] salt = passwordSalt.generateRandomSalt();
        String hashedPassword1 = passwordHash.generateHashPassword(password, salt);
        // act
        String hashedPassword2 = passwordHash.generateHashPassword(password, salt);
        // assert
        assertEquals(hashedPassword1, hashedPassword2);
    }

    @Test
    void shouldGenerateDifferentPasswordIfSaltIsDifferent() {
        // arrange
        byte[] salt1 = passwordSalt.generateRandomSalt();
        byte[] salt2 = passwordSalt.generateRandomSalt();
        String hashedPassword1 = passwordHash.generateHashPassword(password, salt1);
        // act
        String hashedPassword2 = passwordHash.generateHashPassword(password, salt2);
        // assert
        assertNotEquals(hashedPassword1, hashedPassword2);
    }
}