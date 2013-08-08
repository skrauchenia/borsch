package com.exadel.borsch.notifier;

import com.exadel.borsch.entity.MenuItem;
import com.exadel.borsch.entity.Order;
import com.exadel.borsch.entity.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.OrderManager;
import com.exadel.borsch.managers.UserManager;
import com.exadel.borsch.notification.BrowserNotification;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Andrew Zhilka
 */
public class PayOrderNotifier extends NotifierTask {
    @Autowired
    private ManagerFactory managerFactory;

    public PayOrderNotifier() {
        super(new BrowserNotification());
    }

    @Override
    public void runPeriodicCheck() {
        UserManager userManager = managerFactory.getUserManager();
        OrderManager orderManager = managerFactory.getOrderManager();

        for (User user : userManager.getAllUsers()) {
            Order weekOrder = orderManager.getCurrentOrderForUser(user);

            boolean isOrderPaid = true;
            for (MenuItem item : weekOrder.getOrder()) {
                if (!item.getIsPaid()) {
                    isOrderPaid = false;
                }
            }

            if (!isOrderPaid) {
                this.getNotification().setMessage(extractMessage("user.notification.payment", user.getLocale()));
                this.getNotification().submit(user);
            }
        }
    }
}
