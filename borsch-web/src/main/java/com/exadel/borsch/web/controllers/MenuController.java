package com.exadel.borsch.web.controllers;

import com.exadel.borsch.dao.Course;
import com.exadel.borsch.dao.Dish;
import com.exadel.borsch.dao.PriceList;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.PriceManager;
import com.exadel.borsch.managers.simple.SimpleManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author Vlad
 */
@Controller
public class MenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private ManagerFactory factory;

    @RequestMapping("/menu")
    public ModelAndView processPageRequest() {
        ModelAndView mav = new ModelAndView(ViewURLs.MENU_PAGE);
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
//            ListMultimap<Course, Dish> courses = dishes.getCourses();
              mav.addObject("courseList", dishes.getCourses().asMap());
//            mav.addObject("firstCourse", courses.get(Course.FIRST_COURSE));
//            mav.addObject("secondCourse", courses.get(Course.SECOND_COURSE));
//            mav.addObject("dessert", courses.get(Course.SECOND_COURSE));
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
    public DishJSON processSaveDishRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");
        String course = request.getParameter("course");
        Dish dish = new Dish(name, price, Course.getCourse(course), description);
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        PriceList dishes = null;
        if ((prices != null) && (!prices.isEmpty())) {
            dishes = prices.get(prices.size() - 1);
        } else {
            dishes = new PriceList();
        }
        dishes.addDish(dish);
        //manager.updatePriceList(dishes);
        return DishJSON.mapDishToJSON(dish);
    }

    @RequestMapping("/edit/dish/{id}/edit")
    public String processEditPageRequest(@PathVariable String id, ModelMap model) {
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            Dish dish = dishes.getDishById(UUID.fromString(id));
            model.addAttribute("dish", dish);
        }
        model.addAttribute("action", "edit");
        return ViewURLs.DISH_ADD_PAGE;
    }
    @ResponseBody
    @RequestMapping(value = "/edit/dish/edit/save", method = RequestMethod.POST)
    public DishJSON processUpdateDishRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String id = request.getParameter("id");
        ManagerFactory factory = new SimpleManagerFactory();
        PriceManager manager = factory.getPriceManager();
        List<PriceList> prices = manager.getAllPriceLists();
        Dish dish = new Dish();
        if ((prices != null) && (!prices.isEmpty())) {
            PriceList dishes = prices.get(prices.size() - 1);
            dish = dishes.getDishById(UUID.fromString(id));
            dish.setName(name);
            dish.setPrice(Integer.parseInt(price));
            dish.setDescription(description);
            dishes.updateDish(dish);
        }
        return DishJSON.mapDishToJSON(dish);
    }
    @RequestMapping("/edit/dish/{id}/remove")
    public String processRemoveDishRequest(@PathVariable String id) {
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

    public static class DishJSON {
        private String id;
        private String name;
        private Integer price;
        private String description;
        private String index;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public static DishJSON mapDishToJSON(Dish dish) {
            DishJSON json = new DishJSON();

            json.setName(dish.getName());
            json.setId(dish.getId().toString());
            json.setDescription(dish.getDescription());
            json.setPrice(dish.getPrice());
            json.setIndex(String.valueOf(Course.getIndex(dish.getCourse())));
            return json;
        }
    }
}
