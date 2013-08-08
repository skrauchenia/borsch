package com.exadel.borsch.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class Dish extends Identifiable {
    private String name;
    private String photoUrl;
    private Integer price;
    private String description;
    private Course course;

    public Dish() {
        super();
    }
    public Dish(String name, Integer price, Course course, String description) {
        this.name = name;
        this.price = price;
        this.course = course;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    public Course getCourse() {
        return this.course;
    }

    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof Dish) {
            return new EqualsBuilder()
                    .append(this.getId(), ((Dish) toCompare).getId())
                    .append(this.name, ((Dish) toCompare).getName())
                    .append((int) this.price, ((Dish) toCompare).getPrice())
                    .append(this.course, ((Dish) toCompare).getCourse())
                    .append(this.photoUrl, ((Dish) toCompare).getPhotoUrl())
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(42, 7)
                .append(this.getId())
                .append(name)
                .append(price)
                .append(description)
                .toHashCode();
    }
}