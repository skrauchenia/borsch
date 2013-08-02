package com.exadel.borsch.notifier;

import com.exadel.borsch.notification.Notification;

/**
 * @author zubr
 */
public abstract class NotifierTask {
    private Notification notification;

    public NotifierTask(Notification notification) {
        this.notification = notification;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public abstract void runPeriodicCheck();

}
