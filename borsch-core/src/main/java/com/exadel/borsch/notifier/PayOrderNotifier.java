package com.exadel.borsch.notifier;

import com.exadel.borsch.dao.MenuItem;
import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.OrderManager;
import com.exadel.borsch.managers.UserManager;
import com.exadel.borsch.notification.BrowserNotification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Andrew Zhilka
 */
public class PayOrderNotifier extends NotifierTask {
    @Autowired
    private ManagerFactory managerFactory;

    public PayOrderNotifier() {
        super(new BrowserNotification("Check Your orders. Some of them are not paid"));
    }

    @Override
    public void runPeriodicCheck() {
        UserManager userManager = managerFactory.getUserManager();
        OrderManager orderManager = managerFactory.getOrderManager();
        List<User> targetUsers = new LinkedList<>();

        for (User user : userManager.getAllUsers()) {
            Order weekOrder = orderManager.getCurrentOrderForUser(user);

            boolean isOrderPaid = true;
            for (MenuItem item : weekOrder.getOrder()) {
                if (!item.getIsPaid()) {
                    isOrderPaid = false;
                }
            }

            if (!isOrderPaid) {
                targetUsers.add(user);
            }
        }

        this.getNotification().submit(targetUsers);
    }
}
