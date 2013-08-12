package com.exadel.borsch.dao;

import com.exadel.borsch.entity.OrderChange;
import org.joda.time.DateTime;

import java.util.List;
/**
 * @author Andrew Zhilka
 */
public interface OrderChangeDao {
    OrderChange getById(Long id);
    void save(OrderChange change);
    void delete(Long id);
    void update(OrderChange change);
    List<OrderChange> getByUserAndMenuItemId(OrderChange change, Long userId, Long menuItemId);
    List<OrderChange> getAllByDate(DateTime date);
    List<OrderChange> getAllByWeekStart(DateTime date);
    List<OrderChange> getAllChangesByUser(Long userId);
    void truncateTable();
}
