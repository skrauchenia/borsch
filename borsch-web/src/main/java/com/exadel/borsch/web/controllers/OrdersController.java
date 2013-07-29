package com.exadel.borsch.web.controllers;

import com.exadel.borsch.managers.MenuManager;
import com.exadel.borsch.managers.simple.SimpleManagerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Vlad
 */
@Controller
public class OrdersController {

    @RequestMapping("/orders")
    public String processPageRequest(Model model) {
        MenuManager manager = new SimpleManagerFactory().getMenuManager();
        model.addAttribute("orders", manager.getAllOrders());
        return ViewURLs.MENU_PAGE;
    }
}
