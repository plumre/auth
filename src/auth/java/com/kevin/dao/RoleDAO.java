package com.kevin.dao;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.common.BaseDAO;
import com.kevin.entity.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static java.time.LocalDateTime.now;

/**
 * the DAO(Data Access Object) of Role
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 11:06
 */
@Repository
public class RoleDAO extends BaseDAO {

    private class RoleMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setId(resultSet.getLong("id"));
            role.setName(resultSet.getString("name"));
            role.setGmtCreate(resultSet.getDate("gmt_create"));
            role.setGmtModified(resultSet.getDate("gmt_modified"));
            return role;
        }
    }

    /**
     * select role by name
     * @param name name of role
     * @return the role entity
     */
    public Role getRole(String name) {
        String sql = "select * from auth_role where name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new RoleMapper());
    }


    public void saveRole(Role role) {
        String sql = "insert into auth_role(id, name, pwd, gmt_create) values(?,?,?)";
        jdbcTemplate.update(sql, role.getId(), role.getName(), now());
    }

    public void deleteRole(Long id) {
        String sql = "delete from auth_role where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateRole(Role role) {
        String sql = "update auth_role set name = ?, gmt_modified = ? where id = ?";
        jdbcTemplate.update(sql, role.getName(), now(), role.getId());
    }

    public Role getRole(Long id) {
        String sql = "select * from auth_role where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, new RoleMapper());
    }

    public Collection<Role> listRoles(Collection<Long> ids) {
        StringBuffer sql = new StringBuffer("select * from auth_role where id in (");
        for (Long id : ids) {
            sql.append(id).append("?,");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(")");
        return jdbcTemplate.query(sql.toString(),ids.toArray(new Object[0]), new RoleMapper());
    }

    public Collection<Role> listRoles(int page, int size) {
        String sql = "select * from auth_role limit ?,?";
        return jdbcTemplate.query(sql, new Object[]{(page - 1) * size, (page * size - 1)}, new RoleMapper());
    }



}