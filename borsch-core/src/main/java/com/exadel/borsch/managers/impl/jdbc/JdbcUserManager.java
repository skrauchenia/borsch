package com.exadel.borsch.managers.impl.jdbc;

import com.exadel.borsch.dao.UserDao;
import com.exadel.borsch.entity.AccessRight;
import com.exadel.borsch.entity.User;
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
