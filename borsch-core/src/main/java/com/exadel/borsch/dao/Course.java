package com.exadel.borsch.dao;

/**
 * @author Tima
 */
public enum Course {
    FIRST_COURSE("firstCourse"),
    SECOND_COURSE("secondCourse"),
    DESSERT("dessert");

    private String name;
    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
