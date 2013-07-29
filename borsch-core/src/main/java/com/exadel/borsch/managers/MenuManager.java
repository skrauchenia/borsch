package com.exadel.borsch.managers;

import com.exadel.borsch.dao.Order;

import java.util.List;
import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
public interface MenuManager {
    void updateOrder(Order toUpdate);
    void deleteOrderById(UUID id);
    Order getOrderById(UUID id);
    void addOrder(Order toAdd);
    List<Order> getAllOrders();
}
