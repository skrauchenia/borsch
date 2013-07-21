package com.exadel.borsch.notification;

import com.exadel.borsch.dao.User;

/**
 * @author zubr
 */
public class BrowserNotification extends Notification {
    private User target;

    public BrowserNotification(User target, String message) {
        super(message);
        this.target = target;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    @Override
    public void doNotify() {
        BrowserNotificationHolder.pushNotification(target, getMessage());
    }
}
