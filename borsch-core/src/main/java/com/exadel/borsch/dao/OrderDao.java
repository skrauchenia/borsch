package com.exadel.borsch.dao;

import com.exadel.borsch.entity.Order;

/**
 * @author Vlad
 */
public interface OrderDao {
    void save(Order order);
    void delete(Long id);
    void update(Order order);
    Order getByOwnerId(Long ownerId);
}
