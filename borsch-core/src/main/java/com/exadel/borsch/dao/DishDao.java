package com.exadel.borsch.dao;

import com.exadel.borsch.entity.Dish;

import java.util.List;

/**
 * @author Vlad
 */
public interface DishDao {
    Dish getById(Long id);
    void save(Dish dish);
    void delete(Long id);
    void update(Dish dish);
    List<Dish> getAllByMenuItemId(Long menuItemId);
    List<Dish> getAllByPriceListId(Long priceListId);
    void setPriceList(Long dishId, Long priceListId);
}
