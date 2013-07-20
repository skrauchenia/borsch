package com.exadel.borsch.managers;

import com.exadel.borsch.dao.PriceList;

/**
 * @author Andrey Zhilka
 */
public interface PriceManager {
    public PriceList getPriceListById(String hashId);
    public void deletePriceListById(String hashId);
    public void addPriceList(PriceList toAdd);
    public void updatePriceList(PriceList toUpdate);
}
