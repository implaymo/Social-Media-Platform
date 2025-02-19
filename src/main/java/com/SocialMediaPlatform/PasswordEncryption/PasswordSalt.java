package com.SocialMediaPlatform.PasswordEncryption;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordSalt {

    private byte[] salt;

    public PasswordSalt() {
    }

    public byte[] generateRandomSalt() {
        salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }


}
