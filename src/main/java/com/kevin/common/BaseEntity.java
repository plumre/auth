package com.kevin.common;

/*
 * Created by renhongjiang on 2019/1/17.
 */

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/17 11:15
 */
public abstract class BaseEntity {

    private Long id;
    private Date gmtCreate;
    private Date gmtModified;

    public static <T extends BaseEntity> Map<Long, T> idEntityMap(Collection<T> list) {
        Map<Long, T> map = new HashMap<>();
        if (list == null || list.size() == 0) {
            return map;
        }

        for (T entity : list) {
            map.put(entity.getId(), entity);
        }

        return map;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}