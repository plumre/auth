package com.kevin.common;

/*
 * Created by renhongjiang on 2019/1/17.
 */

import java.io.Serializable;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/17 11:16
 */
public class AjaxResult implements Serializable {


    private static final long serialVersionUID = -1282705380502963911L;

    private static final Integer AJAX_STATUS_SUCCESS = 0;
    private static final Integer AJAX_STATUS_WARN = 1;
    private static final Integer AJAX_STATUS_ERROR = 2;

    private Integer statusCode;
    private String message;

    private AjaxResult() {
        super();
    }

    public static AjaxResult success() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setStatusCode(AJAX_STATUS_SUCCESS);
        ajaxResult.setMessage("操作成功");
        return ajaxResult;
    }

    public static AjaxResult error() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setStatusCode(AJAX_STATUS_ERROR);
        ajaxResult.setMessage("操作错误");
        return ajaxResult;
    }

    public static AjaxResult warn() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setStatusCode(AJAX_STATUS_WARN);
        ajaxResult.setMessage("操作警告");
        return ajaxResult;
    }

    public AjaxResult(Integer statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}