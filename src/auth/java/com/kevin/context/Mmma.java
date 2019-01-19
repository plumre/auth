package com.kevin.context;

/*
 * Created by renhongjiang on 2019/1/19.
 */

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/19 13:05
 */
public class Mmma {

    public static void main(String[] args) {
        /*
        不建议使用，推荐System.currentTimeMillis()
        System.out.println(new Date().getTime());
        */
        System.out.println("syscurr = " + System.currentTimeMillis());
        System.out.println("timetime = " + Calendar.getInstance().getTime().getTime());
        System.out.println("timemill = " + Calendar.getInstance().getTimeInMillis());
    }
}