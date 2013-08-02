package com.exadel.borsch.web.controllers;

import com.exadel.borsch.dao.Dish;
import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.OrderManager;
import com.exadel.borsch.web.users.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
        OrderManager orderManager = managerFactory.getOrderManager();
        User user = UserUtils.getUserByPrincipal(principal);

        Order order = orderManager.getCurrentOrderForUser(user);
        model.addAttribute(order);
        if (order.getOrder().get(0).getChoices().isEmpty()) {
            Dish testDish = managerFactory.getPriceManager().getCurrentPriceList().getDishes().get(0);
            order.getOrder().get(0).addDish(testDish);
        }

        return ViewURLs.HOME_PAGE;
    }

    @ResponseBody
    @RequestMapping("/home/orders/{day}")
    public List<Dish> processOrderRequest(Principal principal, @PathVariable int day) {

        OrderManager orderManager = managerFactory.getOrderManager();
        User user = UserUtils.getUserByPrincipal(principal);

        Order order = orderManager.getCurrentOrderForUser(user);
        return order.getOrder().get(day).getChoices();
    }

    @ResponseBody
    @RequestMapping("/home/orders/{day}/remove/{itemIdx}")
    public void processOrderItemRemove(Principal principal, @PathVariable int day,
        @PathVariable int itemIdx) {

        OrderManager orderManager = managerFactory.getOrderManager();
        User user = UserUtils.getUserByPrincipal(principal);

        Order order = orderManager.getCurrentOrderForUser(user);
        order.getOrder().get(day).removeDishByIndex(itemIdx);
    }
}
