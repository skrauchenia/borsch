package com.exadel.borsch.dao;

/**
 * @author Andrew Zhilka
 */
public enum ChangeAction {
    ADDED_NEW_DISH("Added"),
    REMOVED_DISH("Deleted");

    private String name;

    ChangeAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
