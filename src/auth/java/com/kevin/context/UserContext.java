package com.kevin.context;

/*
 * Created by renhongjiang on 2019/1/19.
 */

import com.kevin.entity.User;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/19 14:52
 */
public class UserContext {

    private static ThreadLocal<UserContext> userContextTL = new ThreadLocal<>();

    private User user;

    private UserContext(User user) {
        this.user = user;
    }

    static void setCurrent(User user) {
        userContextTL.set(new UserContext(user));
    }

    public static UserContext getCurrent() {
        return userContextTL.get();
    }

    public User getUser() {
        return user;
    }
}