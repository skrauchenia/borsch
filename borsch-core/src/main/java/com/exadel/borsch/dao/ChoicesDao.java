package com.exadel.borsch.dao;

/**
 * @author Vlad
 */
public interface ChoicesDao {
    void save(Long menuItemId, Long dishId);
    void delete(Long dishId);
    void deleteAllByMenuItemId(Long menuItemId);
}
