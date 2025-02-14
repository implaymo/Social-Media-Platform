package com.SocialMediaPlatform.PasswordEncryption;

import java.security.SecureRandom;

public class PasswordSalt {

    private byte[] salt;

    public PasswordSalt() {
    }

    public byte[] generateRandomSalt() {
        try {
            salt = new byte[16];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(salt);
            return salt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
