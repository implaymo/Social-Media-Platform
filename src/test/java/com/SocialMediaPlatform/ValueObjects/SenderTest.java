package com.SocialMediaPlatform.ValueObjects;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SenderTest {

    @Test
    void shouldReturnTrueWhenEqualsSameObject() {
        // arrange
        Sender sender = new Sender("user1");

        // act
        boolean result = sender.equals(sender);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenEqualsNullObject() {
        // arrange
        Sender sender = new Sender("user1");

        // act
        boolean result = sender.equals(null);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenEqualsDifferentClass() {
        // arrange
        Sender sender = new Sender("user1");
        String otherObject = "user1";

        // act
        boolean result = sender.equals(otherObject);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenEqualsDifferentSenderValue() {
        // arrange
        Sender sender1 = new Sender("user1");
        Sender sender2 = new Sender("user2");

        // act
        boolean result = sender1.equals(sender2);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenEqualsSameSenderValue() {
        // arrange
        Sender sender1 = new Sender("user1");
        Sender sender2 = new Sender("user1");

        // act
        boolean result = sender1.equals(sender2);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfHashCodesAreEqual() {
        // arrange
        Sender sender1 = new Sender("user1");
        Sender sender2 = new Sender("user1");
        // act & assert
        assertEquals(sender1.hashCode(), sender2.hashCode());
    }

    @Test
    void shouldReturnFalseIfHashCodesNotEqual() {
        // arrange
        Sender sender1 = new Sender("user1");
        Sender sender2 = new Sender("user2");
        // act & assert
        assertNotEquals(sender1.hashCode(), sender2.hashCode());
    }

    @Test
    void shouldReturnTrueIfGetNameFromSender() {
        // arrange
        String senderName = "user1";
        Sender sender1 = new Sender(senderName);
        // act
        String name = sender1.getSender();
        // assert
        assertEquals(senderName, name);
    }

    @Test
    void shouldReturnFalseIfNamesAreDifferent() {
        // arrange
        String senderName1 = "user1";
        String senderName2 = "user1";
        Sender sender1 = new Sender(senderName1);
        Sender sender2 = new Sender(senderName2);
        // act & assert
        assertEquals(sender2.getSender(), sender1.getSender());
    }

    @Test
    void shouldThrowExceptionIfSenderIsNull(){
        // arrange
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> new Sender(null));
    }
}