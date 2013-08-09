package com.exadel.borsch.web.controllers;

import com.exadel.borsch.entity.Dish;
import com.exadel.borsch.entity.MenuItem;
import com.exadel.borsch.entity.Order;
import com.exadel.borsch.entity.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.OrderManager;
import com.exadel.borsch.managers.PriceManager;
import com.exadel.borsch.web.users.UserUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

/**
 *
 * @author Vlad
 */
@Controller
public class HomeController {
    @Autowired
    private ManagerFactory managerFactory;

    @Secured("ROLE_EDIT_MENU_SELF")
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

    @Secured("ROLE_EDIT_MENU_SELF")
    @RequestMapping("/")
    public String processDefaultPageRequest(Model model, Principal principal) {
        return "redirect:/home";
    }

    @ResponseBody
    @Secured("ROLE_EDIT_MENU_SELF")
    @RequestMapping("/home/orders/{day}")
    public List<Dish> processOrderRequest(Principal principal, @PathVariable int day) {

        OrderManager orderManager = managerFactory.getOrderManager();
        User user = UserUtils.getUserByPrincipal(principal);

        Order order = orderManager.getCurrentOrderForUser(user);
        if (day < 0 || day > order.getOrder().size()) {
            return null;
        }

        return order.getOrder().get(day).getChoices();
    }

    @ResponseBody
    @Secured("ROLE_EDIT_MENU_SELF")
    @RequestMapping("/home/orders/{date}/{itemId}")
    public OrderResult processOrderModification(Principal principal, @PathVariable String date,
        @PathVariable Long itemId) {

        PriceManager priceManager = managerFactory.getPriceManager();
        OrderManager orderManager = managerFactory.getOrderManager();
        User user = UserUtils.getUserByPrincipal(principal);


        Dish dish = priceManager.getCurrentPriceList().getDishById(itemId);
        DateTime orderDate = DateTime.parse(date, DateTimeFormat.forPattern("dd-MM-yyy"));
        Order order = orderManager.findOrderAtDateForUser(user, orderDate);
        int day = orderDate.getDayOfWeek() - 1;
        if (dish == null || order == null || day >= order.getOrder().size()) {
            return new OrderResult("fail", null);
        }

        MenuItem menuItem = order.getOrder().get(day);
        if (menuItem.getChoices().contains(dish)) {
            menuItem.removeDish(dish);
            return new OrderResult("removed", dish);
        }

        menuItem.addDish(dish);
        return new OrderResult("added", dish);
    }

    public static class OrderResult {
        private String status;
        private Dish dish;

        public OrderResult(String status, Dish dish) {
            this.status = status;
            this.dish = dish;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Dish getDish() {
            return dish;
        }

        public void setDish(Dish dish) {
            this.dish = dish;
        }

    }
}
