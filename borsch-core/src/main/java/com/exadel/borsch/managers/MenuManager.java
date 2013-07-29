package com.exadel.borsch.managers;

import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.User;

import java.util.List;
import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
public interface MenuManager {
    void updateOrder(Order toUpdate);
    void deleteOrderById(String hashId);
    Order getOrderById(UUID id);
    void addOrder(Order toAdd);
    List<Order> getAllOrders();
    List<Order> getOrdersForUser(User user);
}
