package com.exadel.borsch.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author Vlad
 */
public class BorschJdbcDaoSupport extends JdbcDaoSupport {

    private SimpleJdbcInsert jdbcInsert;

    public BorschJdbcDaoSupport(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
        setJdbcInsert(new SimpleJdbcInsert(jdbcTemplate));
    }

    public SimpleJdbcInsert getJdbcInsert() {
        return jdbcInsert;
    }

    public void setJdbcInsert(SimpleJdbcInsert jdbcInsert) {
        this.jdbcInsert = jdbcInsert;
    }
}
