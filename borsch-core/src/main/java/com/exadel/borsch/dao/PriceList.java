package com.exadel.borsch.dao;

import com.exadel.borsch.util.Encoder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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
    private DateTime expirationTime;

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

    public List<Dish> getDishes() {
        return Collections.unmodifiableList(dishes);
    }

    public boolean addDishes(List<Dish> toAdd) {
        return dishes.addAll(toAdd);
    }

    public boolean removeDishes(List<Dish> toRemove) {
        return dishes.removeAll(toRemove);
    }

    public String getHash() {
        StringBuilder forHash = new StringBuilder();
        for (Dish dish : dishes) {
            forHash.append(dish.getHashId());
        }

        return Encoder.encodeWithMD5(expirationTime.toString() + creationTime.toString(),
                        forHash.toString());
    }

    @Override
    public boolean equals(Object toCompare) {
        if(toCompare instanceof PriceList) {
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
