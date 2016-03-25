package com.ruanku.service;

import java.sql.ResultSet;

import com.ruanku.dao.ManagerDao;
import com.ruanku.dao.impl.AdminDaoImpl;
import com.ruanku.dao.impl.ManagerDaoImpl;
import com.ruanku.model.Manager;
import com.ruanku.model.PageBean;
import com.ruanku.model.User;
import com.ruanku.model.Shop;
import com.ruanku.model.User;
import com.ruanku.util.AppException;

public class ManagerService {
	
	private ManagerDao managerDao = new ManagerDaoImpl();
	private AdminDaoImpl adminDaoImpl = new AdminDaoImpl();

	public Manager login(String name,String password) throws AppException{
		Manager manager = null;

		try {
			int id = managerDao.login(name,password);
			if(id > 0){
				manager = managerDao.getManager(id);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ManagerService.login");
		}
		return manager;
	}
	
	/**
	 * 统计用户数量
	 * @return
	 * @throws AppException
	 */
	public int userTotal(String account) throws AppException {
		return adminDaoImpl.getUserCount(account);
	}
	
	/**
	 * 统计店铺数量
	 * @return
	 * @throws AppException
	 */
	public int shopTotal(String shopName) throws AppException {
		return adminDaoImpl.getShopCount(-1, shopName);
	}
	/**
	 * 统计提交申请的店铺数量
	 * @return
	 * @throws AppException
	 */
	public int shopApplyTotal() throws AppException {
		return adminDaoImpl.getShopCount(1, null);
	}
	/**
	 * 店铺统计数据的条数
	 * @param formatMonth
	 * @return
	 * @throws AppException
	 */
	public int staTotal(String formatMonth, String shopName) throws AppException {
		return adminDaoImpl.getStaCount(formatMonth, shopName);
	}
	/**
	 * 查询用户信息
	 * @param pageBean
	 * @return
	 * @throws AppException
	 */
	public ResultSet getUsersInfo(PageBean pageBean, String account) throws AppException{
		return adminDaoImpl.getUsersInfo(pageBean, account);
	}
	/**
	 * 查询店铺信息
	 * @param pageBean
	 * @return
	 * @throws AppException
	 */
	public ResultSet getShopsInfo(PageBean pageBean,String shopName) throws AppException {
		return adminDaoImpl.getShopsInfo(pageBean, shopName);
	}
	/**
	 * 查询申请审批的店铺信息
	 * @param pageBean
	 * @return
	 * @throws AppException
	 */
	public ResultSet getApplications(PageBean pageBean) throws AppException {
		return adminDaoImpl.getApplications(pageBean);
	}
	/**
	 * 查询统计数据
	 * @param pageBean
	 * @param formatMonth
	 * @return
	 * @throws AppException
	 */
	public ResultSet getStatistics(PageBean pageBean,String formatMonth, String shopName) throws AppException {
		return adminDaoImpl.getStatistics(pageBean, formatMonth, shopName);
	}
	/**
	 * 更新店铺信息
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public int updateUser(User user) throws AppException {
		return adminDaoImpl.updateUser(user);
	}
	/**
	 * 标记用户为删除状态
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	public int deleteUser(String userId) throws AppException {
		return adminDaoImpl.deleteUser(userId);
	}
	/**
	 * 批量标记用户为删除状态
	 * @param userIds
	 * @return
	 * @throws AppException
	 */
	public int deleteUsers(String userIds) throws AppException {
		return adminDaoImpl.deleteUsers(userIds);
	}
	/**
	 * 更新店铺信息
	 * @param shop
	 * @return
	 * @throws AppException
	 */
	public int updateShop(Shop shop) throws AppException {
		return adminDaoImpl.updateShop(shop);
	}
	/**
	 * 标记店铺为删除状态
	 * @param shopId
	 * @return
	 * @throws AppException
	 */
	public int deleteShop(String shopId) throws AppException {
		return adminDaoImpl.deleteShop(shopId);
	}
	/**
	 * 批量标记店铺为删除状态
	 * @param shopIds
	 * @return
	 * @throws AppException
	 */
	public int deleteShops(String shopIds) throws AppException {
		return adminDaoImpl.deleteShops(shopIds);
	}
	/**
	 * 审批店铺
	 * @param shopId
	 * @param approve
	 * @return
	 * @throws AppException
	 */
	public int shopApprove(String shopId, String approve) throws AppException {
		return adminDaoImpl.shopApprove(shopId, approve);
	}
	
	/**
	 * 店铺统计详情查询
	 * @param shopId
	 * @param formatMonth
	 * @return
	 */
	public ResultSet getDetailInfo(String shopId, String formatMonth,PageBean pageBean) {
		return adminDaoImpl.getDetailInfo(shopId, formatMonth, pageBean);
	}
	/**
	 * 统计详情计数
	 * @param shopId
	 * @param formatMonth
	 * @return
	 */
	public int getDetailCount(String shopId, String formatMonth) {
		return adminDaoImpl.getDetailCount(shopId, formatMonth);
	}
}
