package com.exadel.borsch.dao;

import com.google.common.collect.Sets;

import java.util.Set;

public enum AccessRight {
    ROLE_EDIT_MENU_SELF("ROLE_EDIT_MENU_SELF"),
    ROLE_EDIT_MENU_OTHER("ROLE_EDIT_MENU_OTHER"),
    ROLE_EDIT_PRICE("ROLE_EDIT_PRICE"),
    ROLE_PRINT_ORDER("ROLE_PRINT_ORDER"),
    ROLE_EDIT_PROFILE("ROLE_EDIT_PROFILE");

    private String name;

    AccessRight(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Set<String> getAllRightsToString() {
        Set<String> rights = Sets.newHashSet();
        for (AccessRight accessRight : AccessRight.values()) {
            rights.add(accessRight.getName());
        }
        return rights;
    }
}
