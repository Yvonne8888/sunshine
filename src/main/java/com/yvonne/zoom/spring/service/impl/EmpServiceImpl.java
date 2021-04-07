package com.yvonne.zoom.spring.service.impl;

import com.yvonne.zoom.spring.dao.impl.EmpDaoImpl;
import com.yvonne.zoom.spring.dao.IEmpDao;
import com.yvonne.zoom.spring.domain.Emp;
import com.yvonne.zoom.spring.service.IEmpService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/70:48
 */
@Service("empService")
@Scope(scopeName = "prototype")
public class EmpServiceImpl implements IEmpService {

    @Resource(name = "empDao1")
    private IEmpDao empDao;

    public EmpServiceImpl() {
        empDao = new EmpDaoImpl();
    }

    public void setEmpDao(IEmpDao empDao) {
        this.empDao = empDao;
    }

    @Override
    public List<Emp> findAll() {
        try {
            return empDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Emp emp) {
        try {
            return empDao.save(emp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Emp emp) {
        try {
            return empDao.update(emp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int empno) {
        try {
            return empDao.delete(empno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Emp findEmpByNo(int empno) {
        try {
            return empDao.findEmpByNo(empno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
