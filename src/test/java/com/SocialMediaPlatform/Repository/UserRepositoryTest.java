package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

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
    void shouldReturnTrueIfEmailSearchedInDatabase() {
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

    @Test
    void shouldReturnTrueIfPasswordInDatabase() {
        // arrange
        when(userRepository.passwordMatch("Password123!")).thenReturn(true);
        // act
        boolean userPassword = userRepository.passwordMatch("Password123!");
        // assert
        assertTrue(userPassword);
    }

    @Test
    void shouldReturnFalseIfPasswordInDatabase() {
        // arrange
        when(userRepository.passwordMatch("Password123!")).thenReturn(true);
        // act
        boolean userPassword = userRepository.passwordMatch("Password1234!");
        // assert
        assertFalse(userPassword);
    }

    @Test
    void shouldReturnOptionalIfUserInDatabase() {
        // arrange
        User user = User.builder()
                .email("john123@gmail.com")
                .build();
        // Mock the repository to return the user when searching for the specific email
        when(userRepository.findByEmail("john123@gmail.com")).thenReturn(Optional.of(user));

        // act
        Optional<User> userInDatabase = userRepository.findByEmail("john123@gmail.com");

        // assert
        assertTrue(userInDatabase.isPresent());
        assertEquals("john123@gmail.com", userInDatabase.get().getEmail());
    }

    @Test
    void shouldReturnEmptyIfUserNotInDatabase() {
        // arrange
        when(userRepository.findByEmail("mickylauda@gmail.com")).thenReturn(Optional.empty());

        // act
        Optional<User> userInDatabase = userRepository.findByEmail("mickylauda@gmail.com");

        // assert
        assertTrue(userInDatabase.isEmpty());
    }

}