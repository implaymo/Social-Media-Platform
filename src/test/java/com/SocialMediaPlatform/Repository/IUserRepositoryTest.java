package com.SocialMediaPlatform.Repository;


import com.SocialMediaPlatform.Domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class IUserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        user = userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void shouldFindUserById() {
        // arrange
        // act
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        // assert
        assertTrue(foundUser.isPresent());
        assertEquals(user.getName(), foundUser.get().getName());
        assertEquals(user.getPassword(), foundUser.get().getPassword());
    }
}