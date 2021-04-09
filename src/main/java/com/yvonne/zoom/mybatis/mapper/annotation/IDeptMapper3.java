package com.yvonne.zoom.mybatis.mapper.annotation;

import com.yvonne.zoom.mybatis.domain.Dept;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Yvonne Wang
 */
public interface IDeptMapper3 {
    @Results(id="deptRS2", value={
            @Result(column="dname",property="dnameX"),
            @Result(column="loc",property="locX"),
            @Result(column="deptno",property="deptno",id=true,
                    one=@One(select="com.yvonne.zoom.mybatis.mapper.IDeptMapper3.findEmpsByDeptno",
                            fetchType= FetchType.LAZY))
    })

    /**
     * 注解的一对一查询，利用懒加载模式
     */
    @Select("SELECT * FROM dept")
    List<Dept> findAll();
}
