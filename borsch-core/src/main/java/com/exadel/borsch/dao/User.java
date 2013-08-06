package com.exadel.borsch.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Andrey Zhilka
 */
public class User extends Identifiable {

    public static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    private String login;
    private String name;
    private String email;
    private boolean needEmailNotification = true;
    private Set<AccessRight> accessRights = new HashSet<>();
    private Locale locale = new Locale("ru_RU");

    public User() {
        super();
        accessRights.add(AccessRight.ROLE_EDIT_MENU_SELF);
    }

    public boolean getNeedEmailNotification() {
        return needEmailNotification;
    }

    public void setNeedEmailNotification(boolean needEmailNotification) {
        this.needEmailNotification = needEmailNotification;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean addAccessRights(List<AccessRight> toAdd) {
        return accessRights.addAll(toAdd);
    }

    public boolean discardAccessRights(List<AccessRight> toDiscard) {
        return accessRights.removeAll(toDiscard);
    }

    public Set<AccessRight> getAccessRights() {
        return Collections.unmodifiableSet(accessRights);
    }

    public Set<String> getStringAccessRights() {
        Set<String> rights = new HashSet<>();
        for (AccessRight right : accessRights) {
            rights.add(right.name());
        }

        return rights;
    }

    public void setStringAccessRights(List<String> newAccessRights) {
        accessRights = new HashSet<>();
        for (String newRight : newAccessRights) {
            for (AccessRight right : AccessRight.values()) {
                if (right.name().equals(newRight)) {
                    accessRights.add(right);
                    break;
                }
            }
        }
    }

    public boolean hasAccessRight(AccessRight accessRight) {
        return accessRights.contains(accessRight);
    }
}
