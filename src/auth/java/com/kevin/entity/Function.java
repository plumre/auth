package com.kevin.entity;

/*
 * Created by renhongjiang on 2019/1/17.
 */

import com.kevin.common.BaseEntity;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/17 11:16
 */
public class Function extends BaseEntity {

    private String name;
    private Long parentId;
    private String url;
    private Integer serialNum;
    private Integer accordion;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public Integer getAccordion() {
        return accordion;
    }

    public void setAccordion(Integer accordion) {
        this.accordion = accordion;
    }
}