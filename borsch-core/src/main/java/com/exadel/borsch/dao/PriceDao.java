package com.exadel.borsch.dao;

import com.exadel.borsch.entity.PriceList;

import java.util.List;

/**
 * @author Vlad
 */
public interface PriceDao {
    PriceList getById(Long id);
    void delete(Long id);
    void save(PriceList priceList);
    void update(PriceList priceList);
    List<PriceList> getAll();
}
