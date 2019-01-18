package com.kevin.dao;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.common.BaseDAO;
import com.kevin.entity.RoleFunction;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.time.LocalDateTime.now;

/**
 * TODO the DAO(Data Access Object) of RoleFunction
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 11:06
 */
@Repository
public class RoleFunctionDAO extends BaseDAO {



    private class RoleFunctionMapper implements RowMapper<RoleFunction> {
        @Override
        public RoleFunction mapRow(ResultSet resultSet, int i) throws SQLException {
            RoleFunction roleFunction = new RoleFunction();
            roleFunction.setId(resultSet.getLong("id"));
            roleFunction.setRoleId(resultSet.getLong("role_id"));
            roleFunction.setFunctionId(resultSet.getLong("function_id"));
            roleFunction.setGmtCreate(resultSet.getDate("gmt_create"));
            roleFunction.setGmtModified(resultSet.getDate("gmt_modified"));
            return roleFunction;
        }
    }

    /**
     * select roleFunction by name
     *
     * @param name name of roleFunction
     * @return the roleFunction entity
     */
    public RoleFunction getRoleFunction(String name) {
        String sql = "select * from auth_role_function where name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new RoleFunctionMapper());
    }


    public void saveRoleFunction(RoleFunction roleFunction) {
        String sql = "insert into auth_role_function(id, role_id, function_id, status, gmt_create) values(?,?,?,?)";
        jdbcTemplate.update(sql, roleFunction.getId(), roleFunction.getRoleId(), roleFunction.getFunctionId(), roleFunction.getStatus(), now());
    }

    public void saveRoleFunction(Collection<RoleFunction> roleFunctions) {
        String sql = "insert into auth_role_function(role_id, function_id, status, gmt_create) values(?,?,?,?)";

        List<Object[]> batchArgs = new ArrayList<>();
        for (RoleFunction roleFunction : roleFunctions) {
            Object[] objects = new Object[4];
            objects[0] = roleFunction.getRoleId();
            objects[1] = roleFunction.getFunctionId();
            objects[2] = roleFunction.getStatus();
            objects[3] = now();
            batchArgs.add(objects);
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public void deleteRoleFunction(Long roleId) {
        String sql = "delete from auth_role_function where role_id = ?";
        jdbcTemplate.update(sql, roleId);
    }

    public void updateRoleFunction(RoleFunction roleFunction) {
        String sql = "update auth_role_function set role_id = ?, function_id = ?, status = ?, gmt_modified = ? where id = ?";
        jdbcTemplate.update(sql, roleFunction.getRoleId(), roleFunction.getFunctionId(), roleFunction.getStatus(), now(), roleFunction.getId());
    }

    public RoleFunction getRoleFunction(Long id) {
        String sql = "select * from auth_role_function where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RoleFunctionMapper());
    }

    public Collection<RoleFunction> listRoleFunctions(Collection<Long> ids) {
        StringBuffer sql = new StringBuffer("select * from auth_role_function where id in (");
        for (Long id : ids) {
            sql.append(id).append("?,");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(")");
        return jdbcTemplate.query(sql.toString(), ids.toArray(new Object[0]), new RoleFunctionMapper());
    }

    public Collection<RoleFunction> listRoleFunctions(Long roleId) {
        String sql = "select * from auth_user_role where user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{roleId}, new RoleFunctionMapper());
    }


}