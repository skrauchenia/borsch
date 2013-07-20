package com.exadel.borsch.dao;

import com.exadel.borsch.util.Encoder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Dish {
    private String name;
    private String photoUrl;
    private Integer price;
    private String description;
    private String hashId;

    Dish() {
        updateHash();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateHash();
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        updateHash();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updateHash();
    }

    private void updateHash() {
        hashId = Encoder.encodeWithMD5(name + description, price.toString());
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof Dish) {
            return this.getHashId().equals(((Dish) toCompare).getHashId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(42, 7)
                .append(name)
                .append(price)
                .append(description)
                .toHashCode();
    }
}
