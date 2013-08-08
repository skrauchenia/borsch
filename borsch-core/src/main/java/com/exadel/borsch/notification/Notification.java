package com.exadel.borsch.notification;

import com.exadel.borsch.entity.User;

import java.util.List;

/**
 * @author zubr
 */
public abstract class Notification {
    private String message;

    public Notification() {
    }

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract void submit(User target);

    public void submit(List<User> targets) {
        for (User target: targets) {
            if (!(this instanceof EmailNotification)
                    || target.getNeedEmailNotification()) {
                submit(target);
            }
        }
    }
}
