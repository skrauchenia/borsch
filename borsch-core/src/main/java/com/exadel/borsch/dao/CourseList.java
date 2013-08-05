/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.dao;

import com.google.common.collect.ListMultimap;

import java.util.List;

/**
 *
 * @author Fedor
 */
public class CourseList {
    private List<Dish> firstCourse;
    private List<Dish> secondCourse;
    private List<Dish> dessert;
    public CourseList(PriceList list) {
        ListMultimap<Course, Dish> courses = list.getCourses();
        this.firstCourse = courses.get(Course.FIRST_COURSE);
        this.secondCourse = courses.get(Course.SECOND_COURSE);
        this.dessert = courses.get(Course.DESSERT);
    }

    public List<Dish> getFirstCourse() {
        return firstCourse;
    }

    public List<Dish> getSecondCourse() {
        return secondCourse;
    }

    public List<Dish> getDessert() {
        return dessert;
    }
}
