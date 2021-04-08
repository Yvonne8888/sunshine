package com.yvonne.zoom.mybatis.dao.impl;

import com.yvonne.zoom.mybatis.dao.IEmpDao;
import com.yvonne.zoom.mybatis.domain.Emp;
import com.yvonne.zoom.mybatis.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/822:26
 */
public class EmpDaoImpl implements IEmpDao {

    @Override
    public List<Emp> findAll(String mapperRelation) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        return sqlSession.selectList(mapperRelation);
    }

    @Override
    public List<Emp> findEmpsByFuzzy(String mapperRelation, String fuzzy) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        return sqlSession.selectList(mapperRelation, fuzzy);
    }

    @Override
    public void save(String mapperRelation, Emp emp) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        sqlSession.update(mapperRelation, emp);
    }

    @Override
    public void remove(String mapperRelation, int empno) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        sqlSession.update(mapperRelation, empno);
    }
}
