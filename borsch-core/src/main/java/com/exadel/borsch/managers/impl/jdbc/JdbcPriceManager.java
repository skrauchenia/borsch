package com.exadel.borsch.managers.impl.jdbc;

import com.exadel.borsch.dao.DishDao;
import com.exadel.borsch.dao.PriceDao;
import com.exadel.borsch.entity.Dish;
import com.exadel.borsch.entity.PriceList;
import com.exadel.borsch.managers.PriceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Vlad
 */
@Service("jdbcPriceManager")
@Scope(value = "singleton")
public class JdbcPriceManager implements PriceManager {

    @Autowired
    private PriceDao priceDao;

    @Autowired
    private DishDao dishDao;

    @Override
    @Transactional(readOnly = true)
    public PriceList getPriceListById(Long id) {
        PriceList priceList = priceDao.getById(id);

        if (priceList != null) {
            priceList.addDishes(dishDao.getAllByPriceListId(id));
            return priceList;
        }

        priceList = new PriceList();

        priceDao.save(priceList);

        return priceList;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public PriceList getCurrentPriceList() {
        List<PriceList> priceLists = priceDao.getAll();
        return priceLists.get(priceLists.size() - 1);
    }

    @Override
    public void deletePriceListById(Long id) {
        priceDao.delete(id);
    }

    @Override
    public void addPriceList(PriceList toAdd) {
        priceDao.save(toAdd);
    }

    @Override
    public void updatePriceList(PriceList toUpdate) {
        priceDao.update(toUpdate);
    }

    @Override
    public void addDishToPriceList(Dish dish, PriceList priceList) {
        dishDao.
    }

    @Override
    public void updateDishInPriceList(Dish dish, PriceList priceList) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeDishFromPriceList(Dish dish, PriceList priceList) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<PriceList> getAllPriceLists() {
        return priceDao.getAll();
    }
}
