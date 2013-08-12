package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.BorschJdbcDaoSupport;
import com.exadel.borsch.dao.UserDao;
import com.exadel.borsch.entity.AccessRight;
import com.exadel.borsch.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Vlad
 */
public class UserDaoImpl extends BorschJdbcDaoSupport implements UserDao {

    private static final String QUERY_SELECT_USER = "SELECT * FROM User ";

    private static final String QUERY_SELECT_USER_BY_ID = QUERY_SELECT_USER + "WHERE idUser=?";

    private static final String QUERY_SELECT_USER_BY_LOGIN = QUERY_SELECT_USER + "WHERE login=?";

    private static final String QUERY_SELECT_USER_BY_ACCESS_RIGHT = QUERY_SELECT_USER + "WHERE accessRight LIKE ?";

    private static final String QUERY_UPDATE_USER = "UPDATE User SET "
            + "login=?,name=?,email=?,needEmailNotification=?,accessRights=?,locale=? "
            + "WHERE idUser=?";

    private static final String QUERY_DELETE_USER = "DELETE FROM User WHERE idUser=?";

    private static final String PERCENT = "%";

    private static final RowMapper<User> USER_ROW_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("idUser"),
                    rs.getString("login"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getBoolean("needEmailNotification"),
                    parseAccessRights(rs.getString("accessRights")),
                    parseLocale(rs.getString("locale"))
            );
        }
    };

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        setJdbcInsert(getJdbcInsert()
                .withTableName("user")
                .usingGeneratedKeyColumns("idUser"));
    }

    @Override
    public User getUserById(Long userId) {
        try {
            return getJdbcTemplate().queryForObject(
                    QUERY_SELECT_USER_BY_ID,
                    new Object[]{userId},
                    USER_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            return getJdbcTemplate().queryForObject(
                    QUERY_SELECT_USER_BY_LOGIN,
                    new Object[]{login},
                    USER_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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
        Map<String, Object> params = new HashMap<>();
        params.put("login", user.getLogin());
        params.put("name", user.getName());
        params.put("email", user.getEmail());
        params.put("needEmailNotification", user.getNeedEmailNotification());
        params.put("accessRights", user.getAccessRights());
        params.put("locale", user.getLocale().toString());

        user.setId((Long) getJdbcInsert()
                .executeAndReturnKey(params));
    }

    @Override
    public List<User> getAllUsers() {
        return getJdbcTemplate().query(
                QUERY_SELECT_USER,
                USER_ROW_MAPPER
        );
    }

    @Override
    public List<User> getAllUsers(AccessRight accessRight) {
        String editAccessRight = PERCENT + accessRight.toString() + PERCENT;
        return getJdbcTemplate().query(
                QUERY_SELECT_USER_BY_ACCESS_RIGHT,
                new Object[]{editAccessRight},
                USER_ROW_MAPPER
        );
    }

    private static Set<AccessRight> parseAccessRights(String parse) {
        StringTokenizer st = new StringTokenizer(parse, "[], ");
        Set<AccessRight> rights = new HashSet<>();
        while (st.hasMoreTokens()) {
            rights.add(AccessRight.valueOf(st.nextToken()));
        }
        return rights;
    }

    private static Locale parseLocale(String locale) {
        StringTokenizer st = new StringTokenizer(locale, "_");
        return new Locale(st.nextToken(), st.nextToken());
    }
}
