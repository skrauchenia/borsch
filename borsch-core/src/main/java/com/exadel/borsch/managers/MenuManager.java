package com.exadel.borsch.managers;

import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.User;

import java.util.List;

/**
 * @author Andrey Zhilka
 */
public interface MenuManager {
    void updateOrder(Order toUpdate);
    void deleteOrderById(String hashId);
    Order getOrderById(String hashId);
    void addOrder(Order toAdd);
    List<Order> getAllOrders();
    List<Order> getOrdersForUser(User user);
}
