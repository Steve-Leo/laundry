package com.ruanku.dao;

import java.util.List;

import com.ruanku.model.User;
import com.ruanku.util.AppException;

public interface UserDao {
	/**
	 * 编写人：曾盼
	 * 查询是否有同名用户存在
	 * @param name 用户名
	 * @return 有同名返回true，否则返回false
	 * @throws AppException
	 */
	public boolean isExist(String name) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 保存用户信息
	 * @param user 用户对象
	 * @return 保存成功返回true,否则返回false
	 * @throws AppException
	 */
	public boolean save(User user) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 验证登录并获取用户id
	 * @param name 用户名
	 * @param password 密码
	 * @return id 用户编号
	 * @throws AppException
	 */
	public int login(String name,String password) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 根据id获取用户
	 * @param id
	 * @return user 用户
	 * @throws AppException
	 */
	public User getUser(int id) throws AppException;
	
	//根据用户名密码 更改password
	/**
	 * 编写人：曾盼
	 * 作用：修改某一用户的登录密码
	 * @param account 用户账号
	 * @param oldpassword 原密码
	 * @param newpassword 新密码
	 * @return 是否更改成功
	 * @throws AppException
	 */
	public boolean changePassword(String account,String oldpassword,String newpassword) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：修改某一个用户的信息
	 * @param id 用户id
	 * @param name 用户姓名
	 * @param address 用户地址
	 * @param tel 用户电话
	 * @return 修改信息是否成功
	 * @throws AppException
	 */
	public boolean UpdateUserInfo(int id,String name,String address,String tel) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：修改某一用户的资金额
	 * @param id
	 * @param value
	 * @return
	 * @throws AppException
	 */
	public boolean updateUserAccount(int id,double value) throws AppException;
	
	//根据用户支付密码和用户id 更改password
	/**
	 * 编写人：曾盼
	 * 根据用户支付密码和用户id 更改password
	 * @param id 用户id
	 * @param oldpassword 原支付密码
	 * @param newpassword 新支付密码
	 * @return 是否修改成功
	 * @throws AppException
	 */
	public boolean changePayPassword(int id,String oldpassword,String newpassword) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：返回所有用户信息
	 * @return 返回所有用户信息
	 * @throws AppException
	 */
	public List<User> getAllUser() throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：根据是否删除，返回所有用户信息
	 * @param del 是否删除
	 * @return 根据是否删除，返回所有用户信息
	 * @throws AppException
	 */
	public List<User> getUserWithDel(int del) throws AppException;

	/**
	 * 用户验证,by 杨扬
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public boolean verifyUser(User user) throws AppException;
}
