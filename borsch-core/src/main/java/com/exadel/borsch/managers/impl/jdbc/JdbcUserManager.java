package com.exadel.borsch.managers.impl.jdbc;

import com.exadel.borsch.dao.UserDao;
import com.exadel.borsch.entiry.User;
import com.exadel.borsch.managers.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vlad
 */
@Service("jdbcUserManager")
@Scope("singleton")
public class JdbcUserManager implements UserManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserManager.class);

    @Autowired
    private UserDao userDao;


    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public void deleteUserById(Long userId) {

    }

    @Override
    public void updateUser(User toUpdate) {

    }

    @Override
    public void addUser(final User toAdd) {
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    private void createAdmin() {
    }
}
