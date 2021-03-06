package com.exadel.borsch.managers.impl.simple;

import com.exadel.borsch.entity.Dish;
import com.exadel.borsch.entity.MenuItem;
import com.exadel.borsch.entity.Order;
import com.exadel.borsch.entity.User;
import com.exadel.borsch.managers.OrderManager;
import com.exadel.borsch.util.DateTimeUtils;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Vlad
 */
@Service("simpleOrderManager")
@Scope(value = "singleton")
public class SimpleOrderManager implements OrderManager {
    private List<Order> orders;

    public SimpleOrderManager() {
        // TODO read frome file
        orders = new ArrayList<>();
    }

    @Override
    public void updateOrder(Order toUpdate, MenuItem menuItem) {
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
    public void deleteOrderById(Long id) {
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
    public Order getOrderById(Long id) {
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

    public List<Order> getAllOrders(DateTime startDate) {
        List<Order> appropriateOrders = new ArrayList<>();
        for (Order order : orders) {
            DateTime orderStartDate = order.getStartDate();
            if (DateTimeUtils.sameDates(startDate, order.getStartDate())) {
                appropriateOrders.add(order);
            }
        }

        return Collections.unmodifiableList(appropriateOrders);
    }

    @Override
    public List<Order> getOrdersForUser(User user) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getOwner().equals(user)) {
                result.add(order);
            }
        }
        return Collections.unmodifiableList(result);
    }

    @Override
    public Order getCurrentOrderForUser(User user) {
        List<Order> userOrders = getOrdersForUser(user);
        if (userOrders.isEmpty()) {
            Order order = new Order();
            order.setOwner(user);
            order.setStartDate(DateTimeUtils.getStartOfNextWeek());
            order.setEndDate(order.getStartDate().plusDays(DateTimeUtils.WORKING_DAYS_IN_WEEK));
            fillOrderWithItems(order);
            addOrder(order);
            return order;
        }
        return userOrders.get(0);
    }

    @Override
    public void fillOrderWithItems(Order order) {
        for (int i = 0; i < DateTimeUtils.WORKING_DAYS_IN_WEEK; i++) {
            MenuItem item = new MenuItem();
            item.setDate(order.getStartDate().plusDays(i));
            order.addMenuItem(item);
        }
    }

    @Override
    public Order findOrderAtDateForUser(User user, DateTime date) {
        List<Order> userOrders = getOrdersForUser(user);
        date = DateTimeUtils.getStartOfWeek(date);
        for (Order order: userOrders) {
            if (DateTimeUtils.sameDates(order.getStartDate(), date)) {
                return order;
            }
        }
        return null;
    }


    @Override
    public void removeDishFormMenuItem(MenuItem menuItem, Dish dish) {
        // unused in this impl
    }

    @Override
    public void addDishFormMenuItem(MenuItem menuItem, Dish dish) {
        // unused in this impl
    }
}
