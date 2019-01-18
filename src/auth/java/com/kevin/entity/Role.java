package com.kevin.entity;

/*
 * Created by renhongjiang on 2019/1/17.
 */

import com.kevin.common.BaseEntity;

import java.util.List;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/17 11:16
 */
public class Role extends BaseEntity {

    private String name;
    private String  functionIds;


    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}