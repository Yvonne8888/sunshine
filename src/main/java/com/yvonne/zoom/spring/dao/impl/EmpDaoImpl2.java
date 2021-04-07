package com.yvonne.zoom.spring.dao.impl;

import com.yvonne.zoom.spring.dao.IEmpDao;
import com.yvonne.zoom.spring.domain.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/722:15
 */
@Repository("empDao2")
public class EmpDaoImpl2 implements IEmpDao, RowMapper<Emp> {
    @Resource
    private JdbcTemplate jdbcTemplateX; //定义jdbc模板对象

    @Override
    //实现RowMapper<T>接口的映射方法
    public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Emp(rs.getInt("empno"), rs.getString("name"), rs.getInt("age"));
    }

    @Override
    public List<Emp> findAll() throws SQLException {
        System.out.println("方案1");
        String sql = "SELECT * FROM emp";
        return jdbcTemplateX.query(sql, this);
    }

    @Override
    public int save(Emp emp) throws SQLException {
        String sql = "INSERT INTO emp VALUES(?,?,?)";
        return jdbcTemplateX.update(sql, emp.getEmpno(), emp.getName(), emp.getAge());
    }

    @Override
    public int update(Emp emp) throws SQLException {
        String sql = "UPDATE emp SET name=?,age=? WHERE empno=?";
        return jdbcTemplateX.update(sql, emp.getName(), emp.getAge(), emp.getEmpno());
    }

    @Override
    public int delete(int empno) throws SQLException {
        String sql = "DELETE FROM emp WHERE empno=?";
        return jdbcTemplateX.update(sql, empno);
    }

    @Override
    public Emp findEmpByNo(int empno) throws SQLException {
        String sql = "SELECT * FROM emp WHERE empno=?";
        return jdbcTemplateX.queryForObject(sql, this, empno);
    }
}
