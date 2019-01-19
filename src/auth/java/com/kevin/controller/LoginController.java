package com.kevin.controller;

/*
 * Created by renhongjiang on 2019/1/19.
 */

import com.kevin.common.Node;
import com.kevin.context.LoginUserCache;
import com.kevin.context.NativeCache;
import com.kevin.context.UserContext;
import com.kevin.entity.Role;
import com.kevin.entity.User;
import com.kevin.entity.UserRole;
import com.kevin.service.RoleService;
import com.kevin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/19 12:46
 */
@Controller
//@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private NativeCache nativeCache;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        if (UserContext.getCurrent() != null && UserContext.getCurrent().getUser() != null) {
            return "/layout/main";
        }
        return "/security/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, String name, String pwd) {
        User user = userService.getUser(name, pwd);
        if (user == null) {
            return index();
        }
        try {
            //Membrane cache
//            LoginUserCache.put(user, 30 * 60);
            if ("admin".equals(user.getName())) {
                model.addAttribute("nodes", listNodes(true, null));
            } else {
                Collection<UserRole> userRoles = userService.listUserRoles(user.getId());
                if (userRoles == null || userRoles.size() == 0) {
                    model.addAttribute("用户未关联任何角色");
                    return index();
                }
                List<Long> roleIds = new ArrayList<>();
                userRoles.forEach(userRole -> roleIds.add(userRole.getRoleId()));
                Collection<Role> roles = roleService.listRoles(roleIds);
                nativeCache.setRoles(user.getId(), (List<Role>) roles);
                model.addAttribute("nodes", listNodes(false, user.getId()));
            }
            return "/layout/main";
        } catch (Exception e) {
            e.printStackTrace();
            LoginUserCache.remove(user);
            return index();
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        if (UserContext.getCurrent() != null && UserContext.getCurrent().getUser() != null) {
            LoginUserCache.remove(UserContext.getCurrent().getUser());
        }
        return index();
    }

    private List<Node> listNodes(boolean isAdmin, Long userId) {
        Set<String> permissionUrls = new HashSet<>();
        Set<Long> rootFunctionIdSet = new HashSet<>();
        if (!isAdmin) {

        }
        return null;
    }

}