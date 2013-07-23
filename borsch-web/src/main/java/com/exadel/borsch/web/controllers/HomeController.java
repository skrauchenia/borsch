/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Vlad
 */
@Controller
public class HomeController {
    @RequestMapping("/home")
    public String processPageRequest() {
        return ViewURLs.HOME_PAGE;
    }
}
