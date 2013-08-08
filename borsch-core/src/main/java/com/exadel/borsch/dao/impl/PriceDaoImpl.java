package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.BorschJdbcDaoSupport;
import com.exadel.borsch.dao.PriceDao;
import com.exadel.borsch.entity.PriceList;
import org.joda.time.DateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad
 */
public class PriceDaoImpl extends BorschJdbcDaoSupport implements PriceDao {

    private static final String QUERY_SELECT_PRICE = "SELECT * FROM PriceList ";

    private static final String QUERY_SELECT_PRICE_BY_ID = QUERY_SELECT_PRICE + "WHERE idPriceList=?";

    private static final String QUERY_DELETE_PRICE = "DELETE FROM PriceList WHERE idPriceList=?";

    private static final String QUERY_UPDATE_PRICE = "UPDATE PriceList "
            + "SET creationTime=?,expirationTime=?  WHERE idPriceList=?";

    private static final RowMapper<PriceList> PRICE_LIST_ROW_MAPPER = new RowMapper<PriceList>() {
        @Override
        public PriceList mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PriceList(
                    rs.getLong("idPriceList"),
                    new DateTime(rs.getDate("creationTime")),
                    new DateTime(rs.getDate("expirationTime"))
            );
        }
    };

    @Override
    public PriceList getById(Long id) {
        try {
            return getJdbcTemplate().queryForObject(
                    QUERY_SELECT_PRICE_BY_ID,
                    new Object[]{id},
                    PRICE_LIST_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        getJdbcTemplate().update(
                QUERY_DELETE_PRICE,
                id
        );
    }

    @Override
    public void save(PriceList priceList) {
        Map<String, Object> params = new HashMap<>();
        params.put("creationTime",priceList.getCreationTime().toDate());
        params.put("expirationTime",priceList.getExpirationTime().toDate());

        priceList.setId((Long) getJdbcInsert()
                .withTableName("PriceList")
                .usingGeneratedKeyColumns("idPriceList")
                .executeAndReturnKey(params));
    }

    @Override
    public void update(PriceList priceList) {
        getJdbcTemplate().update(
                QUERY_UPDATE_PRICE,
                priceList.getCreationTime().toDate(),
                priceList.getExpirationTime().toDate(),
                priceList.getId()
        );
    }

    @Override
    public List<PriceList> getAll() {
        return getJdbcTemplate().query(
                QUERY_SELECT_PRICE,
                PRICE_LIST_ROW_MAPPER
        );
    }
}
