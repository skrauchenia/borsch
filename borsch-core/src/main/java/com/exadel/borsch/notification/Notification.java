package com.exadel.borsch.notification;

/**
 * @author zubr
 */
public abstract class Notification {
    private String message;

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract void doNotify();
}
