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
    @Qualifier("jdbcOrderManager")
    private OrderManager orderManager;
    @Autowired
    @Qualifier("jdbcPriceManager")
    private PriceManager priceManager;
    @Autowired
    @Qualifier("simpleOrderChangeManager")
    private OrderChangeManager changeManager;

    public UserManager getUserManager() {
        return userManager;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public PriceManager getPriceManager() {
        return priceManager;
    }

    public OrderChangeManager getChangeManager() {
        return changeManager;
    }
}