package com.yvonne.zoom.spring.service;

import com.yvonne.zoom.spring.domain.Emp;

import java.util.List;

public interface IEmpService {
    List<Emp> findAll();
    int save(Emp emp);
    int update(Emp emp);
    int delete(int empno);
    Emp findEmpByNo(int empno);
}
