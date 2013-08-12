package com.exadel.borsch.managers.impl.jdbc;

import com.exadel.borsch.dao.DishDao;
import com.exadel.borsch.dao.MenuItemDao;
import com.exadel.borsch.dao.OrderChangeDao;
import com.exadel.borsch.dao.UserDao;
import com.exadel.borsch.entity.OrderChange;
import com.exadel.borsch.managers.OrderChangeManager;
import com.exadel.borsch.util.DateTimeUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Zhilka
 */
public class JdbcOrderChangeManager implements OrderChangeManager {
    @Autowired
    private MenuItemDao menuItemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DishDao dishDao;
    @Autowired
    private OrderChangeDao orderChangeDao;

    @Override
    public List<OrderChange> getChangesForCurrentWeek() {
        return orderChangeDao.getAllByWeekStart(DateTimeUtils.getStartOfCurrentWeek());
    }

    @Override
    public List<OrderChange> getActualChanges() {
        List<OrderChange> actualChanges = new ArrayList<>();
        DateTime startOfCurrentWeek = DateTimeUtils.getStartOfCurrentWeek();
        List<OrderChange> thisWeekChanges = orderChangeDao.getAllByWeekStart(startOfCurrentWeek);

        for (OrderChange change : thisWeekChanges) {
            if (!DateTimeUtils.isDateBefore(change.getDateOfChange(), startOfCurrentWeek)) {
                actualChanges.add(change);
            }
        }

        return actualChanges;
    }

    @Override
    public void addNewChange(OrderChange newChange) {
        orderChangeDao.save(newChange);
    }

    @Override
    public void deleteChangeById(Long id) {
        orderChangeDao.delete(id);
    }

    @Override
    public void updateChange(OrderChange newChange) {
        orderChangeDao.update(newChange);
    }

    @Override
    public void resetChanges() {
        orderChangeDao.truncateTable();
    }

    @Override
    public void resetOldChanges() {
        List<OrderChange> actualChanges = getActualChanges();
        resetChanges();
        for (OrderChange change : actualChanges) {
            orderChangeDao.save(change);
        }
    }
}
