package com.exadel.borsch.web.users;

import com.exadel.borsch.entity.User;
import com.exadel.borsch.managers.ManagerFactory;
import com.exadel.borsch.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import java.util.Collection;

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
        User user = daoManager.getUserByLogin(userName);

        if (user == null) {
            user = new User();
            user.setLogin(userName);
            user.setName(dco.getStringAttribute("cn"));
            user.setEmail(userName + "@exadel.com");

            daoManager.addUser(user);
        }

        return new BorschUserDetails(user);
    }

    @Override
    public void mapUserToContext(UserDetails ud, DirContextAdapter dca) {
        //unused
    }

}
