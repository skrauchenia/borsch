package com.exadel.borsch.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Vlad
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String processPageRequest() {
        return ViewURLs.LOGIN_PAGE;
    }
}
