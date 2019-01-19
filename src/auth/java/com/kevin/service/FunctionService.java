package com.kevin.service;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.dao.FunctionDAO;
import com.kevin.entity.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * function service
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 16:03
 */
@Service
public class FunctionService {

    @Autowired
    private FunctionDAO functionDAO;

    /**
     * save function info
     *
     * @param function function info
     */
    public void saveFunction(Function function) {
        functionDAO.saveFunction(function);
    }

    /**
     * delete the specific function as per id
     *
     * @param id the id of function
     */
    public void deleteFunction(Long id) {
        functionDAO.deleteFunction(id);
    }

    /**
     * update url info of the specific function
     *
     * @param id  the id of specific function
     * @param url new url info
     */
    public void updateUrl(Long id, String url) {
        functionDAO.updateUrl(id, url);
    }

    /**
     * list children functions as per parentId with the page num and page size
     *
     * @param page     page num
     * @param size     page size
     * @param parentId the id of parent node
     * @return collection of children functions
     */
    public List<Function> listFunctions(Integer page, Integer size, Long parentId) {
        return functionDAO.listFunctions(page, size, parentId);
    }

    /**
     * list all functions
     *
     * @return collection of all functions
     */
    public List<Function> listFunctions() {
        return functionDAO.listFunctions();
    }


}