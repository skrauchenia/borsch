package com.exadel.borsch.managers;

import com.exadel.borsch.dao.OrderChange;

import java.util.List;
import java.util.UUID;

/**
 * @author Andrew Zhilka
 */
public interface OrderChangeManager {
    List<OrderChange> getChangesForCurrentWeek();
    List<OrderChange> getActualChanges();
    void addNewChange(OrderChange newChange);
    void deleteChangeById(UUID id);
    void updateChange(OrderChange newChange);
    void resetChanges();
    void resetOldChanges();
}
