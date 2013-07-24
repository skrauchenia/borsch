package com.exadel.borsch.dao;

import com.exadel.borsch.util.Encoder;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
public class Order {
    private UUID id = UUID.randomUUID();
    private List<MenuItem> order = new ArrayList<>();
    private DateTime startDate;
    private DateTime endDate;
    private User owner;

    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
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

    public MenuItem getMenuById(String id) {
        for (MenuItem menu : order) {
            if (menu.getHash().equals(id)) {
                return menu;
            }
        }
        return new MenuItem();
    }

    public String getHash() {
        return Encoder.encodeWithMD5(owner.getHash(),
                startDate.toString() + endDate.toString());
    }

    public boolean discardMenuItems(List<MenuItem> toDiscard) {
        return order.removeAll(toDiscard);
    }

    public boolean addMenuItems(List<MenuItem> toAdd) {
        return order.addAll(toAdd);
    }
}
