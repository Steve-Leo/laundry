package com.ruanku.dao;

import java.sql.ResultSet;

import com.ruanku.model.PageBean;
import com.ruanku.model.Shop;
import com.ruanku.model.User;
import com.ruanku.util.AppException;

/**
 * 管理员Dao
 * @author brady
 *
 */
public interface AdminDao {
	public int getUserCount(String account) throws AppException;
	public int getShopCount(int state, String shopName) throws AppException;
	/**
	 * 日期格式为"YYYY-mm"
	 * @param formatMonth
	 * @param shopName TODO
	 * @return
	 * @throws AppException
	 */
	public int getStaCount(String formatMonth, String shopName) throws AppException;
	public ResultSet getUsersInfo(PageBean pageBean, String account) throws AppException;
	public ResultSet getShopsInfo(PageBean pageBean, String shopName) throws AppException;
	/**
	 * 日期格式为"YYYY-mm"
	 * @param pageBean
	 * @param formatMonth
	 * @param shopName TODO
	 * @return
	 * @throws AppException
	 */
	public ResultSet getStatistics(PageBean pageBean, String formatMonth, String shopName) throws AppException;
	/**
	 * 店铺申请
	 * @param pageBean
	 * @return
	 * @throws AppException
	 */
	public ResultSet getApplications(PageBean pageBean) throws AppException;
	public int updateUser(User user) throws AppException;
	public int deleteUser(String userId) throws AppException;
	public int deleteUsers(String userIds) throws AppException;
	public int updateShop(Shop shop) throws AppException;
	public int deleteShop(String shopId) throws AppException;
	public int deleteShops(String shopIds) throws AppException;
	/**
	 * 店铺审批
	 * @param shopId
	 * @param approve
	 * @return
	 * @throws AppException
	 */
	public int shopApprove(String shopId, String approve) throws AppException;
	public ResultSet getDetailInfo(String shopId, String formatMonth, PageBean pageBean);
	public int getDetailCount(String shopId, String formatMonth);
}
