package com.exadel.borsch.web.controllers;

import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 *
 * @author Vlad
 */
@Controller
public class UsersController {

    @Autowired
    private ManagerFactory managerFactory;
    @RequestMapping("/users")
    public String processPageRequest() {
        return ViewURLs.USERS_PAGE;
    }
    @RequestMapping("/edit/user/{userId}")
    public String processEditPageRequest(@PathVariable String userId, ModelMap model) {
        UserManager userManager = managerFactory.getUserManager();
        User user = userManager.getUserById(UUID.fromString(userId));
        model.addAttribute("user", user);
        return ViewURLs.USER_EDIT_PAGE;
    }

    @RequestMapping("/edit/user/{userId}/edit")
    public String processUpdateUserRequest(@PathVariable String userId, @RequestParam("name") String name,
                                         @RequestParam("eMail") String email) {
        UserManager userManager = managerFactory.getUserManager();
        User user = userManager.getUserById(UUID.fromString(userId));
        user.setName(name);
        user.setEmail(email);
        userManager.updateUser(user);

        return ViewURLs.USER_EDIT_PAGE;
    }
}
