package com.exadel.borsch.dao;

import com.exadel.borsch.util.Encoder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Andrey Zhilka
 */
public class MenuItem {
    private DateTime date;
    private List<Dish> choices = new ArrayList<>();

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
