package com.yvonne.zoom.spring.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.yvonne.zoom.spring.dao.IStudentDao;
import com.yvonne.zoom.spring.domain.Student;
import com.yvonne.zoom.spring.service.IStudentService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author Yvonne Wang
 */
@Service("studentService")
@Scope(scopeName="prototype")
public class StudentServiceImpl implements IStudentService {

	/**
	 * 使用@Resource注解从容器中取出名为"studentDao"的哪个javabean
	 */
	@Resource(name="studentDao")
	private IStudentDao studentDao;
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public List<Student> findAll() throws Exception {
		return studentDao.findAll();
	}

}
