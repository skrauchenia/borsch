package com.exadel.borsch.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Simple index page controller
 */
@Controller
public class IndexController {
    private static final int THE_ANSWER = 42;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView handleRequest() {
        ModelAndView model = new ModelAndView("home");
        model.addObject("value", THE_ANSWER);
        return model;
    }

}
