package com.exadel.borsch.notifier;

import com.exadel.borsch.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * @author zubr
 */
public abstract class NotifierTask {
    private Notification notification;

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

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

    protected String extractMessage(String messageCode, Locale userLocale) {
//        ApplicationContext context
//                = new ClassPathXmlApplicationContext("applicationContext.xml");
//        return context.getMessage(messageCode, null, userLocale);
        return messageSource.getMessage(messageCode, null, userLocale);
    }

}
