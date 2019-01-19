package com.kevin.controller;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.common.AjaxResult;
import com.kevin.common.Whether;
import com.kevin.entity.User;
import com.kevin.entity.UserRole;
import com.kevin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * the controller of user
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 17:30
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String listUsers() {
        return "/security/user/user-list";
    }

    /**
     * save or update user info
     * @param user {@link User}
     * @return AjaxResult {@link AjaxResult}
     */
    @ResponseBody
    @RequestMapping(value = "/saveUpdateUser", method = RequestMethod.POST)
    public AjaxResult saveUpdateUser(User user) {
        try {
            if (user.getId() == null) {
                userService.saveUser(user);
            } else {
                userService.updateUser(user);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    /**
     * delete user info as per id
     * @param id id of user
     * @return AjaxResult {@link AjaxResult}
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public AjaxResult deleteUser(Long id) {
        userService.deleteUser(id);
        return AjaxResult.success();
    }

    /**
     * get collection of user info as per page and size
     * @param page page num
     * @param size page size
     * @return collection of user info {@link User}
     */
    @ResponseBody
    @RequestMapping(value = "/listUsers", method = RequestMethod.POST)
    public List<User> listUsers(int page, int size) {
        return (List<User>) userService.listUsers(page, size);
    }
}