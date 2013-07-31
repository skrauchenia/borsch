package com.exadel.borsch.managers.simple;

import com.exadel.borsch.dao.AccessRight;
import com.exadel.borsch.dao.Course;
import com.exadel.borsch.dao.Dish;
import com.exadel.borsch.dao.Order;
import com.exadel.borsch.dao.PriceList;
import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.MenuManager;
import com.exadel.borsch.managers.PriceManager;
import com.exadel.borsch.managers.UserManager;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author Vlad
 */
@Service
public class SimpleManagerFactory implements ManagerFactory {
    private static SimpleUserManager simpleUserManager = null;
    private static SimpleMenuManager simpleMenuManager = null;
    private static SimplePriceManager simplePriceManager = null;
    @Override
    public UserManager getUserManager() {
        if (simpleUserManager == null) {
            simpleUserManager = new SimpleUserManager();
        }
        return simpleUserManager;
    }

    @Override
    public MenuManager getMenuManager() {
        if (simpleMenuManager == null) {
            simpleMenuManager = new SimpleMenuManager();
        }
        return simpleMenuManager;
    }

    @Override
    public PriceManager getPriceManager() {
        if (simplePriceManager == null) {
            simplePriceManager = new SimplePriceManager();
        }
        return simplePriceManager;
    }

    private static class SimpleUserManager implements UserManager {
        private List<User> users;

        public SimpleUserManager() {
            // TODO read frome file
            users = new ArrayList<>();

            // Test data
            User admin = new User();
            admin.setName("Administrator The Great");
            admin.setLogin("admin");
            admin.setEmail("borschmail@gmail.com");
            admin.addAccessRights(Arrays.asList(AccessRight.values()));
            users.add(admin);
        }
        @Override
        public User getUserById(UUID userId) {
            for (User user : users) {
                if (user.getId().equals(userId)) {
                    return user;
                }
            }
            return null;
        }

        @Override
        public User getUserByLogin(String login) {
            for (User user : users) {
                if (user.getLogin().equals(login)) {
                    return user;
                }
            }
            return null;
        }


        @Override
        public void deleteUserById(UUID userId) {
            ListIterator<User> iter = users.listIterator();
            while (iter.hasNext()) {
                User curUser = iter.next();
                if (curUser.getId().equals(userId)) {
                    iter.remove();
                    return;
                }
            }
        }

        @Override
        public void updateUser(User toUpdate) {
            ListIterator<User> iter = users.listIterator();
            while (iter.hasNext()) {
                User curUser = iter.next();
                if (curUser.getId().equals(toUpdate.getId())) {
                    iter.set(toUpdate);
                    return;
                }
            }
        }

        @Override
        public void addUsers(List<User> toAdd) {
            users.addAll(toAdd);
        }

        @Override
        public List<User> getAllUsers() {
            return Collections.unmodifiableList(users);
        }
    }
    private static class SimpleMenuManager implements MenuManager {
        private List<Order> orders;

        public SimpleMenuManager() {
            // TODO read frome file
            orders = new ArrayList<>();
        }
        @Override
        public void updateOrder(Order toUpdate) {
            ListIterator<Order> iter = orders.listIterator();
            while (iter.hasNext()) {
                Order curOrder = iter.next();
                if (curOrder.getId().equals(toUpdate.getId())) {
                    iter.set(toUpdate);
                    return;
                }
            }

            orders.add(toUpdate);
        }

        @Override
        public void deleteOrderById(UUID id) {
            ListIterator<Order> iter = orders.listIterator();
            while (iter.hasNext()) {
                Order curOrder = iter.next();
                if (curOrder.getId().equals(id)) {
                    iter.remove();
                    return;
                }
            }
        }

        @Override
        public Order getOrderById(UUID id) {
            for (Order order : orders) {
                if (order.getId().equals(id)) {
                    return order;
                }
            }
            return null;
        }

        @Override
        public void addOrder(Order toAdd) {
            orders.add(toAdd);
        }

        @Override
        public List<Order> getAllOrders() {
            return Collections.unmodifiableList(orders);
        }

        @Override
        public List<Order> getOrdersForUser(User user) {
            List<Order> result = new ArrayList<>();
            for (Order order: orders) {
                if (order.getOwner().equals(user)) {
                    result.add(order);
                }
            }
            return result;
        }
    }
    private static class SimplePriceManager implements PriceManager {
        private List<PriceList> prices;
        SimplePriceManager() {
            // TODO read from file
            prices = new ArrayList<>();

            // Test data
            PriceList list = new PriceList();
            //CHECKSTYLE:OFF
            Object[][] listData = {
                {Course.DESSERT, "Торт", "Отличный старый добрый торт! Прямо из пекарни! (столовая)", "cake.jpg.to", 59600},
                {Course.DESSERT, "Блинчики с джемом", "Очень вкусные, и в отличие от блинчиков с ветчиной - не содержат кошек!", "pancakes.jpg.to", 10000},
                {Course.FIRST_COURSE, "Боорщъ", "Просто борщ", "borsch.jpg.to", 7950},
                {Course.FIRST_COURSE, "Суп", "Это не борщ. Не заказывайте.", "soup.jpg.to", 7000},
                {Course.SECOND_COURSE, "Макароны", "Длинные такие, твердые.", "spaghetti.jpg.to", 3700},
                {Course.SECOND_COURSE, "Картофель фри", "Mega edition. Специально от нашей столовой! Почти как в макдональдсе!", "potatoes.jpg.to", 4200},
                {Course.SECOND_COURSE, "Драники", "Настоящие белорусские! Без пояснений", "draniki.jpg.to", 5400},
                {Course.SECOND_COURSE, "Котлеты", "Из них делают блины. В том числе.", "http://котлет.jpg.to/", 19000}
            };
            for (int i = 0; i < listData.length; i++) {
                Dish dish = new Dish();
                dish.setCourse((Course) listData[i][0]);
                dish.setName((String) listData[i][1]);
                dish.setDescription((String) listData[i][2]);
                dish.setPhotoUrl((String) listData[i][3]);
                dish.setPrice((int) listData[i][4]);
                list.addDish(dish);
            }
            //CHECKSTYLE:ON

            // Some empty list
            prices.add(new PriceList());
            // Some useful list
            prices.add(list);
        }
        @Override
        public PriceList getPriceListById(UUID id) {
            for (PriceList priceList : prices) {
                if (priceList.getId().equals(id)) {
                    return priceList;
                }
            }
            return null;
        }

        @Override
        public void deletePriceListById(UUID id) {
            ListIterator<PriceList> iter = prices.listIterator();
            while (iter.hasNext()) {
                PriceList curPriceList = iter.next();
                if (curPriceList.getId().equals(id)) {
                    iter.remove();
                    return;
                }
            }
        }

        @Override
        public void addPriceList(PriceList toAdd) {
            prices.add(toAdd);
        }

        @Override
        public void updatePriceList(PriceList toUpdate) {
            ListIterator<PriceList> iter = prices.listIterator();
            while (iter.hasNext()) {
                PriceList curPriceList = iter.next();
                if (curPriceList.getId().equals(toUpdate.getId())) {
                    iter.set(toUpdate);
                    return;
                }
            }

            prices.add(toUpdate);
        }

        @Override
        public List<PriceList> getAllPriceLists() {
            return Collections.unmodifiableList(prices);
        }
    }
}