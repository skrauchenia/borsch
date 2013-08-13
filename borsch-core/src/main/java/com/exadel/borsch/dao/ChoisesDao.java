package com.exadel.borsch.dao;

/**
 * @author Vlad
 */
public interface ChoisesDao {
    void save(Long menuItemId, Long dishId);
    void delete(Long dishId);
}
