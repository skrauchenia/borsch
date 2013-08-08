package com.exadel.borsch.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author Vlad
 */
public class BorschJdbcDaoSupport extends JdbcDaoSupport {

    private SimpleJdbcInsert jdbcInsert;

    public SimpleJdbcInsert getJdbcInsert() {
        return jdbcInsert;
    }

    public void setJdbcInsert(SimpleJdbcInsert jdbcInsert) {
        this.jdbcInsert = jdbcInsert;
    }
}
