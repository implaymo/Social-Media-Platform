package com.SocialMediaPlatform.ValueObjects;

public class Message {
    
    private String message;

    public Message(String message) {
        if(message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Message messageObj = (Message) obj;
        return message.equals(messageObj.message);
    }

    public int hashCode() {
        return message.hashCode();
    }
}
