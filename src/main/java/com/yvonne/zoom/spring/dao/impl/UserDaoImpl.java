package com.yvonne.zoom.spring.dao.impl;

import com.yvonne.zoom.spring.dao.IUserDao;
import org.springframework.stereotype.Repository;


//目标对象
@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	@Override
	public void add() {
		System.out.println("添加用户。");
//		int c = 10 / 0;
	}
	@Override
	public void update() {
		System.out.println("修改用户。");
	}
	@Override
	public void delete() {
		System.out.println("删除用户。");
	}
	@Override
	public void query() {
		System.out.println("查询用户。");
	}
}
