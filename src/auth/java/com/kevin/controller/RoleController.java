package com.kevin.controller;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.common.AjaxResult;
import com.kevin.common.Whether;
import com.kevin.entity.Role;
import com.kevin.entity.RoleFunction;
import com.kevin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO the controller of role
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 17:30
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @ResponseBody
    @RequestMapping(value = "/listRoles", method = RequestMethod.POST)
    public List<Role> listRoles(int page, int size) {
        return (List<Role>) roleService.listRoles(page, size);
    }

    @ResponseBody
    @RequestMapping(value = "/saveUpdateRole", method = RequestMethod.POST)
    public AjaxResult saveUpdateRole(Role role) {
        try {
            String[] ids = role.getFunctionIds().split(",");
            List<RoleFunction> roleFunctions = new ArrayList<>();
            for (String id : ids) {
                RoleFunction roleFunction = new RoleFunction();
                roleFunction.setFunctionId(Long.valueOf(id));
                roleFunction.setStatus(Whether.YES.getValue());
                roleFunctions.add(roleFunction);
            }
            if (role.getId() == null) {
                roleService.saveRole(role, roleFunctions);
            } else {
                roleService.updateRole(role, roleFunctions);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public AjaxResult deleteRole(Long id) {
        roleService.deleteRole(id);
        return AjaxResult.success();
    }


}