package com.exadel.borsch.managers;

import com.exadel.borsch.dao.Order;

/**
 * @author Andrey Zhilka
 */
public interface MenuManager {
    void updateOrder(Order toUpdate);
    void deleteOrderById(String hashId);
    Order getOrderById(String hashId);
}
