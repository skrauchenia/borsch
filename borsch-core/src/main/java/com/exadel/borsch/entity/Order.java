package com.exadel.borsch.entity;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Andrey Zhilka
 */
public class Order {
    private Long id;
    private List<MenuItem> order = new ArrayList<>();
    private DateTime startDate;
    private DateTime endDate;
    private User owner;

    public Order() {
        super();
    }

    public Order(Long id, DateTime startDate, DateTime endDate) {
        this.setId(id);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<MenuItem> getOrder() {
        return Collections.unmodifiableList(order);
    }

    public MenuItem getMenuById(Long id) {
        for (MenuItem menu : order) {
            if (menu.getId().equals(id)) {
                return menu;
            }
        }
        return new MenuItem();
    }

    public void addMenuItem(MenuItem item) {
        order.add(item);
    }

    public void discardMenuItem(MenuItem item) {
        order.remove(item);
    }

    public boolean discardMenuItems(List<MenuItem> toDiscard) {
        return order.removeAll(toDiscard);
    }

    public boolean addMenuItems(List<MenuItem> toAdd) {
        return order.addAll(toAdd);
    }

}
