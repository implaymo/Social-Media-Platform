package com.SocialMediaPlatform.ValueObjects;

public class Content {
    
    private String name;

    public Content(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Content content = (Content) obj;
        return name.equals(content.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
