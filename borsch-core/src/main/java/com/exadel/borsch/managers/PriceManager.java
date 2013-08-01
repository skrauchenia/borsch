package com.exadel.borsch.managers;

import com.exadel.borsch.dao.PriceList;

import java.util.List;
import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
public interface PriceManager {
    PriceList getPriceListById(UUID id);
    PriceList getCurrentPriceList();
    void deletePriceListById(UUID id);
    void addPriceList(PriceList toAdd);
    void updatePriceList(PriceList toUpdate);
    List<PriceList> getAllPriceLists();
}
