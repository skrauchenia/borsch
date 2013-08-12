package com.exadel.borsch.entity;

import org.joda.time.DateTime;

/**
 * @author Andrew Zhilka
 */
public class OrderChange {
    private Long id;
    private Long changedDishId;
    private Long actedUserId;
    private Long menuItemId;
    private DateTime dateOfChange;
    private ChangeAction committedAction;

    public OrderChange(Long changeId, Long changedDishId, Long actedUserId,
                Long menuItemId, DateTime date, ChangeAction action) {
        this.setId(changeId);
        this.changedDishId = changedDishId;
        this.actedUserId = actedUserId;
        this.menuItemId = menuItemId;
        this.dateOfChange = date;
        this.committedAction = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(DateTime dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    public Long getChangedDishId() {
        return changedDishId;
    }

    public void setChangedDishId(Long changedDishId) {
        this.changedDishId = changedDishId;
    }

    public Long getActedUserId() {
        return actedUserId;
    }

    public void setActedUserId(Long actedUserId) {
        this.actedUserId = actedUserId;
    }

    public ChangeAction getCommittedAction() {
        return committedAction;
    }

    public void setCommittedAction(ChangeAction committedAction) {
        this.committedAction = committedAction;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }
}
