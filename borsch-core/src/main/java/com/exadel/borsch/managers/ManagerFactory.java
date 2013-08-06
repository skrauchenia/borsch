package com.exadel.borsch.managers;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vlad
 */
public final class ManagerFactory {

    @Autowired
    private UserManager userManager;
    @Autowired
    private OrderManager orderManager;
    @Autowired
    private PriceManager priceManager;

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