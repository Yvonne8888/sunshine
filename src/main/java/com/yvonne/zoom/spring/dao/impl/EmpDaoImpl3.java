package com.yvonne.zoom.spring.dao.impl;

import com.yvonne.zoom.spring.dao.IEmpDao;
import com.yvonne.zoom.spring.dao.IEmpTransferDao;
import com.yvonne.zoom.spring.domain.Emp;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/722:33
 */
//@Repository("empDao3")
public class EmpDaoImpl3 extends JdbcDaoSupport implements IEmpDao, IEmpTransferDao, RowMapper<Emp> {
    /**getJdbcTemplate().执行SQL */

    @Override
    //实现RowMapper<T>接口的映射方法
    public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Emp(rs.getInt("empno"), rs.getString("name"),rs.getInt("age"));
    }

    @Override
    public List<Emp> findAll() throws SQLException {
        System.out.println("方案2");
        String sql = "SELECT * FROM emp";
        return getJdbcTemplate().query(sql, this);
    }

    @Override
    public int save(Emp emp) throws SQLException {
        String sql = "INSERT INTO emp VALUES(?,?,?,?,?,?,?,?)";
        return getJdbcTemplate().update(sql, emp.getEmpno(), emp.getName(), emp.getAge());
    }

    @Override
    public int update(Emp emp) throws SQLException {
        String sql = "UPDATE emp SET name=?,age=? WHERE empno=?";
        return getJdbcTemplate().update(sql, emp.getName(), emp.getAge(), emp.getEmpno());
    }

    @Override
    public int delete(int empno) throws SQLException {
        String sql = "DELETE FROM emp WHERE empno=?";
        return getJdbcTemplate().update(sql, empno);
    }

    @Override
    public Emp findEmpByNo(int empno) throws SQLException {
        String sql = "SELECT * FROM emp WHERE empno=?";
        return getJdbcTemplate().queryForObject(sql, this, empno);
    }

    @Override
    public int outMoney(int empno, int money) throws SQLException {
        System.out.println("开始outMoney");
        String sql = "UPDATE emp SET age = age - ? WHERE empno = ?";
        return getJdbcTemplate().update(sql, money, empno);
    }

    @Override
    public int inMoney(int empno, int money) throws SQLException {
        System.out.println("开始inMoney");
        String sql = "UPDATE emp SET age = age + ? WHERE empno = ?";
        return getJdbcTemplate().update(sql, money, empno);
    }
}
