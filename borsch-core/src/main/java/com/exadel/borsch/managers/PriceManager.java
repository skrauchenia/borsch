package com.exadel.borsch.managers;

import com.exadel.borsch.dao.PriceList;

import java.util.List;

/**
 * @author Andrey Zhilka
 */
public interface PriceManager {
    PriceList getPriceListById(String hashId);
    void deletePriceListById(String hashId);
    void addPriceList(PriceList toAdd);
    void updatePriceList(PriceList toUpdate);
    List<PriceList> getAllPriceLists();
}
