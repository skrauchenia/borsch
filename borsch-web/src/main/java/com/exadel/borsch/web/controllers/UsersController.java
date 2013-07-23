/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author confroom915
 */
@Controller
public class UsersController {
    @RequestMapping("/users")
    public String processPageRequest() {
        return ViewURLs.USERS_PAGE;
    }
}
