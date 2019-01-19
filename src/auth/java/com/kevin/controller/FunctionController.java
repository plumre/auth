package com.kevin.controller;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.common.AjaxResult;
import com.kevin.common.Node;
import com.kevin.common.Tree;
import com.kevin.context.NativeCache;
import com.kevin.entity.Function;
import com.kevin.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * the controller of function
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 17:30
 */
@Controller
@RequestMapping("/function")
public class FunctionController {

    @Autowired
    private FunctionService functionService;
    @Autowired
    private NativeCache nativeCache;

    @RequestMapping("/index")
    public String listFunctions() {
        return "/security/function/function-list";
    }

    @ResponseBody
    @RequestMapping(value = "/saveUpdateFunction", method = RequestMethod.POST)
    public AjaxResult saveUpdateFunction(Function function) {
        try {
            if (function.getId() == null) {
                function.setSerialNum(nativeCache.listAllFunctions().size());
                functionService.saveFunction(function);
                nativeCache.saveFunction(function);
            } else {
                functionService.updateUrl(function.getId(), function.getUrl());
                nativeCache.replaceFunction(function);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFunction", method = RequestMethod.POST)
    public AjaxResult deleteFunction(Long id) {
        functionService.deleteFunction(id);
        nativeCache.removeFunction(id);
        return AjaxResult.success();
    }

    @ResponseBody
    @RequestMapping(value = "/listSubFunctions", method = RequestMethod.POST)
    public List<Function> listSubFunctions(Integer page, Integer size, Long parentId) {
        if (parentId == null) {
            parentId = 0L;
        }
        return functionService.listFunctions(page, size, parentId);
    }

    @ResponseBody
    @RequestMapping(value = "/buildMenuTreeForEdit", method = RequestMethod.POST)
    public List<Node> buildMenuTreeForEdit() {
        List<Function> functions = nativeCache.listAllFunctions();
        return new Tree(functions).build();
    }

}