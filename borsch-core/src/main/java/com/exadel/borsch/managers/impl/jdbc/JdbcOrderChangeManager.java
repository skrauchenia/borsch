package com.exadel.borsch.managers.impl.jdbc;

import com.exadel.borsch.dao.OrderChangeDao;
import com.exadel.borsch.entity.OrderChange;
import com.exadel.borsch.managers.OrderChangeManager;
import com.exadel.borsch.util.DateTimeUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Zhilka
 */
@Service("jdbcOrderChangeManager")
@Scope("singleton")
public class JdbcOrderChangeManager implements OrderChangeManager {

    @Autowired
    private OrderChangeDao orderChangeDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<OrderChange> getChangesForCurrentWeek() {
        return orderChangeDao.getAllByWeekStart(DateTimeUtils.getStartOfCurrentWeek());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NEVER)
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewChange(OrderChange newChange) {
        orderChangeDao.save(newChange);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteChangeById(Long id) {
        orderChangeDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateChange(OrderChange newChange) {
        orderChangeDao.update(newChange);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void resetChanges() {
        orderChangeDao.truncateTable();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void resetOldChanges() {
        List<OrderChange> actualChanges = getActualChanges();
        resetChanges();
        for (OrderChange change : actualChanges) {
            orderChangeDao.save(change);
        }
    }
}
