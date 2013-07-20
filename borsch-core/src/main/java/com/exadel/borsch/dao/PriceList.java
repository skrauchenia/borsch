package com.exadel.borsch.dao;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Andrey Zhilka
 */
public class PriceList {
    private List<Dish> dishes = new ArrayList<>();
    private DateTime creationTime;

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    public List<Dish> getDishes() {
        return Collections.unmodifiableList(dishes);
    }

    public boolean addDishes(List<Dish> toAdd) {
        return dishes.addAll(toAdd);
    }

    public boolean removeDishes(List<Dish> toRemove) {
        return dishes.removeAll(toRemove);
    }
}
