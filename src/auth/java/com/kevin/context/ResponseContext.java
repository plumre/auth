package com.kevin.context;

/*
 * Created by renhongjiang on 2019/1/19.
 */

import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/19 15:22
 */
class ResponseContext {
    private static ThreadLocal<HttpServletResponse> tl = new ThreadLocal<>();

    public static void setCurrent(HttpServletResponse response) {
        tl.set(response);
    }

    public static HttpServletResponse getCurrent() {
        return tl.get();
    }


}