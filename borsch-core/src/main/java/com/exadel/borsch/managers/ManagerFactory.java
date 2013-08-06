package com.exadel.borsch.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 * @author Vlad
 */
@Scope(value = "singleton")
public final class ManagerFactory {

    @Autowired
    private UserManager userManager;
    @Autowired
    private static OrderManager orderManager;
    @Autowired
    private static PriceManager priceManager;

    public UserManager getUserManager() {
        return userManager;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public PriceManager getPriceManager() {
        return priceManager;
    }
}