package com.exadel.borsch.checker;

import com.exadel.borsch.entity.ChangeAction;
import com.exadel.borsch.entity.OrderChange;
import com.exadel.borsch.util.DateTimeUtils;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Set;

/**
 * @author Andrew Zhilka
 */
public final class OrderChangesHolder {
    private static Set<OrderChange> orderChanges = Sets.newHashSet();

    private OrderChangesHolder() {
    }


    public static void addChange(OrderChange change) {
        for (OrderChange curChange : orderChanges) {
            if (DateTimeUtils.sameDates(curChange.getDateOfChange(), change.getDateOfChange())
                 && change.getActedUserId().equals(curChange.getActedUserId())
                 && change.getChangedDishId().equals(curChange.getChangedDishId())) {
                switch (change.getCommittedAction()) {
                    case ADDED_NEW_DISH :
                        if (curChange.getCommittedAction() == ChangeAction.REMOVED_DISH) {
                            orderChanges.remove(curChange);
                            return;
                        }
                        break;
                    case REMOVED_DISH :
                        if (curChange.getCommittedAction() == ChangeAction.ADDED_NEW_DISH) {
                            orderChanges.remove(curChange);
                            return;
                        }
                        break;
                    default:
                        continue;
                }
            }
        }

        orderChanges.add(change);
    }

    public static Set<OrderChange> returnChanges() {
        return Collections.unmodifiableSet(orderChanges);
    }

    public static void resetHolder() {
        orderChanges.clear();
    }
}
