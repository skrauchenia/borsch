package com.exadel.borsch.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Vlad
 */
@Service
public final class ManagerFactory {

    @Autowired
    @Qualifier("jdbcUserManager")
    private UserManager userManager;
    @Autowired
    @Qualifier("simpleOrderManager")
    private OrderManager orderManager;
    @Autowired
    @Qualifier("jdbcPriceManager")
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