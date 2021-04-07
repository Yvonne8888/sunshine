package com.yvonne.zoom.spring.service;

import com.yvonne.zoom.spring.domain.Student;

import java.util.List;


/**
 * @author Yvonne Wang
 */
public interface IStudentService {

	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	public List<Student> findAll() throws Exception;
}
