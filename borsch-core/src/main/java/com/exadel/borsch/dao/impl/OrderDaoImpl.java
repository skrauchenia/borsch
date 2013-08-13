package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.BorschJdbcDaoSupport;
import com.exadel.borsch.dao.OrderDao;
import com.exadel.borsch.entity.Order;
import com.exadel.borsch.util.Entry;
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
public class OrderDaoImpl extends BorschJdbcDaoSupport implements OrderDao {

    private static final String QUERY_SELECT_ORDER = "SELECT * FROM Orders ";

    private static final String QUERY_SELECT_ENTRY = "SELECT idOrder, owner FROM Orders";

    private static final String QUERY_SELECT_ORDER_BY_OWNER_ID = QUERY_SELECT_ORDER + "WHERE owner=?";

    private static final String QUERY_SELECT_ORDER_BY_ID = QUERY_SELECT_ORDER + "WHERE idOrder=?";

    private static final String QUERY_DELETE_ORDER = "DELETE FROM Orders WHERE idOrder=?";

    private static final String QUERY_DELETE_ORDER_BY_USER_ID = "DELETE FROM Orders WHERE owner=?";

    private static final String QUERY_UPDATE_ORDER = "UPDATE Orders SET "
            + "startDate=? endDate=? WHERE idOrder=?";

    private static final String QUERY_SET_OWNER_ID = "UPDATE Orders SET "
            + "owner=? WHERE idOrder=?";

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

    private static final RowMapper<Entry> ENTRY_ROW_MAPPER = new RowMapper<Entry>() {
        @Override
        public Entry mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Entry(
                    rs.getLong("idOrder"),
                    rs.getLong("owner")
            );
        }
    };

    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        getJdbcInsert()
                .withTableName("Orders")
                .usingGeneratedKeyColumns("idOrder");
    }

    @Override
    public Order getById(Long id) {
        try {
            return getJdbcTemplate().queryForObject(
                    QUERY_SELECT_ORDER_BY_ID,
                    new Object[]{id},
                    ORDER_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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
        getJdbcTemplate().update(
                QUERY_DELETE_ORDER,
                id
        );
    }

    @Override
    public void update(Order order) {
        getJdbcTemplate().update(
                QUERY_UPDATE_ORDER,
                order.getStartDate().toDate(),
                order.getEndDate(),
                order.getId()
        );
    }

    @Override
    public List<Order> getByOwnerId(Long ownerId) {
        return getJdbcTemplate().query(
                QUERY_SELECT_ORDER_BY_OWNER_ID,
                new Object[]{ownerId},
                ORDER_ROW_MAPPER
        );
    }

    @Override
    public void setOwnerId(Long orderId, Long ownerId) {
        getJdbcTemplate().update(
                QUERY_SET_OWNER_ID,
                ownerId,
                orderId
        );
    }

    @Override
    public List<Order> getAll() {
        return getJdbcTemplate().query(
                QUERY_SELECT_ORDER,
                ORDER_ROW_MAPPER
        );
    }

    @Override
    public List<Entry> getUsersIdForOrdersId() {
        return getJdbcTemplate().query(
                QUERY_SELECT_ENTRY,
                ENTRY_ROW_MAPPER
        );
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        getJdbcTemplate().update(
                QUERY_DELETE_ORDER_BY_USER_ID,
                userId
        );
    }
}
