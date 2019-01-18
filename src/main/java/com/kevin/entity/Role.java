package com.kevin.entity;

/*
 * Created by renhongjiang on 2019/1/17.
 */

import com.kevin.common.BaseEntity;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/17 11:16
 */
public class Role extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}