package com.exadel.borsch.entity;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import java.util.*;

/**
 * @author Andrey Zhilka
 */
public class PriceList extends Identifiable {
    private List<Dish> dishes = new ArrayList<>();
    private DateTime creationTime;
    private DateTime expirationTime;

    public PriceList() {
        super();
    }

    public DateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(DateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Dish getDishById(Long id) {
        for (Dish dish : dishes) {
            if (dish.getId().equals(id)) {
                return dish;
            }
        }

        return null;
    }

    public List<Dish> getDishes() {
        return Collections.unmodifiableList(dishes);
    }

    public boolean addDish(Dish dish) {
        return dishes.add(dish);
    }

    public boolean addDishes(List<Dish> toAdd) {
        return dishes.addAll(toAdd);
    }

    public boolean removeDishes(List<Dish> toRemove) {
        return dishes.removeAll(toRemove);
    }

    public boolean removeDish(Dish dish) {
        return dishes.remove(dish);
    }

    public void updateDish(Dish dish) {
        ListIterator<Dish> iter = dishes.listIterator();
        while (iter.hasNext()) {
            Dish curDish = iter.next();
            if (curDish.getId().equals(dish.getId())) {
                iter.set(dish);
                return;
            }
        }
        dishes.add(dish);
    }

    public ListMultimap<Course, Dish> getCourses() {
        ListMultimap<Course, Dish> courses = ArrayListMultimap.create();
        for (Dish dish : dishes) {
            courses.put(dish.getCourse(), dish);
        }
        return courses;
    }
    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof PriceList) {
            return new EqualsBuilder()
                    .append(this.getId(), ((PriceList) toCompare).getId())
                    .append(creationTime, ((PriceList) toCompare).getCreationTime())
                    .append(expirationTime, ((PriceList) toCompare).getExpirationTime())
                    .append(this.dishes, ((PriceList) toCompare).getDishes())
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(42, 17)
                .append(this.getId())
                .append(this.dishes)
                .append(this.creationTime)
                .append(this.expirationTime)
                .toHashCode();
    }
}