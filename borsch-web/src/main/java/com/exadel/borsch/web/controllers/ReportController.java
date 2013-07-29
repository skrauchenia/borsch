package com.exadel.borsch.web.controllers;

import com.exadel.borsch.dao.MenuItem;
import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.MenuManager;
import com.exadel.borsch.util.DateTimeUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
@Controller
public class ReportController {

    @Autowired
    private ManagerFactory managerFactory;

    @ResponseBody
    @RequestMapping("/report/setPaid/{orderId}/{menuId}")
    public AjaxResponse processAjaxRequest(@PathVariable String orderId, @PathVariable String menuId) {
        MenuManager menuManager = managerFactory.getMenuManager();
        AjaxResponse response = new AjaxResponse();

        Order order = menuManager.getOrderById(UUID.fromString(orderId));
        MenuItem menuItem = order.getMenuById(UUID.fromString(menuId));
        menuItem.setIsPaid(true);
        menuManager.updateOrder(order);
        response.setResponseSucceed(true);

        return response;
    }

    @RequestMapping("/report")
    public String processPageRequest(ModelMap model) {
        MenuManager menuManager = managerFactory.getMenuManager();
        ListMultimap<Integer, DailyOrder> report = ArrayListMultimap.create();
        List<Order> allOrders;

        allOrders = menuManager.getAllOrders();

        for (Order order : allOrders) {
            order.sortOrderByWeekday();
            for (MenuItem item : order.getOrder()) {
                DailyOrder daySummary = new DailyOrder();
                daySummary.setMenuItem(item);
                daySummary.setUser(order.getOwner());
                daySummary.setTotal(item.getTotalPrice());
                daySummary.setWeekOrderId(order.getId());
                report.put(item.getDate().getDayOfWeek(), daySummary);
            }
        }

        List<List<DailyOrder>> reportFinalVersion = new ArrayList<List<DailyOrder>>();

        for (int i = 0; i < DateTimeUtils.WORKING_DAYS_IN_WEEK; i++) {
            reportFinalVersion.add(report.get(i));
        }

        model.addAttribute("report", reportFinalVersion);

        return ViewURLs.WEEK_ORDER_REPORT;
    }

    private static class AjaxResponse {

        private boolean responseSucceed = false;

        public boolean getResponesSucceed() {
            return this.responseSucceed;
        }

        public void setResponseSucceed(boolean status) {
            this.responseSucceed = status;
        }
    }

    private static class DailyOrder {

        private User user;
        private MenuItem menuItem;
        private Integer total;
        private UUID weekOrderId;

        private UUID getWeekOrderId() {
            return weekOrderId;
        }

        private void setWeekOrderId(UUID weekOrderId) {
            this.weekOrderId = weekOrderId;
        }

        private Integer getTotal() {
            return total;
        }

        private void setTotal(Integer total) {
            this.total = total;
        }

        private User getUser() {
            return user;
        }

        private void setUser(User user) {
            this.user = user;
        }

        private MenuItem getMenuItem() {
            return menuItem;
        }

        private void setMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
        }
    }
}
