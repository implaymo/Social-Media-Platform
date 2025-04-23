package com.SocialMediaPlatform.ValueObjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class ContentTest {

    @Test
    void constructorShouldInitializeName() {
        String testName = "Test Content";
        Content content = new Content(testName);
        assertNotNull(content);
    }

    @Test
    void constructorShouldThrowExceptionWhenNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Content(null);
        });
        assertEquals("Name cannot be null", exception.getMessage());
    }
}