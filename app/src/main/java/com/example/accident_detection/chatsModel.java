package com.example.accident_detection;

public class chatsModel {
    String message;
    String sender;

    public chatsModel(String message, String sender) {
        this.message = message;
        this.sender = sender;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
