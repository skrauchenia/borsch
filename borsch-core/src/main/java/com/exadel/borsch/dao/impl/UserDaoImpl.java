package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.UserDao;
import com.exadel.borsch.entiry.AccessRight;
import com.exadel.borsch.entiry.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author Vlad
 */
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    private static final String QUERY_SELECT_USER = "SELECT "
            + "u.id as idUser,"
            + "u.login as login,"
            + "u.name as name,"
            + "u.needEmailNotification as needEmailNotification,"
            + "u.accessRights as accessRights,"
            + "u.locale as locale"
            + "FROM User u ";

    private static final String QUERY_INSERT_USER = "INSERT INTO User "
            + "(login,name,email,needEmailNotification,accessRights,locale) "
            + "VALUES (?,?,?,?,?,?) returning idUser";

    private static final String QUERY_UPDATE_USER = "UPDATE User SET "
            + "login=?,name=?,email=?,needEmailNotification=?,accessRights=?,locale=? "
            + "WHERE id=?";

    private static final String QUERY_DELETE_USER = "DELETE FROM User WHERE id=?";

    private final RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("idUser"),
                    rs.getString("login"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getBoolean("needEmailNotification"),
                    parseAccessRights(rs.getString("accessRights")),
                    new Locale(rs.getString("locale"))
            );
        }
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
    public void delete(Long userId) {
        getJdbcTemplate().update(
                QUERY_DELETE_USER,
                userId
        );
    }

    @Override
    public void update(User user) {
        getJdbcTemplate().update(
                QUERY_UPDATE_USER,
                user.getLogin(),
                user.getName(),
                user.getEmail(),
                user.getNeedEmailNotification(),
                user.getAccessRights().toString(),
                user.getLocale().toString(),
                user.getId()
        );
    }

    @Override
    public void save(User user) {
        Long id = getJdbcTemplate().queryForLong(
                QUERY_INSERT_USER,
                user.getLogin(),
                user.getName(),
                user.getEmail(),
                user.getNeedEmailNotification(),
                user.getAccessRights().toString(),
                user.getLocale().toString()
        );

        user.setId(id);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    private Set<AccessRight> parseAccessRights(String parse) {

    }
}
