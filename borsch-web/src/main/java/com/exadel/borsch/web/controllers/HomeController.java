package com.exadel.borsch.web.controllers;

import com.exadel.borsch.dao.MenuItem;
import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.MenuManager;
import com.exadel.borsch.util.DateTimeUtils;
import com.exadel.borsch.web.users.UserUtils;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Vlad
 */
@Controller
public class HomeController {

    @Autowired
    private ManagerFactory managerFactory;

    @RequestMapping("/home")
    public String processPageRequest(Model model, Principal principal) {
        MenuManager menuManager = managerFactory.getMenuManager();
        User user = UserUtils.getUserByPrincipal(principal);

        List<Order> orders = menuManager.getOrdersForUser(user);
        Order order;
        if (!orders.isEmpty()) {
            order = orders.get(0);
        } else {
            order = new Order();
            order.setOwner(user);
            order.setStartDate(DateTimeUtils.getStartOfCurrentWeek());
            order.setEndDate(order.getStartDate().plusDays(DateTimeUtils.WORKING_DAYS_IN_WEEK));
            for (int i = 0; i < DateTimeUtils.WORKING_DAYS_IN_WEEK; i++) {
                order.addMenuItem(new MenuItem());
            }
            menuManager.addOrder(order);
        }

        model.addAttribute(order);

        return ViewURLs.HOME_PAGE;
    }
}
