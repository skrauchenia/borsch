/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.web.users;

import java.util.Collection;

import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

/**
 * @author zubr
 */
public class BorschUserDetailsContextMapper implements UserDetailsContextMapper {

    @Autowired
    private ManagerFactory managerFactory;
    @Override
    public UserDetails mapUserFromContext(DirContextOperations dco, String userName,
                                          Collection<? extends GrantedAuthority> authority) {
        UserManager daoManager = managerFactory.getUserManager();
        User user = daoManager.getUserByName(userName);

        if (user == null) {
            user = new User();
        }

        return new BorschUserDetails(user);
    }

    @Override
    public void mapUserToContext(UserDetails ud, DirContextAdapter dca) {
        //unused
    }

}
