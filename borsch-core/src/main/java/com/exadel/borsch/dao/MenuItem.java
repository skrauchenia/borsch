package com.exadel.borsch.dao;

import com.exadel.borsch.util.Encoder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
public class MenuItem {
    private UUID id = UUID.randomUUID();
    private DateTime date;
    private List<Dish> choices = new ArrayList<>();
    private boolean isPaid = false;

    public Integer getTotalPrice() {
        Integer total = 0;
        for (Dish dish : choices) {
            total += dish.getPrice();
        }

        return total;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean status) {
        this.isPaid = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public List<Dish> getChoices() {
        return Collections.unmodifiableList(choices);
    }

    public boolean cancelChoices(List<Dish> forCancel) {
        return choices.removeAll(forCancel);
    }

    public boolean addChoices(List<Dish> toAdd) {
        return choices.addAll(toAdd);
    }

    public String getHash() {
        StringBuilder toHash = new StringBuilder();
        for (Dish choice : choices) {
            toHash.append(choice.getHashId());
        }
        return Encoder.encodeWithMD5(toHash.toString(), date.toString());
    }

    @Override
    public boolean equals(Object menuItem) {
        if (menuItem instanceof MenuItem) {
            return new EqualsBuilder()
                    .append(date, ((MenuItem) menuItem).getDate())
                    .append(this.getHash(), ((MenuItem) menuItem).getHash())
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(42, 6)
                .append(date)
                .append(this.getHash())
                .toHashCode();
    }
}
