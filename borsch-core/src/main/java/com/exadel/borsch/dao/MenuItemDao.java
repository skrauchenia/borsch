package com.exadel.borsch.dao;

import com.exadel.borsch.entity.MenuItem;

import java.util.List;

/**
 * @author Vlad
 */
public interface MenuItemDao {
    MenuItem getById(Long id);
    void save(MenuItem menuItem);
    void delete(Long id);
    void update(MenuItem menuItem);
    List<MenuItem> getAllByOrderId(Long orderId);
    void setOrderId(Long menuItemId, Long orderId);
}
