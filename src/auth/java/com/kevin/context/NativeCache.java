package com.kevin.context;

/*
 * Created by renhongjiang on 2019/1/19.
 */

import com.kevin.entity.Function;
import com.kevin.entity.Role;
import com.kevin.service.FunctionService;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * cache of auth info for local use
 * singleton use, not for distribution or group
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/19 10:58
 */
@Service
public class NativeCache {

    private Map<Long, Function> functionMap = new HashMap<>();
    private Map<Long, List<Role>> userRoleMap = new HashMap<>();

    @Autowired
    private FunctionService functionService;

    @PostConstruct
    public void init() {
        List<Function> functions = functionService.listFunctions();
        functions.forEach(function -> functionMap.put(function.getId(), function));
    }

    public List<Function> listAllFunctions() {
        if (functionMap.isEmpty()) {
            init();
        }
        return new ArrayList<>(functionMap.values());
    }

    public void saveFunction(Function function) {
        functionMap.put(function.getId(), function);
    }

    public void replaceFunction(Function function) {
        if (functionMap.containsKey(function.getId())) {
            functionMap.remove(function.getId());
            functionMap.put(function.getId(), function);
        }
    }

    public void removeFunction(Long functionId) {
        functionMap.remove(functionId);
    }

    public Function getFunction(Long id) {
        return functionMap.get(id);
    }

    public void setRoles(Long userId, List<Role> roles) {
        userRoleMap.put(userId, roles);
    }

    public List<Role> listRoles(Long userId) {
        return userRoleMap.get(userId);
    }


}