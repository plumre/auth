package com.kevin.context;

/*
 * Created by renhongjiang on 2019/1/19.
 */

import com.kevin.entity.User;

import javax.servlet.http.Cookie;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/19 12:53
 */
public class LoginUserCache {

    //    private static Map<Long, LoginUser> cache = new HashMap<>();
    private static Map<String, User> cache = new HashMap<>();


    public static User get() {

        return null;
    }

    /**
     * cache for login info
     *
     * @param user user info
     *             // @param expire validate time, seconds as unit; 30 minutes --> 1800s
     */
    public static void put(User user/*, long expire*/) {
//        long expireTime = System.currentTimeMillis() + expire * 1000;
//        LoginUser loginUser = new LoginUser(user, expireTime);
        cache.put(user.getName(), user);
        UserContext.setCurrent(user);
        setCookie(user);
    }

    public static void remove(User user) {
        cache.remove(user.getName());
    }

    private static class LoginUser {
        private User user;
        private long expire;

        public LoginUser(User user, long expire) {
            this.user = user;
            this.expire = expire;
        }

        public long getExpire() {
            return expire;
        }

        public void setExpire(long expire) {
            this.expire = expire;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    public static void setCookie(User user) {
        int expire = 1800;
        String source = user.getName() + "$" + user.getPwd();
        byte[] bytes = Base64.getEncoder().encode(source.getBytes());
        Cookie cookie = new Cookie("auth", new String(bytes));
        cookie.setMaxAge(expire);
        ResponseContext.getCurrent().addCookie(cookie);
    }

    public static void removeCookie(String username) {
        cache.remove(username);
        Cookie cookie = new Cookie("auth", null);
        ResponseContext.getCurrent().addCookie(cookie);
        UserContext.setCurrent(null);
    }
}