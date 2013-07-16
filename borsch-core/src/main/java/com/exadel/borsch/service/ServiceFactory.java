package com.exadel.borsch.service;

import com.exadel.borsch.dao.impl.RandomDaoImpl;
import com.exadel.borsch.service.impl.SomeBusinessLogicServiceImpl;

/**
 * @author skrauchenia
 */
public final class ServiceFactory {

    private ServiceFactory() {

    }

    public static SomeBusinessLogicService getSomeBusinessLogicService() {
        return new SomeBusinessLogicServiceImpl(new RandomDaoImpl());
    }
}
