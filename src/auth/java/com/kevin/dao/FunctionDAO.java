package com.kevin.dao;

/*
 * Created by renhongjiang on 2019/1/18.
 */

import com.kevin.common.BaseDAO;
import com.kevin.entity.Function;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import static java.time.LocalDateTime.now;

/**
 * TODO the DAO(Data Access Object) of Function
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/18 11:06
 */
@Repository
public class FunctionDAO extends BaseDAO {

    private class FunctionMapper implements RowMapper<Function> {
        @Override
        public Function mapRow(ResultSet resultSet, int i) throws SQLException {
            Function function = new Function();
            function.setId(resultSet.getLong("id"));
            function.setName(resultSet.getString("name"));
            function.setParentId(resultSet.getLong("parent_id"));
            function.setUrl(resultSet.getString("url"));
            function.setSerialNum(resultSet.getInt("serial_num"));
            function.setAccordion(resultSet.getInt("accordion"));
            function.setGmtCreate(resultSet.getDate("gmt_create"));
            function.setGmtModified(resultSet.getDate("gmt_modified"));
            return function;
        }
    }

    /**
     * select function by name
     *
     * @param name name of function
     * @return the function entity
     */
    public Function getFunction(String name) {
        String sql = "select * from auth_function where name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new FunctionMapper());
    }


    public void saveFunction(Function function) {
        String sql = "insert into auth_function(id, name, parentId, url, serialNum, accordion, gmt_create) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, function.getId(), function.getName(), function.getParentId(), function.getUrl(), function.getSerialNum(), function.getAccordion(), now());
    }

    public void deleteFunction(Long id) {
        String sql = "delete from auth_function where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateFunction(Function function) {
        String sql = "update auth_function set name = ?, parentId = ?, url = ?, serialNum = ?, accordion = ?, gmt_modified = ? where id = ?";
        jdbcTemplate.update(sql, function.getName(), function.getParentId(), function.getUrl(), function.getSerialNum(), function.getAccordion(), now(), function.getId());
    }

    public void updateUrl(Long id, String url) {
        String sql = "update auth_function set url = ?, gmt_modified = ? where id = ?";
        jdbcTemplate.update(sql, url, now(), id);
    }

    public Function getFunction(Long id) {
        String sql = "select * from auth_function where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new FunctionMapper());
    }

    public Collection<Function> listFunctions(Collection<Long> ids) {
        StringBuffer sql = new StringBuffer("select * from auth_function where id in (");
        for (Long id : ids) {
            sql.append(id).append("?,");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(")");
        return jdbcTemplate.query(sql.toString(), ids.toArray(new Object[0]), new FunctionMapper());
    }

    public List<Function> listFunctions(int page, int size, Long parentId) {
        return null;
    }

    public List<Function> listFunctions() {
        String sql = "select * from auth_function";
        return jdbcTemplate.query(sql, new FunctionMapper());
    }

}