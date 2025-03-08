package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Repository.UserRepository;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Test
    void shouldLoadUserByUsername() {
        // Arrange
        String email = "test@example.com";
        User user = User.builder()
                .id("userId")
                .email(email)
                .password("password")
                .name("Test User")
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Assert
        assertNotNull(userDetails);
        assertTrue(userDetails instanceof CustomUserDetails);
        assertEquals(email, userDetails.getUsername());

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        assertEquals("userId", customUserDetails.getId());

        // Verify repository was called
        verify(userRepository).findByEmail(email);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(email)
        );

        assertEquals("User not found: " + email, exception.getMessage());

        // Verify repository was called
        verify(userRepository).findByEmail(email);
    }
}