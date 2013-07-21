package com.exadel.borsch.notification;

import com.exadel.borsch.dao.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zubr
 */
public final class BrowserNotificationHolder {
    private static Map<User, Stack<String>> notifications = new HashMap<>();

    private BrowserNotificationHolder() {
    }

    public static void pushNotification(User target, String message) {
        if (!notifications.containsKey(target)) {
            notifications.put(target, new Stack<String>());
        }
        notifications.get(target).push(message);
    }

    public static String popNotification(User target) {
        Stack<String> stack = notifications.get(target);
        if (stack == null || stack.size() == 0) {
           return null;
        }
        return stack.pop();
    }
}
