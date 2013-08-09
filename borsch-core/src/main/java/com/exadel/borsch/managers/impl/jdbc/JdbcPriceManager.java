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
import java.util.ListIterator;

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
        if (priceLists.isEmpty()) {
            PriceList newPriceList = new PriceList();

            priceDao.save(newPriceList);

            priceLists.add(newPriceList);
        }
        return priceLists.get(priceLists.size() - 1);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePriceListById(Long id) {
        priceDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addPriceList(PriceList toAdd) {
        priceDao.save(toAdd);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePriceList(PriceList toUpdate) {
        priceDao.update(toUpdate);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addDishToPriceList(Dish dish, PriceList priceList) {
        dishDao.save(dish);
        dishDao.setPriceList(dish.getId(), priceList.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDishInPriceList(Dish dish, PriceList priceList) {
        dishDao.update(dish);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeDishFromPriceList(Dish dish, PriceList priceList) {
        dishDao.delete(dish.getId());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<PriceList> getAllPriceLists() {
        List<PriceList> priceLists = priceDao.getAll();
        ListIterator<PriceList> it = priceLists.listIterator();

        while (it.hasNext()) {
            PriceList curPriceList = it.next();

            curPriceList.addDishes(dishDao.getAllByPriceListId(curPriceList.getId()));
        }

        return priceLists;
    }
}
