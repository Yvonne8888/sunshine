package com.yvonne.zoom.spring.dao;

import java.util.List;

import com.yvonne.zoom.spring.domain.Student;

/**
 * @author Yvonne Wang
 */
public interface IStudentDao {

	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	public List<Student> findAll() throws Exception;
}
