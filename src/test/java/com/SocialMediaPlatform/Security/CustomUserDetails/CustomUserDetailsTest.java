package com.SocialMediaPlatform.Security.CustomUserDetails;

import com.SocialMediaPlatform.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomUserDetailsTest {

    @Test
    void shouldCreateUserDetailsFromUser() {
        // Arrange
        User user = mock(User.class);
        when(user.getId()).thenReturn("userID");
        when(user.getEmail()).thenReturn("example@gmail.com");
        when(user.getPassword()).thenReturn("hashedpassword");

        // Act
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        // Assert
        assertEquals("userID", customUserDetails.getId());
        assertEquals("example@gmail.com", customUserDetails.getUsername());
        assertEquals("hashedpassword", customUserDetails.getPassword());
        assertTrue(customUserDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(customUserDetails.isEnabled());
    }

}