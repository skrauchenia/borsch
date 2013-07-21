package com.exadel.borsch.managers;

import com.exadel.borsch.dao.PriceList;

/**
 * @author Andrey Zhilka
 */
public interface PriceManager {
    PriceList getPriceListById(String hashId);
    void deletePriceListById(String hashId);
    void addPriceList(PriceList toAdd);
    void updatePriceList(PriceList toUpdate);
}
