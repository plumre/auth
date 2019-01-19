package com.kevin.entity;

/*
 * Created by renhongjiang on 2019/1/17.
 */

import com.kevin.common.BaseEntity;

/**
 * user entity
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/17 11:16
 */
public class User extends BaseEntity {

    private String name;
    private String pwd;
    private String roleIds;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
