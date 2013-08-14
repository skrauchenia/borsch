package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.BorschJdbcDaoSupport;
import com.exadel.borsch.dao.ChoicesDao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vlad
 */
public class ChoicesDaoImpl extends BorschJdbcDaoSupport implements ChoicesDao {

    private static final String QUERY_DELETE_DISH = "DELETE FROM Choises WHERE dish=?";

    private static final String QUERY_DELETE_DISH_BY_MENU_ITEM_ID = "DELETE FROM Choises WHERE menuItem=?";

    public ChoicesDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        setJdbcInsert(getJdbcInsert()
                .withTableName("choises")
        );
    }

    @Override
    public void save(Long menuItemId, Long dishId) {
        Map<String, Object> params = new HashMap<>();
        params.put("menuItem", menuItemId);
        params.put("dish", dishId);

        getJdbcInsert().execute(params);
    }

    @Override
    public void delete(Long dishId) {
        getJdbcTemplate().update(
                QUERY_DELETE_DISH,
                dishId
        );
    }

    @Override
    public void deleteAllByMenuItemId(Long menuItemId) {
        getJdbcTemplate().update(
                QUERY_DELETE_DISH_BY_MENU_ITEM_ID,
                menuItemId
        );
    }
}
