package com.yvonne.zoom.spring.proxy;


import com.yvonne.zoom.spring.dao.IUserDao;

/**
 * 静态代理设计模式
 * 局限性：代理类与目标类必须实现相同的接口。
 * @author Yvonne Wang
 *
 */
public class UserDaoStaticProxy implements IUserDao{
	private IUserDao userDao;
	public UserDaoStaticProxy(IUserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public void add() {
		checkSecurity();
		userDao.add();
	}
	@Override
	public void update() {
		checkSecurity();
		userDao.update();
	}
	@Override
	public void delete() {
		userDao.delete();
	}
	@Override
	public void query() {
		userDao.query();
	}
	private void checkSecurity(){
		System.out.println("进行安全性查询。。。");
	}
}
