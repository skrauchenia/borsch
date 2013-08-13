package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.BorschJdbcDaoSupport;
import com.exadel.borsch.dao.DishDao;
import com.exadel.borsch.entity.Course;
import com.exadel.borsch.entity.Dish;
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
public class DishDaoImpl extends BorschJdbcDaoSupport implements DishDao {

    private static final String QUERY_SELECT_DISH = "SELECT * FROM Dish ";

    private static final String QUERY_SELECT_DISH_BY_ID = QUERY_SELECT_DISH + "WHERE idDish=?";

    private static final String QUERY_SELECT_DISH_BY_MENU_ITEM_ID = "SELECT * FROM Dish LEFT OUTER JOIN Choises ON Choises.dish=Dish.idDish WHERE Choises.menuItem=?";

    private static final String QUERY_SELECT_DISH_BY_PRICE_LIST_ID = QUERY_SELECT_DISH + "WHERE priceList=?";

    private static final String QUERY_DELETE_DISH = "DELETE FROM Dish WHERE idDish=?";

    private static final String QUERY_UPDATE_DISH = "UPDATE Dish SET "
            + "name=?,photoUrl=?,price=?,description=?,course=? WHERE idDish=?";

    private static final String QUERY_UPDATE_DISH_WITH_PRICE_LIST = "UPDATE Dish SET priceList=? WHERE idDish=?";

    private static final RowMapper<Dish> DISH_ROW_MAPPER = new RowMapper<Dish>() {
        @Override
        public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Dish(
                    rs.getLong("idDish"),
                    rs.getString("name"),
                    rs.getString("photoUrl"),
                    rs.getInt("price"),
                    rs.getString("description"),
                    Course.valueOf(rs.getString("course"))
            );
        }
    };

    public DishDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        setJdbcInsert(getJdbcInsert()
                .withTableName("dish")
                .usingGeneratedKeyColumns("idDish"));
    }

    @Override
    public Dish getById(Long id) {
        try {
            return getJdbcTemplate().queryForObject(
                    QUERY_SELECT_DISH_BY_ID,
                    new Object[]{id},
                    DISH_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(Dish dish) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dish.getName());
        params.put("photoUrl", dish.getName());
        params.put("price", dish.getPrice());
        params.put("description", dish.getDescription());
        params.put("course", dish.getCourse().toString());

        dish.setId((Long) getJdbcInsert()
                .executeAndReturnKey(params));
    }

    @Override
    public void delete(Long id) {
        getJdbcTemplate().update(
                QUERY_DELETE_DISH,
                id
        );
    }

    @Override
    public void update(Dish dish) {
        getJdbcTemplate().update(
                QUERY_UPDATE_DISH,
                dish.getName(),
                dish.getPhotoUrl(),
                dish.getPrice(),
                dish.getDescription(),
                dish.getCourse().toString(),
                dish.getId()
        );
    }

    @Override
    public List<Dish> getAllByMenuItemId(Long menuItemId) {
        return getJdbcTemplate().query(
                QUERY_SELECT_DISH_BY_MENU_ITEM_ID,
                new Object[]{menuItemId},
                DISH_ROW_MAPPER
        );
    }

    @Override
    public List<Dish> getAllByPriceListId(Long priceListId) {
        return getJdbcTemplate().query(
                QUERY_SELECT_DISH_BY_PRICE_LIST_ID,
                new Object[]{priceListId},
                DISH_ROW_MAPPER
        );
    }

    @Override
    public void setPriceList(Long dishId, Long priceListId) {
        getJdbcTemplate().update(
                QUERY_UPDATE_DISH_WITH_PRICE_LIST,
                priceListId,
                dishId
        );
    }
}
