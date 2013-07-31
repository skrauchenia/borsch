package com.exadel.borsch.dao;

import com.exadel.borsch.util.Encoder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
public class PriceList {
    private UUID id = UUID.randomUUID();
    private List<Dish> dishes = new ArrayList<>();
    private DateTime creationTime;
    private DateTime expirationTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Dish getDishById(UUID id) {
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
    public String getHash() {
        StringBuilder forHash = new StringBuilder();
        for (Dish dish : dishes) {
            forHash.append(dish.getHashId());
        }

        return Encoder.encodeWithMD5(expirationTime.toString() + creationTime.toString(),
                        forHash.toString());
    }
    public Map<Course, List<Dish>> getCourses() {
        Map<Course, List<Dish>> courses = new HashMap<>();
        for (Dish dish : dishes) {
            Course course = dish.getCourse();
            if (!courses.containsKey(course)) {
                courses.put(course, new ArrayList<Dish>());
            }
            courses.get(course).add(dish);
        }
        return courses;
    }
    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof PriceList) {
            return new EqualsBuilder()
                    .append(creationTime, ((PriceList) toCompare).getCreationTime())
                    .append(expirationTime, ((PriceList) toCompare).getExpirationTime())
                    .append(this.getHash(), ((PriceList) toCompare).getHash())
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(42, 17)
                .append(this.getHash())
                .toHashCode();
    }
}