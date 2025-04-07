package com.SocialMediaPlatform.Domain;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldReturnUserObjectAndFields() {
        // arrange
        User user = User.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();

        // act + assert
        assertNotNull(user);
        assertEquals("John Cena", user.getName());
        assertEquals("john123@gmail.com", user.getEmail());
        assertEquals("John@12345", user.getPassword());
    }


}