package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.PasswordEncryption.PasswordHash;

import java.util.Base64;

public class PasswordService {

    private final PasswordHash passwordHash;

    public PasswordService(PasswordHash passwordHash) {
        this.passwordHash = passwordHash;
    }


    public String encryptPassword(User userMapped, User userInDatabase) {
        return encryptProvidedPassword(userMapped, userInDatabase);
    }

    private byte[] transformUserInDatabaseSaltFromBase64IntoByte(User userInDatabase) {
        String userInDatabaseSalt = userInDatabase.getSalt();
        return Base64.getDecoder().decode(userInDatabaseSalt);
    }

    private String hashPasswordProvidedWithUserInDatabaseSalt(String password, byte[] userInDatabaseSaltInBytes) {
        return passwordHash.generateHashPassword(password,
                userInDatabaseSaltInBytes);
    }

    private String encryptProvidedPassword(User userMapped, User userInDatabase) {
        byte[] userInDatabaseSalt = transformUserInDatabaseSaltFromBase64IntoByte(userInDatabase);
        return hashPasswordProvidedWithUserInDatabaseSalt(userMapped.getPassword(), userInDatabaseSalt);
    }
}
