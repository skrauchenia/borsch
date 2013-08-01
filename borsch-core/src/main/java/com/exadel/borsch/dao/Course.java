/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.dao;

/**
 *
 * @author Fedor
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
