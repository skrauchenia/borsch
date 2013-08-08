
package com.exadel.borsch.web.controllers;

import com.exadel.borsch.dao.MenuItem;
import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.OrderManager;
import com.exadel.borsch.managers.UserManager;
import com.exadel.borsch.util.DateTimeUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
@Controller
public class ReportController {

    @Autowired
    private ManagerFactory managerFactory;

    @Secured("ROLE_PRINT_ORDER")
    @ResponseBody
    @RequestMapping(value = "/report/setPaid/{orderId}/{menuId}", method = RequestMethod.POST)
    public void processAjaxRequest(@PathVariable String orderId, @PathVariable String menuId) {
        OrderManager orderManager = managerFactory.getOrderManager();
        Order order = orderManager.getOrderById(UUID.fromString(orderId));
        MenuItem menuItem = order.getMenuById(UUID.fromString(menuId));
        menuItem.setIsPaid(true);
        orderManager.updateOrder(order);
    }

    @Secured("ROLE_PRINT_ORDER")
    @RequestMapping("/report")
    public String processPageRequest(ModelMap model) {
        OrderManager orderManager = managerFactory.getOrderManager();
        ListMultimap<Integer, DailyOrder> report = ArrayListMultimap.create();
        List<Order> allOrders;

        allOrders = orderManager.getAllOrders();

        for (Order order : allOrders) {
            for (MenuItem item : order.getOrder()) {
                if (item.getChoices().isEmpty()) {
                    continue;
                }
                DailyOrder daySummary = DailyOrder.mapOrderAndItemToDailyOrder(item, order);
                report.put(item.getDate().getDayOfWeek() - 1, daySummary);
            }
        }

        List<List<DailyOrder>> reportFinalVersion = new ArrayList<>();

        for (int i = 0; i < DateTimeUtils.WORKING_DAYS_IN_WEEK; i++) {
            reportFinalVersion.add(report.get(i));
        }

        model.addAttribute("report", reportFinalVersion);
        model.addAttribute("workingDays", DateTimeUtils.WORKING_DAYS_IN_WEEK);

        return ViewURLs.WEEK_ORDER_REPORT;
    }
    @Secured("ROLE_PRINT_ORDER")
    @RequestMapping("/orderTable")
    public String loadOrderTable(ModelMap model) {
        OrderManager orderManager = managerFactory.getOrderManager();
        UserManager userManager = managerFactory.getUserManager();
        List<User> users = userManager.getAllUsers();
        ListMultimap<User, MenuItem> orders = ArrayListMultimap.create();
        List<String> date = new ArrayList<>();
        Map<Integer, Integer> totalPrice = new TreeMap<>();
        boolean flag = true;
        for (User user : users) {
            List<MenuItem> items = orderManager.getCurrentOrderForUser(user).getOrder();
            for (MenuItem item : items) {
                if (flag) {
                    date.add(item.getDate().dayOfMonth().get() + "."
                            + item.getDate().monthOfYear().get() + "."
                            + item.getDate().year().get());
                }
                int day = item.getDate().dayOfMonth().get();
                if (!totalPrice.containsKey(day)) {
                    totalPrice.put(day, item.getTotalPrice());
                } else {
                    totalPrice.put(day, totalPrice.get(day) + item.getTotalPrice());
                }
                orders.put(user, item);
            }
            flag = false;
        }
        model.addAttribute("date", date);
        model.addAttribute("orders", orders.asMap());
        model.addAttribute("totalPrice", totalPrice);
        return ViewURLs.ORDER_TABLE;
    }

    public static class DailyOrder {
        private Integer weekDay;
        private User user;
        private MenuItem menuItem;
        private Integer total;
        private UUID weekOrderId;


        public Integer getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(Integer weekDay) {
            this.weekDay = weekDay;
        }

        public UUID getWeekOrderId() {
            return weekOrderId;
        }

        public void setWeekOrderId(UUID weekOrderId) {
            this.weekOrderId = weekOrderId;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public void setMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
        }

        public static DailyOrder mapOrderAndItemToDailyOrder(MenuItem item, Order order) {
            DailyOrder daySummary = new DailyOrder();
            daySummary.setMenuItem(item);
            daySummary.setUser(order.getOwner());
            daySummary.setWeekDay(item.getDate().getDayOfWeek());
            daySummary.setTotal(item.getTotalPrice());
            daySummary.setWeekOrderId(order.getId());
            return daySummary;
        }
    }
}
