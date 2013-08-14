package com.exadel.borsch.managers;

import com.exadel.borsch.entity.OrderChange;

import java.util.List;

/**
 * @author Andrew Zhilka
 */
public interface OrderChangeManager {
    List<OrderChange> getChangesForCurrentWeek();
    List<OrderChange> getActualChanges();
    void addNewChange(OrderChange newChange);
    void deleteChangeById(Long id);
    void updateChange(OrderChange newChange);
    void resetChanges();
    void resetOldChanges();
}
