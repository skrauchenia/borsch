package com.exadel.borsch.service.impl;

import com.exadel.borsch.dao.SomeEntityDao;
import com.exadel.borsch.service.SomeBusinessLogicService;

import java.util.Random;

/**
 * @author skrauchenia
 */
public class SomeBusinessLogicServiceImpl implements SomeBusinessLogicService {
    private static Random random = new Random();
    private SomeEntityDao entityDao;

    public SomeBusinessLogicServiceImpl(SomeEntityDao entityDao) {
        this.entityDao = entityDao;
    }

    /**
     * Do really important business logic
     * @return
     */
    @Override
    public String doSomeWork() {
        Integer entityId = entityDao.getEntityId();
        entityId -= random.nextInt();
        return entityId.toString();
    }
}
