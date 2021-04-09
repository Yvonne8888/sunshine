package com.yvonne.zoom.mybatis.mapper;

import com.yvonne.zoom.mybatis.domain.Dept;
import com.yvonne.zoom.mybatis.domain.DeptCustom;

import java.util.List;

/**
 * @author Yvonne Wang
 */
public interface IDeptMapper2 {
    List<DeptCustom> findAllDepts1();

    List<Dept> findAllDepts2();

    List<Dept> findAllDepts3();

    List<Dept> findAllDepts4();
}
