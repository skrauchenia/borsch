package com.exadel.borsch.managers.simple;

import com.exadel.borsch.dao.MenuItem;
import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.OrderManager;
import com.exadel.borsch.util.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Vlad
 */
@Service
class SimpleOrderManager implements OrderManager {
    private List<Order> orders;

    public SimpleOrderManager() {
        // TODO read frome file
        orders = new ArrayList<>();
    }
    @Override
    public void updateOrder(Order toUpdate) {
        ListIterator<Order> iter = orders.listIterator();
        while (iter.hasNext()) {
            Order curOrder = iter.next();
            if (curOrder.getId().equals(toUpdate.getId())) {
                iter.set(toUpdate);
                return;
            }
        }

        orders.add(toUpdate);
    }

    @Override
    public void deleteOrderById(UUID id) {
        ListIterator<Order> iter = orders.listIterator();
        while (iter.hasNext()) {
            Order curOrder = iter.next();
            if (curOrder.getId().equals(id)) {
                iter.remove();
                return;
            }
        }
    }

    @Override
    public Order getOrderById(UUID id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public void addOrder(Order toAdd) {
        orders.add(toAdd);
    }

    @Override
    public List<Order> getAllOrders() {
        return Collections.unmodifiableList(orders);
    }

    @Override
    public List<Order> getOrdersForUser(User user) {
        List<Order> result = new ArrayList<>();
        for (Order order: orders) {
            if (order.getOwner().equals(user)) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public Order getCurrentOrderForUser(User user) {
        List<Order> userOrders = getOrdersForUser(user);
        if (userOrders.isEmpty()) {
            Order order = new Order();
            order.setOwner(user);
            order.setStartDate(DateTimeUtils.getStartOfNextWeek());
            order.setEndDate(order.getStartDate().plusDays(DateTimeUtils.WORKING_DAYS_IN_WEEK));
            for (int i = 0; i < DateTimeUtils.WORKING_DAYS_IN_WEEK; i++) {
                MenuItem item = new MenuItem();
                item.setDate(order.getStartDate().plusDays(i));
                order.addMenuItem(item);
            }
            addOrder(order);
            return order;
        }
        return userOrders.get(0);
    }
}
