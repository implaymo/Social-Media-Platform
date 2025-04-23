package com.SocialMediaPlatform.ValueObjects;

public class Sender {
    
    private String sender;

    public Sender(String sender) {
        if (sender == null) {
            throw new IllegalArgumentException("sender cannot be null");
        }
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sender sender1 = (Sender) obj;
        return sender.equals(sender1.sender);
    }

    @Override 
    public int hashCode() {
        return sender.hashCode();
    }
}
