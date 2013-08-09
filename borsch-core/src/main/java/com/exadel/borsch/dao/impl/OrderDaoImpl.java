package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.BorschJdbcDaoSupport;
import com.exadel.borsch.dao.OrderDao;
import com.exadel.borsch.entity.Order;
import org.joda.time.DateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vlad
 */
public class OrderDaoImpl extends BorschJdbcDaoSupport implements OrderDao {

    private static final String QUERY_SELECT_ORDER = "SELECT * FROM Order ";

    private static final String QUERY_SELECT_ORDER_BY_OWNER_ID = QUERY_SELECT_ORDER + "WHERE owner=?";

    private static final RowMapper<Order> ORDER_ROW_MAPPER = new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Order(
                    rs.getLong("idOrder"),
                    new DateTime(rs.getDate("startDate")),
                    new DateTime(rs.getDate("endDate"))
            );
        }
    };

    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        getJdbcInsert()
                .withTableName("Order")
                .usingGeneratedKeyColumns("idOrder");
    }

    @Override
    public void save(Order order) {
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", order.getStartDate().toDate());
        params.put("endDate", order.getEndDate().toDate());

        order.setId((Long) getJdbcInsert()
                .executeAndReturnKey(params));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Order getByOwnerId(Long ownerId) {
        try {
            return getJdbcTemplate().queryForObject(
                    QUERY_SELECT_ORDER_BY_OWNER_ID,
                    new Object[]{ownerId},
                    ORDER_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
