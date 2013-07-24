package com.exadel.borsch.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Vlad
 */
@Controller
public class UsersController {
    @RequestMapping("/users")
    public String processPageRequest() {
        return ViewURLs.USERS_PAGE;
    }
    @RequestMapping("/edit/user/{userId}")
    public String processEditPageRequest() {
        
    }
}
