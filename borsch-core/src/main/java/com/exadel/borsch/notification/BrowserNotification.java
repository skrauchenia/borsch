package com.exadel.borsch.notification;

import com.exadel.borsch.entity.User;

/**
 * @author zubr
 */
public class BrowserNotification extends Notification {

    public BrowserNotification() {
    }

    public BrowserNotification(String message) {
        super(message);
    }

    @Override
    public void submit(User target) {
        BrowserNotificationHolder.pushNotification(target, getMessage());
    }
}
