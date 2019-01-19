package com.kevin.common;

/*
 * Created by renhongjiang on 2019/1/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * the basic DAO (Data Access Object)
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/17 11:15
 */
public abstract class BaseDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

}