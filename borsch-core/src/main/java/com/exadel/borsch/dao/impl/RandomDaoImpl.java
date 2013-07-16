package com.exadel.borsch.dao.impl;

import com.exadel.borsch.dao.SomeEntityDao;

import java.util.Random;

/**
 * @author skrauchenia
 */
public class RandomDaoImpl implements SomeEntityDao {
    private static Random random = new Random();

    @Override
    public Integer getEntityId() {
        random.nextInt();
        return random.nextInt();
    }
}
