package com.kevin.service;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.dao.RoleDAO;
import com.kevin.dao.RoleFunctionDAO;
import com.kevin.entity.Role;
import com.kevin.entity.RoleFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * role service
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 16:02
 */
@Service
public class RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RoleFunctionDAO roleFunctionDAO;

    /**
     * save role info, meantime save the relative functions
     *
     * @param role          role info
     * @param roleFunctions the relative functions
     */
    public void saveRole(Role role, Collection<RoleFunction> roleFunctions) {
        roleDAO.saveRole(role);
        /*for (RoleFunction roleFunction : roleFunctions) {
            roleFunction.setRoleId(role.getId());
        }*/
        // forEach Statement
        roleFunctions.forEach(roleFunction -> roleFunction.setRoleId(role.getId()));
        roleFunctionDAO.saveRoleFunction(roleFunctions);
    }

    /**
     * delete role info by id
     *
     * @param id the id of role
     */
    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
        roleFunctionDAO.deleteRoleFunction(id);
    }

    /**
     * edit and save role info
     *
     * @param role role info
     */
    public void updateRole(Role role, Collection<RoleFunction> roleFunctions) {
        roleDAO.updateRole(role);
        roleFunctionDAO.deleteRoleFunction(role.getId());
        roleFunctions.forEach(roleFunction -> roleFunction.setRoleId(role.getId()));
        roleFunctionDAO.saveRoleFunction(roleFunctions);
    }

    /**
     * get role info
     *
     * @param id the id of role
     * @return role info
     */
    public Role getRole(Long id) {
        return roleDAO.getRole(id);
    }

    /**
     * list roles and relative functionIds as per page and size
     *
     * @param page page num
     * @param size page size
     * @return collection of roles
     */
    public Collection<Role> listRoles(int page, int size) {
        Collection<Role> roles = roleDAO.listRoles(page, size);
        roles.forEach(
                role -> {
                    Collection<RoleFunction> roleFunctions = roleFunctionDAO.listRoleFunctions(role.getId());
                    StringBuffer functionIds = new StringBuffer();
                    roleFunctions.forEach(
                            roleFunction -> {
                                functionIds.append(roleFunction.getFunctionId()).append(",");
                            });
                    if (functionIds.length() > 1) {
                        role.setFunctionIds(functionIds.deleteCharAt(functionIds.length() - 1).toString());
                    }
                });
        return roles;
    }

    /**
     * list role info as per ids
     *
     * @param ids the collection of roles' id
     * @return collection of roles
     */
    public Collection<Role> listRoles(Collection<Long> ids) {
        return roleDAO.listRoles(ids);
    }

    /**
     * list role-functions relations as per roleId
     *
     * @param roleId the id of role
     * @return collection of role-functions relations
     */
    public Collection<RoleFunction> listRoleFunctions(Long roleId) {
        return roleFunctionDAO.listRoleFunctions(roleId);
    }
}