package com.yvonne.zoom.spring.service.impl;

import com.yvonne.zoom.spring.dao.IEmpDao;
import com.yvonne.zoom.spring.dao.impl.EmpDaoImpl2;
import com.yvonne.zoom.spring.domain.Emp;
import com.yvonne.zoom.spring.service.IEmpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yvonne Wang
 * @date 2021/4/722:30
 */

@Service("empService2")
public class EmpServiceImpl2 implements IEmpService {

    @Resource
    private IEmpDao empDao2;

    @Override
    public List<Emp> findAll() {
        try {
            return empDao2.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Emp emp) {
        try {
            return empDao2.save(emp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Emp emp) {
        try {
            return empDao2.update(emp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int empno) {
        try {
            return empDao2.delete(empno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Emp findEmpByNo(int empno) {
        try {
            return empDao2.findEmpByNo(empno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
