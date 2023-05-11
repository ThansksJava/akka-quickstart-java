package com.learn1.example9;

/**
 * @author Feng Jie
 * @date 2023/5/9 17:43
 */
public class Event {
    private String type;
    private String message;

    public Event(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
