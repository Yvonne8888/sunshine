package com.yvonne.zoom.spring.dao.impl;

import com.yvonne.zoom.spring.dao.IEmpDao;
import com.yvonne.zoom.spring.domain.Emp;
import com.yvonne.zoom.spring.utils.JdbcUtils;
import com.yvonne.zoom.spring.utils.JdbcUtils1;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/04/06 15:20
 **/
@Repository("empDao1")
public class EmpDaoImpl implements IEmpDao {
    QueryRunner qr;

    public EmpDaoImpl() {
        qr = new QueryRunner(JdbcUtils.getDataSource());
    }

    @Override
    public List<Emp> findAll() throws SQLException {
        String sql = "SELECT * FROM emp";
        BeanListHandler<Emp> empBeanListHandler = new BeanListHandler<>(Emp.class);
        List<Emp> query = qr.query(sql, empBeanListHandler);
        return query;
    }

    @Override
    public int save(Emp emp) throws SQLException {
        return 0;
    }

    @Override
    public int update(Emp emp) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int empno) throws SQLException {
        return 0;
    }

    @Override
    public Emp findEmpByNo(int empno) throws SQLException {
        return null;
    }
}
