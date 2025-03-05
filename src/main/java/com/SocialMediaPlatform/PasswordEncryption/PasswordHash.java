package com.SocialMediaPlatform.PasswordEncryption;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class PasswordHash {

    private static final Integer iterationCount = 65536;
    private static final Integer keyLength = 256;

    public PasswordHash() {

    }


    public String generateHashPassword(String password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
