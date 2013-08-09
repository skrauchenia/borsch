package com.exadel.borsch.managers.impl.jdbc;

import com.exadel.borsch.entity.MenuItem;
import com.exadel.borsch.entity.Order;
import com.exadel.borsch.entity.User;
import com.exadel.borsch.managers.OrderManager;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vlad
 */
@Service("jdbcOrderManager")
@Scope("singleton")
public class JdbcOrderManager implements OrderManager {


    @Override
    public void updateOrder(Order toUpdate, MenuItem menuItem) {

    }

    @Override
    public void deleteOrderById(Long id) {

    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public void addOrder(Order toAdd) {

    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public List<Order> getAllOrders(DateTime startDate) {
        return null;
    }

    @Override
    public List<Order> getOrdersForUser(User user) {
        return null;
    }

    @Override
    public Order getCurrentOrderForUser(User user) {
        return null;
    }

    @Override
    public Order findOrderAtDateForUser(User user, DateTime date) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
