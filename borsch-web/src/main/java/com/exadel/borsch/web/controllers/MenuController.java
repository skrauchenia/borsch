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
    @RequestMapping("/add/dish")
    public String processAddPageRequest(ModelMap model) {
        return ViewURLs.DISH_ADD_PAGE;
    }
    @RequestMapping("/add/dish/save")
    public String processSaveDishRequest(ModelMap model, HttpServletRequest request) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");
        Course course  = Course.valueOf(request.getParameter("course"));
        Dish dish = new Dish(name, price, description);
        dish.setCourse(course);
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            dishes.getDishes().add(dish);
        }
        return ViewURLs.MENU_PAGE;
    }
    @RequestMapping("/edit/dish/{id}")
    public String processEditPageRequest(@PathVariable String id, ModelMap model) {
//        model.addAttribute("id", id);
//        model.addAttribute("name", "name");
//        model.addAttribute("price", "price");
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            Dish dish = dishes.getDishById(UUID.fromString(id));
            model.addAttribute("name", dish.getName());
            model.addAttribute("price", dish.getPrice());
            model.addAttribute("course", dish.getCourse());
            model.addAttribute("description", dish.getDescription());
        }
        return ViewURLs.DISH_EDIT_PAGE;
    }

    @ResponseBody
    @RequestMapping("/edit/dish/{id}/save")
    public String processUpdateDishRequest(@PathVariable String id,
            ModelMap model, HttpServletRequest request) {
//        System.out.println(request.getParameter("name"));
//        System.out.println(request.getParameter("price"));
//        System.out.println(request.getParameter("course"));
//        model.addAttribute("name", "WORK!!!!1");
//        String json = "{\"name\":\"asd\"}";
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String course = request.getParameter("course");
        String description = request.getParameter("description");
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            Dish dish = dishes.getDishById(UUID.fromString(id));
            dish.setName(name);
            dish.setPrice(Integer.parseInt(price));
            dish.setDescription(description);
            dish.setCourse(Course.valueOf(course));
        }
        return ViewURLs.MENU_PAGE;
    }
    @RequestMapping("/edit/dish/{id}/remove")
    public String processRemoveDishRequest(@PathVariable String id, ModelMap model) {
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            //TODO : remove dish
//            dishes.getDishes(
            System.out.println(dishes.getId());
        }
        return ViewURLs.MENU_PAGE;
    }
}
