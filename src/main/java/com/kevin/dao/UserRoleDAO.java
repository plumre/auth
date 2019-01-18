package com.kevin.dao;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.common.BaseDAO;
import com.kevin.entity.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static java.time.LocalDateTime.now;

/**
 * TODO the DAO(Data Access Object) of UserRole
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 11:06
 */
public class UserRoleDAO extends BaseDAO {



    private class UserRoleMapper implements RowMapper<UserRole> {

        @Override
        public UserRole mapRow(ResultSet resultSet, int i) throws SQLException {
            UserRole userRole = new UserRole();
            userRole.setId(resultSet.getLong("id"));
            userRole.setUserId(resultSet.getLong("user_id"));
            userRole.setRoleId(resultSet.getLong("role_id"));
            userRole.setGmtCreate(resultSet.getDate("gmt_create"));
            userRole.setGmtModified(resultSet.getDate("gmt_modified"));
            return userRole;
        }
    }


    public void saveUserRole(UserRole userRole) {
        String sql = "insert into auth_user_role(id, user_id, role_id, gmt_create) values(?,?,?,?)";
        jdbcTemplate.update(sql, userRole.getId(), userRole.getUserId(), userRole.getRoleId(), now());
    }

    public void deleteUserRole(Long id) {
        String sql = "delete from auth_user_role where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateUserRole(UserRole userRole) {
        String sql = "update auth_user_role set user_id = ?, role_id = ?, gmt_modified = ? where id = ?";
        jdbcTemplate.update(sql, userRole.getUserId(), userRole.getRoleId(), now(), userRole.getId());
    }

    public UserRole getUserRole(Long id) {
        String sql = "select * from auth_user_role where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRoleMapper());
    }

    public Collection<UserRole> listUserRoles(Collection<Long> ids) {
        StringBuffer sql = new StringBuffer("select * from auth_user_role where id in (");
        for (Long id : ids) {
            sql.append(id).append("?,");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(")");
        return jdbcTemplate.query(sql.toString(), ids.toArray(new Object[0]), new UserRoleMapper());
    }


    public Collection<UserRole> listUserRoles(int page, int size) {
        return null;
    }
}