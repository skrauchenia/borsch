package com.exadel.borsch.managers.jdbc;

import com.exadel.borsch.dao.PriceList;
import com.exadel.borsch.managers.PriceManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vlad
 */
@Service("jdbcPriceManager")
@Scope(value = "singleton")
public class JdbcPriceManager extends AbstractJdbc implements PriceManager {
    @Override
    public PriceList getPriceListById(Long id) {
        return null;
    }

    @Override
    public PriceList getCurrentPriceList() {
        return null;
    }

    @Override
    public void deletePriceListById(Long id) {

    }

    @Override
    public void addPriceList(PriceList toAdd) {

    }

    @Override
    public void updatePriceList(PriceList toUpdate) {

    }

    @Override
    public List<PriceList> getAllPriceLists() {
        return null;
    }
}
