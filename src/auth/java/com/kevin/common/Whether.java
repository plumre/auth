package com.kevin.common;

/*
 * Created by renhongjiang on 2019/1/17.
 */

/**
 * the enumeration of yes and no
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/17 11:15
 */
public enum Whether {

    /**
     * 是
     */
    YES(1),
    /**
     * 否
     */
    NO(0);

    private Integer value;

    Whether(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}