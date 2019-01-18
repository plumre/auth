package com.kevin.dao;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.common.BaseDAO;
import com.kevin.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static java.time.LocalDateTime.now;

/**
 * TODO the DAO(Data Access Object) of User
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 11:06
 */
@Repository
public class UserDAO extends BaseDAO {



    private class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPwd(resultSet.getString("pwd"));
            user.setGmtCreate(resultSet.getDate("gmt_create"));
            user.setGmtModified(resultSet.getDate("gmt_modified"));
            return user;
        }
    }

    /**
     * select user by name and pwd
     *
     * @param name name of user
     * @param pwd  pwd of user
     * @return the user entity
     */
    public User getUser(String name, String pwd) {
        String sql = "select * from auth_user where name = ? and pwd = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name, pwd}, new UserMapper());
    }


    public void saveUser(User user) {
        String sql = "insert into auth_user(id, name, pwd, gmt_create) values(?,?,?,?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getPwd(), now());
    }

    public void deleteUser(Long id) {
        String sql = "delete from auth_user where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateUser(User user) {
        String sql = "update auth_user set name = ?, pwd = ?, gmt_modified = ?where id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getPwd(), now(), user.getId());
    }

    public User getUser(Long id) {
        String sql = "select * from auth_user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
    }

    public Collection<User> listUsers(Collection<Long> ids) {
        StringBuffer sql = new StringBuffer("select * from auth_user where id in (");
        for (Long id : ids) {
            sql.append(id).append("?,");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(")");
        return jdbcTemplate.query(sql.toString(), ids.toArray(new Object[0]), new UserMapper());
    }

    public Collection<User> listUsers(int page, int size) {
        return null;
    }

}