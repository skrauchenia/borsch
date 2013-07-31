/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.web.controllers;

import com.exadel.borsch.dao.Course;
import com.exadel.borsch.dao.Dish;
import com.exadel.borsch.dao.PriceList;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.PriceManager;
import com.exadel.borsch.managers.simple.SimpleManagerFactory;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vlad
 */
@Controller
public class MenuController {

    @RequestMapping("/menu")
    public ModelAndView processPageRequest() {
        ModelAndView mav = new ModelAndView(ViewURLs.MENU_PAGE);
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            Map<Course, List<Dish>> courses = dishes.getCourses();
            List<Dish> firstCourse = courses.get(Course.FIRST_COURSE);
            List<Dish> secondCourse = courses.get(Course.SECOND_COURSE);
            List<Dish> dessert = courses.get(Course.DESSERT);
            mav.addObject("firstCourse", firstCourse);
            mav.addObject("secondCourse", secondCourse);
            mav.addObject("dessert", dessert);
        }
        return mav;
    }
    @RequestMapping("/edit/dish/add/{course}")
    public String processAddPageRequest(@PathVariable String course, ModelMap model) {
        model.addAttribute("course", course);
        model.addAttribute("action", "add");
        return ViewURLs.DISH_ADD_PAGE;
    }
    @ResponseBody
    @RequestMapping(value = "/edit/dish/add/save", method = RequestMethod.POST)
    public Dish processSaveDishRequest(ModelMap model, HttpServletRequest request) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");
        Dish dish = new Dish(name, price, description);
        Course course = null;
        switch (request.getParameter("course")) {
            case "FIRST_COURSE":
                course = Course.FIRST_COURSE;
                break;
            case "SECOND_COURSE":
                course = Course.SECOND_COURSE;
                break;
            case "DESSERT":
                course = Course.DESSERT;
                break;
            default:
                throw new AssertionError();
        }
        dish.setCourse(course);
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        PriceList dishes = null;
        if ((prices != null) && (!prices.isEmpty())) {
            dishes = prices.get(prices.size() - 1);
            dishes.addDish(dish);
            manager.updatePriceList(dishes);
        } else {
            dishes = new PriceList();
            dishes.addDish(dish);
            manager.addPriceList(dishes);
        }
        return dish;
    }
    @RequestMapping("/edit/dish/{id}/edit")
    public String processEditPageRequest(@PathVariable String id, ModelMap model) {
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            Dish dish = dishes.getDishById(UUID.fromString(id));
            model.addAttribute("id", id);
            model.addAttribute("name", dish.getName());
            model.addAttribute("price", dish.getPrice());
            model.addAttribute("course", dish.getCourse());
            model.addAttribute("description", dish.getDescription());
        }
        model.addAttribute("action", "edit");
        return ViewURLs.DISH_ADD_PAGE;
    }
    @ResponseBody
    @RequestMapping(value = "/edit/dish/edit/save", method = RequestMethod.POST)
    public Dish processUpdateDishRequest(ModelMap model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String id = request.getParameter("id");
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        Dish dish = null;
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            dish = dishes.getDishById(UUID.fromString(id));
            dish.setName(name);
            dish.setPrice(Integer.parseInt(price));
            dish.setDescription(description);
            dishes.updateDish(dish);
        }
        return dish;
    }
    @RequestMapping("/edit/dish/{id}/remove")
    public String processRemoveDishRequest(@PathVariable String id, ModelMap model) {
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            Dish forRemove = dishes.getDishById(UUID.fromString(id));
            dishes.removeDish(forRemove);
        }
        return ViewURLs.MENU_PAGE;
    }
}
