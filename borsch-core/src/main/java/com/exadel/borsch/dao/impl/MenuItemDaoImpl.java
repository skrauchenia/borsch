package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.BorschJdbcDaoSupport;
import com.exadel.borsch.dao.MenuItemDao;
import com.exadel.borsch.entity.MenuItem;
import org.joda.time.DateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad
 */
public class MenuItemDaoImpl extends BorschJdbcDaoSupport implements MenuItemDao {

    private static final String QUERY_SELECT_MENU_ITEM = "SELECT * FROM MenuItem ";

    private static final String QUERY_SELECT_MENU_ITEM_BY_ID = QUERY_SELECT_MENU_ITEM + "WHERE idMenuItem=?";

    private static final String QUERY_SELECT_MENU_ITEM_BY_ORDER_ID = QUERY_SELECT_MENU_ITEM + "WHERE idOrder=?";

    private static final String QUERY_UPDATE_MENU_ITEM = "UDPATE MenuItem SET "
            + "date=?, isPaid=? WHERE idMenuItem=?";

    private static final String QUERY_SET_ORDER_ID = "UDPATE MenuItem SET orderId=? WHERE idMenuItem=?";

    private static final String QUERY_DELETE_MENU_ITEM = "DELETE FROM MenuIterm WHERE idMenuItem=?";

    private static final RowMapper<MenuItem> MENU_ITEM_ROW_MAPPER = new RowMapper<MenuItem>() {
        @Override
        public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new MenuItem(
                    rs.getLong("idMenuItem"),
                    new DateTime(rs.getDate("date")),
                    rs.getBoolean("isPaid")
            );
        }
    };

    public MenuItemDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        getJdbcInsert()
                .withTableName("MenuItem")
                .usingGeneratedKeyColumns("idMenuItem");
    }

    @Override
    public MenuItem getById(Long id) {
        try {
            return getJdbcTemplate().queryForObject(
                    QUERY_SELECT_MENU_ITEM_BY_ID,
                    new Object[]{id},
                    MENU_ITEM_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(MenuItem menuItem) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", menuItem.getDate().toDate());
        params.put("isPaid", menuItem.getIsPaid());

        menuItem.setId((Long) getJdbcInsert()
                .executeAndReturnKey(params));
    }

    @Override
    public void delete(Long id) {
        getJdbcTemplate().update(
                QUERY_DELETE_MENU_ITEM,
                id
        );
    }

    @Override
    public void update(MenuItem menuItem) {
        getJdbcTemplate().update(
                QUERY_UPDATE_MENU_ITEM,
                menuItem.getDate().toDate(),
                menuItem.getIsPaid(),
                menuItem.getId()
        );
    }

    @Override
    public List<MenuItem> getAllByOrderId(Long orderId) {
        return getJdbcTemplate().query(
                QUERY_SELECT_MENU_ITEM_BY_ORDER_ID,
                MENU_ITEM_ROW_MAPPER
        );
    }

    @Override
    public void setOrderId(Long menuItemId, Long orderId) {
        getJdbcTemplate().update(
                QUERY_SET_ORDER_ID,
                orderId,
                menuItemId
        );
    }
}
