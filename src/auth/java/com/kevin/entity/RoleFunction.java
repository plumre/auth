package com.kevin.entity;

/*
 * Created by renhongjiang on 2019/1/17.
 */

import com.kevin.common.BaseEntity;

/**
 * role-functions relations entity
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/17 11:17
 */
public class RoleFunction extends BaseEntity {

    private Long roleId;
    private Long functionId;
    private Integer status;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}