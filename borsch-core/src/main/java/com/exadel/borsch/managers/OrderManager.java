package com.exadel.borsch.managers;

import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.User;
import org.joda.time.DateTime;

import java.util.List;

/**
 * @author Andrey Zhilka
 */
public interface OrderManager {
    void updateOrder(Order toUpdate);
    void deleteOrderById(Long id);
    Order getOrderById(Long id);
    void addOrder(Order toAdd);
    List<Order> getAllOrders();
    List<Order> getAllOrders(DateTime startDate);
    List<Order> getOrdersForUser(User user);
    Order getCurrentOrderForUser(User user);
}
