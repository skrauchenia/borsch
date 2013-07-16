package com.exadel.borsch.web.controllers;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;

/**
 * Base class fo unit tests with spring context.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:app-servlet.xml"})
public abstract class BaseSpringMvcTest {

//    private org.springframework.web.context.WebApplicationContext wac = ;

    protected MockMvc mockMvc;

    @Before
    public void init() {
//        mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
    }

}
