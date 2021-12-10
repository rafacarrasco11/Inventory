package com.example.inventory.data.model;

public final class Event {
    public static final int onLoginError = 0;
    public static final int onSingUpError = 0;
    public static final int onSingUpSuccess = 0;
    public static final int onLoginSuccess = 0;

    private int eventType;
    private String message;

    public int getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
