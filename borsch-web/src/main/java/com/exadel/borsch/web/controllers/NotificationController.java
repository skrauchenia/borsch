package com.exadel.borsch.web.controllers;

import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.notification.BrowserNotificationHolder;
import com.exadel.borsch.web.users.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author Andrew Zhilka
 */
@Controller
public class NotificationController {

    @Autowired
    private ManagerFactory managerFactory;

    @ResponseBody
    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    public String processAjaxRequest(Principal principal) {
        User user = UserUtils.getUserByPrincipal(principal);
        return BrowserNotificationHolder.getOldestNotification(user);
    }
}