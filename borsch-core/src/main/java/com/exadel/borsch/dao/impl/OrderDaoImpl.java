package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.BorschJdbcDaoSupport;
import com.exadel.borsch.dao.OrderDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Vlad
 */
public class OrderDaoImpl extends BorschJdbcDaoSupport implements OrderDao {
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
}
