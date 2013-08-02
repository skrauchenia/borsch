package com.exadel.borsch.managers;

/**
 * @author Vlad
 */
public interface ManagerFactory {
    UserManager getUserManager();
    OrderManager getOrderManager();
    PriceManager getPriceManager();
}