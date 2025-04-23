package com.SocialMediaPlatform.ValueObjects;

import org.junit.jupiter.api.Test;

import com.SocialMediaPlatform.Domain.ChatMessage;

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

    @Test
    void shouldReturnTrueIfObjectComparedIsTheSame() {
        // arrange 
        String testName = "Test Content";
        Content content = new Content(testName);
        // act
        boolean result = content.equals(content);
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectComparedIsOfAnotherType() {
        // arrange
        String testName = "Test Content";
        Content content = new Content(testName);
        ChatMessage chatMessage = new ChatMessage();
        // act
        boolean result = content.equals(chatMessage);
        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfObjectsHaveDifferentName() {
        // arrange
        String testName1 = "Test Content 1";
        String testName2 = "Test Content 2";
        Content content1 = new Content(testName1);
        Content content2 = new Content(testName2);
        // act
        boolean result = content1.equals(content2);
        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfObjectComparedIsNull() {
        // arrange
        String testName = "Test Content";
        Content content = new Content(testName);
        // act
        boolean result = content.equals(null);
        // assert
        assertFalse(result);
    }

    @Test
    void hashCodeShouldBeEqualForObjectsWithSameName() {
        // arrange
        String testName1 = "Test Content 1";
        String testName2 = "Test Content 1";
        Content content1 = new Content(testName1);
        Content content2 = new Content(testName2);
        // act & assert
        assertEquals(content1.hashCode(), content2.hashCode());
    }

    @Test
    void hashCodeShouldNotBeEqualForObjectWithDifferentName() {
        // arrange
        String testName1 = "Test Content 1";
        String testName2 = "Test Content 2";
        Content content1 = new Content(testName1);
        Content content2 = new Content(testName2);
        // act & assert
        assertNotEquals(content1.hashCode(), content2.hashCode());
    }

    @Test
    void shouldReturnNameOfContent() {
        // arrange
        String testName = "Test Content";
        Content content = new Content(testName);
        // act
        String name = content.getName();
        // assert
        assertEquals(testName, name);
    }
    
    
}