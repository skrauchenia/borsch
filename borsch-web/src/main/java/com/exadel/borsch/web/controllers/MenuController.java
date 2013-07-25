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
            PriceList dishes = prices.get(prices.size() - -1);
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

    @RequestMapping("/edit/dish/{id}/{name}/{price}")
    public String processEditPageRequest(@PathVariable String id,
            @PathVariable String name,
            @PathVariable String price,
            ModelMap model) {
        Dish dish = new Dish(name, Integer.parseInt(price), "asd");
        dish.setId(UUID.randomUUID());
        model.addAttribute("dish", dish);
        model.addAttribute("enumValues", Course.values());
        return ViewURLs.DISH_EDIT_PAGE;
    }
    @RequestMapping("/edit/dish/{id}/save")
    public String processUpdateDishRequest(@PathVariable String id, ModelMap model, HttpServletRequest request) {
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - -1);
            Dish dish = dishes.getDishById(UUID.fromString(id));
            dish.setName(request.getParameter("name"));
            dish.setPrice(Integer.parseInt(request.getParameter("price")));
        }
        return ViewURLs.MENU_PAGE;
    }
}
