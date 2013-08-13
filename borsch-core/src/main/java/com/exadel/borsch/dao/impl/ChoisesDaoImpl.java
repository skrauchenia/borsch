package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.BorschJdbcDaoSupport;
import com.exadel.borsch.dao.ChoisesDao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad
 */
public class ChoisesDaoImpl extends BorschJdbcDaoSupport implements ChoisesDao {

    private static final String QUERY_DELETE_DISH = "DELETE FROM Choises WHERE dish=?";

    public ChoisesDaoImpl(JdbcTemplate jdbcTemplate) {
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
}
