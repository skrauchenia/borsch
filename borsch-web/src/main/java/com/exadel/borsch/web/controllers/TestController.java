/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.web.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zubr
 */
@Controller
public class TestController {
    private static final int MAGIC_NUMBER = 42;

    @RequestMapping("/")
    public String handleRoot(Model model, Principal user) {
        model.addAttribute("value", MAGIC_NUMBER);
        model.addAttribute("user", user);
        return "home";
    }
}
