package com.exadel.borsch.managers.impl.jdbc;

import com.exadel.borsch.dao.User;
import com.exadel.borsch.managers.UserManager;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Vlad
 * */
@Service("jdbcUserManager")
@Scope(value = "singleton")
public class JdbcUserManager extends JdbcDaoSupport implements UserManager {

    public JdbcUserManager() {
        createAdmin();
    }

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
    public Long addUser(final User toAdd) {
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UserQueries.INSERT_USER_QUERY
                                                                   , Statement.RETURN_GENERATED_KEYS);
                ps.setString(MagicNumbers.ONE, toAdd.getLogin());
                ps.setString(MagicNumbers.TWO, toAdd.getName());
                ps.setString(MagicNumbers.TREE, toAdd.getEmail());
                ps.setBoolean(MagicNumbers.FOUR, toAdd.getNeedEmailNotification());
                ps.setString(MagicNumbers.FIVE, toAdd.getAccessRights().toString());
                ps.setString(MagicNumbers.SIX, toAdd.getLocale().toString());
                return ps;
            }
        }, holder);

        Long newPersonId = holder.getKey().longValue();
        return newPersonId;
    }

    @Override
    public void addUsers(List<User> toAdd) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    private void createAdmin() {
    }
}
