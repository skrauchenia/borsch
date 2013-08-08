package com.exadel.borsch.notification;

import com.exadel.borsch.entity.User;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zubr
 */
public final class BrowserNotificationHolder {
    private static ListMultimap<User, String> notifications = ArrayListMultimap.create();
    private static Map<User, Integer> numberOfWatchedNotifications = new HashMap<>();

    private BrowserNotificationHolder() {
    }

    public static void pushNotification(User target, String message) {
        notifications.put(target, message);
    }

    public static String getOldestNotification(User target) {
        return notifications.get(target).remove(0);   //get and remove oldest notification
    }

    public static List<String> popNotifications(User target) {
        List<String> list = notifications.get(target);
        notifications.removeAll(target);
        return list;
    }

    public static List<String> getNotificationsForUser(User target) {
        List<String> currentNotifications = notifications.get(target);
        numberOfWatchedNotifications.put(target, currentNotifications.size());
        return currentNotifications;
    }

    public static boolean hasUserNewNotifications(User target) {
        int watchedNotifications = numberOfWatchedNotifications.get(target);
        int currentNotification = notifications.get(target).size();

        return watchedNotifications == currentNotification;
    }
}
