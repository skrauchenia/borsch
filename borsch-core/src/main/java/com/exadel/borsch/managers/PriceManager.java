package com.exadel.borsch.managers;

import com.exadel.borsch.entity.Dish;
import com.exadel.borsch.entity.PriceList;
import org.joda.time.DateTime;

import java.util.List;

/**
 * @author Andrey Zhilka
 */
public interface PriceManager {
    PriceList getPriceListById(Long id);
    PriceList getCurrentPriceList();
    PriceList getPriceListByCreationTime(DateTime time);
    void deletePriceListById(Long id);
    void addPriceList(PriceList toAdd);
    void updatePriceList(PriceList toUpdate);
    void addDishToPriceList(Dish dish, PriceList priceList);
    void updateDishInPriceList(Dish dish, PriceList priceList);
    void removeDishFromPriceList(Dish dish, PriceList priceList);
    List<PriceList> getAllPriceLists();
}
