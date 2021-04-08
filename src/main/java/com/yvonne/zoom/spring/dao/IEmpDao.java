package com.yvonne.zoom.spring.dao;

import com.yvonne.zoom.spring.domain.Emp;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Yvonne Wang
 */
public interface IEmpDao {

    List<Emp> findAll() throws SQLException;
    int save(Emp emp) throws SQLException;
    int update(Emp emp) throws SQLException;
    int delete(int empno) throws SQLException;
    Emp findEmpByNo(int empno) throws SQLException;

}
