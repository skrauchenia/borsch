/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.web.users;

import com.exadel.borsch.dao.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author zubr
 */
public class BorschUserDetailsService implements UserDetailsService {
    private BorschUserDetails admin;

    public BorschUserDetailsService() {
        User user = new User();
        user.setName("admin");
        user.setPasswordHash("qwedde");
        admin = new BorschUserDetails(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.err.println("Spring requested user: " + userName);
        if (userName.equals("admin")) {
            return admin;
        }
        throw new UsernameNotFoundException(userName);
    }

}
