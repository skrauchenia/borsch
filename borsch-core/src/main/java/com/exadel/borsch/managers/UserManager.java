package com.exadel.borsch.managers;

import com.exadel.borsch.dao.User;

import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
public interface UserManager {
    public User getUserById(UUID userId);
    public void deleteUserById(UUID userId);
    public void updateUser(User toUpdate);
    public User getUserByHash(String hashId);
    public void deleteUserByHash(String hashId);
}
