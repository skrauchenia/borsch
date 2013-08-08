package com.exadel.borsch.managers;

import com.exadel.borsch.entity.PriceList;

import java.util.List;

/**
 * @author Andrey Zhilka
 */
public interface PriceManager {
    PriceList getPriceListById(Long id);
    PriceList getCurrentPriceList();
    void deletePriceListById(Long id);
    void addPriceList(PriceList toAdd);
    void updatePriceList(PriceList toUpdate);
    List<PriceList> getAllPriceLists();
}
