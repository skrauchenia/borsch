package com.exadel.borsch.dao;

import com.exadel.borsch.entity.User;

import java.util.List;

/**
 * @author Vlad
 */
public interface UserDao {
    User getUserById(Long userId);
    User getUserByLogin(String login);
    void delete(Long userId);
    void update(User user);
    void save(User user);
    List<User> getAllUsers();
}
