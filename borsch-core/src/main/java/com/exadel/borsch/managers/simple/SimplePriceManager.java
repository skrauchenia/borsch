package com.exadel.borsch.managers.simple;

import com.exadel.borsch.dao.Course;
import com.exadel.borsch.dao.Dish;
import com.exadel.borsch.dao.PriceList;
import com.exadel.borsch.managers.PriceManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Vlad
 */
@Service
@Scope(value = "singleton")
public class SimplePriceManager implements PriceManager {
    private List<PriceList> prices;
    public SimplePriceManager() {
        // TODO read from file
        prices = new ArrayList<>();

        // Test data
        PriceList list = new PriceList();
        //CHECKSTYLE:OFF
        Object[][] listData = {
                {Course.DESSERT, "Торт", "Отличный старый добрый торт! Прямо из пекарни! (столовая)", "cake.jpg.to", 59600},
                {Course.DESSERT, "Блинчики с джемом", "Очень вкусные, и в отличие от блинчиков с ветчиной - не содержат кошек!", "pancakes.jpg.to", 10000},
                {Course.FIRST_COURSE, "Боорщъ", "Просто борщ", "borsch.jpg.to", 7950},
                {Course.FIRST_COURSE, "Суп", "Это не борщ. Не заказывайте.", "soup.jpg.to", 7000},
                {Course.SECOND_COURSE, "Макароны", "Длинные такие, твердые.", "spaghetti.jpg.to", 3700},
                {Course.SECOND_COURSE, "Картофель фри", "Mega edition. Специально от нашей столовой! Почти как в макдональдсе!", "potatoes.jpg.to", 4200},
                {Course.SECOND_COURSE, "Драники", "Настоящие белорусские! Без пояснений", "draniki.jpg.to", 5400},
                {Course.SECOND_COURSE, "Котлеты", "Из них делают блины. В том числе.", "http://котлет.jpg.to/", 19000}
        };
        for (int i = 0; i < listData.length; i++) {
            Dish dish = new Dish();
            dish.setCourse((Course) listData[i][0]);
            dish.setName((String) listData[i][1]);
            dish.setDescription((String) listData[i][2]);
            dish.setPhotoUrl((String) listData[i][3]);
            dish.setPrice((int) listData[i][4]);
            list.addDish(dish);
        }
        //CHECKSTYLE:ON

        // Some empty list
        prices.add(new PriceList());
        // Some useful list
        prices.add(list);
    }
    @Override
    public PriceList getPriceListById(UUID id) {
        for (PriceList priceList : prices) {
            if (priceList.getId().equals(id)) {
                return priceList;
            }
        }
        return null;
    }

    @Override
    public void deletePriceListById(UUID id) {
        ListIterator<PriceList> iter = prices.listIterator();
        while (iter.hasNext()) {
            PriceList curPriceList = iter.next();
            if (curPriceList.getId().equals(id)) {
                iter.remove();
                return;
            }
        }
    }

    @Override
    public void addPriceList(PriceList toAdd) {
        prices.add(toAdd);
    }

    @Override
    public void updatePriceList(PriceList toUpdate) {
        ListIterator<PriceList> iter = prices.listIterator();
        while (iter.hasNext()) {
            PriceList curPriceList = iter.next();
            if (curPriceList.getId().equals(toUpdate.getId())) {
                iter.set(toUpdate);
                return;
            }
        }

        prices.add(toUpdate);
    }

    @Override
    public List<PriceList> getAllPriceLists() {
        return Collections.unmodifiableList(prices);
    }

    @Override
    public PriceList getCurrentPriceList() {
        if (prices.isEmpty()) {
            return null;
        }
        return prices.get(prices.size() - 1);
    }
}
