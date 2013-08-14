package com.exadel.borsch.managers.impl.jdbc;

import com.exadel.borsch.dao.*;
import com.exadel.borsch.entity.*;
import com.exadel.borsch.managers.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MenuItemDao menuItemDao;

    @Autowired
    private ChoicesDao choicesDao;

    @Autowired
    private OrderChangeDao changesDao;

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserById(Long userId) {

        for (Order order : orderDao.getByOwnerId(userId)) {
            for (MenuItem menuItem : menuItemDao.getAllByOrderId(order.getId())) {
                choicesDao.deleteAllByMenuItemId(menuItem.getId());
                changesDao.deleteAllByMenuItemId(menuItem.getId());
            }
            menuItemDao.deleteAllByOrderId(order.getId());
        }

        orderDao.deleteAllByUserId(userId);

        userDao.delete(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User toUpdate) {
        userDao.update(toUpdate);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User toAdd) {
        userDao.save(toAdd);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<User> getAllUsers() {
        return Collections.unmodifiableList(userDao.getAllUsers());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<User> getAllUsers(AccessRight accessRight) {
        return userDao.getAllUsers(accessRight);
    }
}
