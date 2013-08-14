package com.exadel.borsch.checker;

import com.exadel.borsch.entity.OrderChange;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.OrderChangeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Andrew Zhilka
 */
@Service
public class OrderChangeChecker extends Checker {
    @Autowired
    private ManagerFactory managerFactory;

    @Override
    @Scheduled(cron = "0 0 */2 * * *")
    public void runPeriodCheck() {
        OrderChangeManager changeManager = managerFactory.getChangeManager();

        for (OrderChange change : OrderChangesHolder.returnChanges()) {
            changeManager.addNewChange(change);
        }

        OrderChangesHolder.resetHolder();
    }

    @Scheduled(cron = "0 0 * * 1 *")
    public void resetOldChanges() {
        OrderChangeManager changeManager = managerFactory.getChangeManager();

        changeManager.resetOldChanges();
    }
}
