package com.kevin.service;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.dao.UserDAO;
import com.kevin.dao.UserRoleDAO;
import com.kevin.entity.User;
import com.kevin.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 16:02
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;

    /**
     * save user info
     *
     * @param user user info
     */
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    /**
     * delete user info by id
     *
     * @param id the id of user
     */
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    /**
     * edit and save user info
     *
     * @param user user info
     */
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    /**
     * get user info
     *
     * @param id the id of user
     * @return user info
     */
    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    /**
     * get user info
     *
     * @param name the name of user
     * @param pwd  the id of user
     * @return user info
     */
    public User getUser(String name, String pwd) {
        return userDAO.getUser(name, pwd);
    }

    /**
     * list the users as required ids
     *
     * @param ids Collection of users' ids
     * @return Collection of User
     */
    public Collection<User> listUsers(Collection<Long> ids) {
        return userDAO.listUsers(ids);
    }

    /**
     * list the users as page num and size of page
     *
     * @param page page num
     * @param size page size
     * @return Collection of User
     */
    public Collection<User> listUsers(int page, int size) {
        return userDAO.listUsers(page, size);
    }

    /**
     * list the roles against on users as page num and size of page
     *
     * @param page page num
     * @param size page size
     * @return Collection of UserRole
     */
    public Collection<UserRole> listUserRoles(int page, int size) {
        return userRoleDAO.listUserRoles(page, size);
    }


}