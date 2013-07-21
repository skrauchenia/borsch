package com.exadel.borsch.managers;

/**
 *
 * @author Vlad
 */
public interface ManagerFactory {
    UserManager getUserManager();
    MenuManager getMenuManager();
    PriceManager getPriceManager();
}