package com.exadel.borsch.entity;

import org.joda.time.DateTime;

/**
 * @author Andrew Zhilka
 */
public class OrderChange extends Identifiable {
    private String changedDishId;
    private String actedUserId;
    private String menuItemId;
    private DateTime dateOfChange;
    private ChangeAction committedAction;

    public DateTime getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(DateTime dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    public String getChangedDishId() {
        return changedDishId;
    }

    public void setChangedDishId(String changedDishId) {
        this.changedDishId = changedDishId;
    }

    public String getActedUserId() {
        return actedUserId;
    }

    public void setActedUserId(String actedUserId) {
        this.actedUserId = actedUserId;
    }

    public ChangeAction getCommittedAction() {
        return committedAction;
    }

    public void setCommittedAction(ChangeAction committedAction) {
        this.committedAction = committedAction;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }
}
