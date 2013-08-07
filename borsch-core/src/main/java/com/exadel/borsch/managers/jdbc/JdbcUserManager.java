package com.exadel.borsch.managers.jdbc;

import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.UserManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = "singleton")
public class JdbcUserManager extends AbstractJdbc implements UserManager {

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
    public void addUsers(List<User> toAdd) {
      
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
