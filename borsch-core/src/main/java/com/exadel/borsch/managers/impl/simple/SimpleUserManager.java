package com.exadel.borsch.managers.impl.simple;

import com.exadel.borsch.entity.AccessRight;
import com.exadel.borsch.entity.User;
import com.exadel.borsch.managers.UserManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Vlad
 */
@Service("simpleUserManager")
@Scope(value = "singleton")
public class SimpleUserManager implements UserManager {
    private List<User> users;

    public SimpleUserManager() {
        // TODO read frome file
        users = new ArrayList<User>();

        // Test data
        User admin = new User();
        admin.setName("Administrator The Great");
        admin.setLogin("admin");
        admin.setEmail("borschmail@gmail.com");
        admin.addAccessRights(Arrays.asList(AccessRight.values()));
        users.add(admin);
    }

    @Override
    public User getUserById(Long userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }


    @Override
    public void deleteUserById(Long userId) {
        ListIterator<User> iter = users.listIterator();
        while (iter.hasNext()) {
            User curUser = iter.next();
            if (curUser.getId().equals(userId)) {
                iter.remove();
                return;
            }
        }
    }

    @Override
    public void updateUser(User toUpdate) {
        ListIterator<User> iter = users.listIterator();
        while (iter.hasNext()) {
            User curUser = iter.next();
            if (curUser.getId().equals(toUpdate.getId())) {
                iter.set(toUpdate);
                return;
            }
        }
    }

    @Override
    public void addUser(User toAdd) {
        users.add(toAdd);
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public List<User> getAllUsers(AccessRight accessRight) {
        // unused in this implementstion
        return null;
    }
}
