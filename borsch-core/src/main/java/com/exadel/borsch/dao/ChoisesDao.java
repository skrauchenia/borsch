package com.exadel.borsch.dao;

import java.util.List;

/**
 * @author Vlad
 */
public interface ChoisesDao {
    void save(Long menuItemId, Long dishId);
    void delete(Long dishId);
}
