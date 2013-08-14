package com.exadel.borsch.notifier;

import com.exadel.borsch.entity.MenuItem;
import com.exadel.borsch.entity.Order;
import com.exadel.borsch.entity.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.OrderManager;
import com.exadel.borsch.managers.UserManager;
import com.exadel.borsch.notification.EmailNotification;
import com.exadel.borsch.util.DateTimeUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zubr
 */
@Service
public class MakeOrderNotifier extends NotifierTask {

    @Autowired
    private ManagerFactory managerFactory;

    public MakeOrderNotifier() {
        super(new EmailNotification());
    }

    private boolean isWeekOrderComplete(Order order, DateTime startOfWeek, DateTime endOfWeek) {
        boolean[] days = new boolean[DateTimeUtils.WORKING_DAYS_IN_WEEK];
        for (MenuItem item: order.getOrder()) {
            if (item.getDate().isAfter(startOfWeek) && item.getDate().isBefore(endOfWeek)) {
                days[item.getDate().getDayOfWeek() - 1] = true;
            }
        }
        for (int i = 0; i < days.length; i++) {
            if (!days[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Scheduled(cron = "0 0 * * 4 *")
    public void runPeriodicCheck() {
        UserManager userManager = managerFactory.getUserManager();
        OrderManager menuManager = managerFactory.getOrderManager();

        DateTime startOfWeek = DateTimeUtils.getStartOfNextWeek();
        DateTime endOfWeek = startOfWeek.plusDays(DateTimeUtils.WORKING_DAYS_IN_WEEK);
        List<User> checkedUsers = new ArrayList<>();

        String localizedMessage;

        for (Order order: menuManager.getAllOrders(startOfWeek)) {
            checkedUsers.add(order.getOwner());
            if (!isWeekOrderComplete(order, startOfWeek, endOfWeek)) {
                localizedMessage = extractMessage("user.notification.order", order.getOwner().getLocale());
                getNotification().setMessage(localizedMessage);
                getNotification().submit(order.getOwner());
            }
        }

        List<User> remainingUsers = new ArrayList<User>(userManager.getAllUsers());
        remainingUsers.removeAll(checkedUsers);
        for (User user : remainingUsers) {
            localizedMessage = extractMessage("user.notification.order", user.getLocale());
            getNotification().setMessage(localizedMessage);
            getNotification().submit(user);
        }
    }

}
