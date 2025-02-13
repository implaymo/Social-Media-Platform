package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrueIfEmailSearchedNotInDatabase() {
        // arrange
        when(userRepository.existsByEmail("john123@gmail.com")).thenReturn(true);

        // act
        boolean userSearched = userRepository.existsByEmail("john123@gmail.com");

        // assert
        assertTrue(userSearched);
    }

    @Test
    void shouldReturnFalseIfEmailSearchedNotInDatabase() {
        // arrange
        when(userRepository.existsByEmail("john123@gmail.com")).thenReturn(true);

        // act
        boolean userSearched = userRepository.existsByEmail("lionking@gmail.com");

        // assert
        assertFalse(userSearched);
    }
}