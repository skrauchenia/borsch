package com.exadel.borsch.notifier;

import com.exadel.borsch.entity.AccessRight;
import com.exadel.borsch.entity.PriceList;
import com.exadel.borsch.entity.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.notification.EmailNotification;
import com.exadel.borsch.notification.Notification;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zubr
 */
public class UpdatePriceListNotifier extends NotifierTask {
    @Autowired
    private ManagerFactory managerFactory;

    public UpdatePriceListNotifier(Notification notification) {
        super(new EmailNotification());
    }

    @Override
    @Scheduled(cron = "0 0 * * * 4")
    public void runPeriodicCheck() {
        PriceList priceList = managerFactory.getPriceManager().getCurrentPriceList();
        if (priceList.getExpirationTime().isAfter(DateTime.now())) {
            List<User> editors = managerFactory.getUserManager().getAllUsers(AccessRight.ROLE_EDIT_PRICE);
            for (User user: editors) {
                this.getNotification().setMessage(extractMessage("user.notification.order", user.getLocale()));
                this.getNotification().submit(user);
            }
        }
    }

}
