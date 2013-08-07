package com.exadel.borsch.web.controllers;

import com.exadel.borsch.entiry.Dish;
import com.exadel.borsch.entiry.MenuItem;
import com.exadel.borsch.entiry.Order;
import com.exadel.borsch.entiry.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.OrderManager;
import com.exadel.borsch.util.DateTimeUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    public void processAjaxRequest(@PathVariable Long orderId, @PathVariable Long menuId) {
        OrderManager orderManager = managerFactory.getOrderManager();
        Order order = orderManager.getOrderById(orderId);
        MenuItem menuItem = order.getMenuById(menuId);
        menuItem.setIsPaid(true);
        orderManager.updateOrder(order);
    }

    @Secured("ROLE_PRINT_ORDER")
    @RequestMapping("/report")
    public String processPageRequest(ModelMap model) {
        OrderManager orderManager = managerFactory.getOrderManager();
        ListMultimap<Integer, DailyOrder> report = ArrayListMultimap.create();
        List<Order> allOrders;

        DateTime startOfWeek = DateTimeUtils.getStartOfNextWeek();
        allOrders = orderManager.getAllOrders(startOfWeek);

        for (Order order : allOrders) {
            for (MenuItem item : order.getOrder()) {
                if (item.getChoices().isEmpty()) {
                    continue;
                }
                DailyOrder daySummary = DailyOrder.mapOrderAndItemToDailyOrder(item, order);
                report.put(item.getDate().getDayOfWeek() - 1, daySummary);
            }
        }

        List<List<DailyOrder>> reportFinalVersion = new ArrayList<List<DailyOrder>>();

        for (int i = 0; i < DateTimeUtils.WORKING_DAYS_IN_WEEK; i++) {
            reportFinalVersion.add(report.get(i));
        }

        model.addAttribute("report", reportFinalVersion);
        model.addAttribute("workingDays", DateTimeUtils.WORKING_DAYS_IN_WEEK);

        return ViewURLs.WEEK_ORDER_REPORT;
    }

    @Secured("ROLE_PRINT_ORDER")
    @RequestMapping("/report/summary")
    public String processSummaryRequest(ModelMap model) {
        OrderManager orderManager = managerFactory.getOrderManager();
        int today = DateTime.now().getDayOfWeek();
        Map<Integer, Map<String, Integer>> summary = new TreeMap<>();
        for (int i = today; i <= DateTimeUtils.WORKING_DAYS_IN_WEEK; i++) {
            summary.put(i, new HashMap<String, Integer>());
        }
        DateTime startOfWeek = DateTimeUtils.getStartOfCurrentWeek();
        for (Order order : orderManager.getAllOrders(startOfWeek)) {
            for (MenuItem item : order.getOrder()) {
                if (item.getDate().isBeforeNow()) {
                    continue; // do not show past orders
                }
                Integer dayOfWeek = item.getDate().getDayOfWeek();
                Map<String, Integer> dayOrders = summary.get(dayOfWeek);
                for (Dish dish : item.getChoices()) {
                    String dishName = dish.getName();
                    if (!dayOrders.containsKey(dishName)) {
                        dayOrders.put(dishName, 1);
                    } else {
                        dayOrders.put(dishName, dayOrders.get(dishName) + 1);
                    }
                }
            }
        }

        model.addAttribute("summary", summary);

        return ViewURLs.ORDERS_SUMMARY_PAGE;
    }

    public static class DailyOrder {
        private Integer weekDay;
        private User user;
        private MenuItem menuItem;
        private Integer total;
        private Long weekOrderId;


        public Integer getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(Integer weekDay) {
            this.weekDay = weekDay;
        }

        public Long getWeekOrderId() {
            return weekOrderId;
        }

        public void setWeekOrderId(Long weekOrderId) {
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
