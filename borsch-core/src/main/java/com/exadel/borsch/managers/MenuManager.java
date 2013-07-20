package com.exadel.borsch.managers;

import com.exadel.borsch.dao.Order;

/**
 * @author Andrey Zhilka
 */
public interface MenuManager {
    public void updateOrder(Order toUpdate);
    public void deleteOrderById(String hashId);
    public Order getOrderById(String hashId);
}
