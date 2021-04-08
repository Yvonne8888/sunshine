package com.yvonne.zoom.mybatis.dao;

import com.yvonne.zoom.mybatis.domain.Emp;

import java.util.List;

/**
 * @author Yvonne Wang
 */
public interface IEmpDao {

    List<Emp> findAll(String mapperRelation);
    List<Emp> findEmpsByFuzzy(String mapperRelation, String fuzzy);
    void save(String mapperRelation, Emp emp);
    void remove(String mapperRelation, int empno);
}
