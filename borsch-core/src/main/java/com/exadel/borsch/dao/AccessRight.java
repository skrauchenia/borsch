package com.exadel.borsch.dao;

import com.google.common.collect.Sets;

import java.util.Set;

public enum AccessRight {

    ROLE_EDIT_MENU_SELF,
    ROLE_EDIT_MENU_OTHER,
    ROLE_EDIT_PRICE,
    ROLE_PRINT_ORDER,
    ROLE_EDIT_PROFILE;

    public static Set<String> getAllRightsToString() {
        Set<String> rights = Sets.newHashSet();
        for (AccessRight accessRight : AccessRight.values()) {
            rights.add(accessRight.name());
        }
        return rights;
    }
}
