package com.exadel.borsch.dao;

import org.joda.time.DateTime;

/**
 * @author Andrew Zhilka
 */
public class OrderChange extends Identifiable {
    private String changedDishId;
    private String actedUserId;
    private String dishName;
    private String userName;
    private DateTime dateOfChange;
    private ChangeAction committedAction;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public DateTime getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(DateTime dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    public ChangeAction getCommittedAction() {
        return committedAction;
    }

    public void setCommittedAction(ChangeAction committedAction) {
        this.committedAction = committedAction;
    }
}
