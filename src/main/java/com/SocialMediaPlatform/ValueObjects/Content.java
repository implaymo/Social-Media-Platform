package com.SocialMediaPlatform.ValueObjects;

public class Content {
    
    private String name;

    public Content(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }


}
