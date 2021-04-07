package com.yvonne.zoom.spring.dao.impl;

import java.util.List;

import com.yvonne.zoom.spring.dao.IStudentDao;
import com.yvonne.zoom.spring.domain.Student;
import com.yvonne.zoom.spring.utils.JdbcUtils1;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;


/**
 * @author Yvonne Wang
 */

//向容器中存放名为"studentDao"的javabean
@Repository("studentDao")
public class StudentDaoImpl implements IStudentDao {
	private QueryRunner qr;
	public StudentDaoImpl() {
		this.qr = new QueryRunner(JdbcUtils1.getDataSource());
	}
	@Override
	public List<Student> findAll() throws Exception {
		String sql = "select * from emp";
		return qr.query(sql, new BeanListHandler<>(Student.class));
	}

}
