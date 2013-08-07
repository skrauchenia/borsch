package com.exadel.borsch.managers;

import com.exadel.borsch.dao.User;

import java.util.List;

/**
 * @author Andrey Zhilka
 */
public interface UserManager {
    User getUserById(Long userId);
    User getUserByLogin(String login);
    void deleteUserById(Long userId);
    void updateUser(User toUpdate);
    void addUser(User toAdd);
    void addUsers(List<User> toAdd);
    List<User> getAllUsers();
}
