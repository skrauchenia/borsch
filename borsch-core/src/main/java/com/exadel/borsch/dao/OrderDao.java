package com.exadel.borsch.dao;

import com.exadel.borsch.entity.Order;
import com.exadel.borsch.util.Entry;

import java.util.List;

/**
 * @author Vlad
 */
public interface OrderDao {
    Order getById(Long id);
    void save(Order order);
    void delete(Long id);
    void update(Order order);
    List<Order> getByOwnerId(Long ownerId);
    void setOwnerId(Long orderId, Long ownerId);
    List<Order> getAll();
    List<Entry> getUsersIdForOrdersId();
}
