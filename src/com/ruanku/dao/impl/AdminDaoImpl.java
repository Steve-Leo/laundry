package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.ruanku.dao.AdminDao;
import com.ruanku.model.PageBean;
import com.ruanku.model.Shop;
import com.ruanku.model.User;
import com.ruanku.util.AppException;
import com.ruanku.util.Constant;
import com.ruanku.util.DBUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 管理员模块数据通信DAO实现
 * @author brady
 *
 */
public class AdminDaoImpl implements AdminDao{
		
	private Connection connection = DBUtil.getConnection();

	/**
	 * 获取当前有效用户数量
	 */
	@Override
	public int getUserCount(String account) throws AppException {
		StringBuffer sql = new StringBuffer("select count(*) as total from user where del=?");
		if (account != null && !account.equals("")) {
			sql.append(" and account like '%"+account+"%'");
		}
		int result=0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setInt(1, 0);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取当前有效店铺数量
	 */
	@Override
	public int getShopCount(int state, String shopName) throws AppException {
		StringBuffer sql = new StringBuffer("select count(*) as total from shop where del=?");
		if (state > -1) {
			sql.append(" and state=?");
		}
		if (shopName != null && !shopName.equals("")) {
			sql.append(" and shopname like '%"+shopName+"%'");
		}
		int result=0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setInt(1, 0);
			if (state>-1) {
				preparedStatement.setInt(2, state);
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取当前有效用户信息
	 */
	@Override
	public ResultSet getUsersInfo(PageBean pageBean, String account) throws AppException {
		StringBuffer sql = new StringBuffer("select * from user where del=? ");
		if (account != null && !account.equals("")) {
			sql.append("and account like '%" + account+"%'");
		}
		sql.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setInt(1, 0);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * 获取当前有效店铺信息
	 */
	@Override
	public ResultSet getShopsInfo(PageBean pageBean, String shopName) throws AppException {
		StringBuffer sql = new StringBuffer("select * from shop where del=? ");
		if (shopName != null && !shopName.equals("")) {
			sql.append(" and shopname like '%"+shopName+"%'");
		}
		sql.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setInt(1, 0);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * 获取店铺统计信息
	 */
	@Override
	public ResultSet getStatistics(PageBean pageBean, String formatMonth, String shopName) throws AppException {
		StringBuffer sql = new StringBuffer("select shop_id,shopname,num,total_amount from " + 
				"(select os.shop_id ,max(shopname) as shopname,count(id) as num," +
				"sum(total_amount) as total_amount from "+
				"(select o.*,s.shopname from orders o, shop s where o.shop_id=s.id and "+
				"DATE_FORMAT(pay_time, '%Y-%m')=? and o.state<>-1 ");
		if (shopName != null && !shopName.equals("")) {
			sql.append(" and shopname like '%"+shopName+"%'");
		}
		sql.append(") os GROUP BY os.shop_id) as stas"+" limit " + pageBean.getStart() + "," + pageBean.getRows());
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			if (formatMonth==null) {
				String fmDate= new SimpleDateFormat("yyyy-MM").format(new Date());
				preparedStatement.setString(1, fmDate);
			}else {
				preparedStatement.setString(1, formatMonth);
			}
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * 获取店铺申请
	 */
	@Override
	public ResultSet getApplications(PageBean pageBean) throws AppException {
		String sql = "select * from shop where state=? limit " + pageBean.getStart() + "," + pageBean.getRows();
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 1);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * 获取当前有效统计信息总数
	 */
	@Override
	public int getStaCount(String formatMonth, String shopName) throws AppException {
		StringBuffer sql = new StringBuffer("select count(*) as total from " + 
				"(select os.shop_id ,max(shopname) ,count(id) ,count(total_amount) from "+
				"(select o.*,s.shopname from orders o, shop s where o.shop_id=s.id and "+
				"DATE_FORMAT(pay_time, '%Y-%m')=? and o.state<>-1 ");
		if (shopName != null && !shopName.equals("")) {
			sql.append(" and shopname like '%" +shopName+"%'");
		}
		sql.append(") os GROUP BY os.shop_id) as stas");
		int result=0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			if (formatMonth==null) {
				String fmDate= new SimpleDateFormat("yyyy-MM").format(new Date());
				preparedStatement.setString(1, fmDate);
			}else {
				preparedStatement.setString(1, formatMonth);
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public int updateUser(User user) throws AppException {
		String sqlString = "update user set address=?,tel=?,mail=?,money=?,name=? where id=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user.getAddress());
			preparedStatement.setString(2, user.getTel());
			preparedStatement.setString(3, user.getMail());
			preparedStatement.setDouble(4, user.getMoney());
			preparedStatement.setString(5, user.getName());
			preparedStatement.setInt(6, user.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除用户
	 */
	@Override
	public int deleteUser(String userId) throws AppException {
		String sqlString = "update user set del=? where id=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, Integer.parseInt(userId));
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 批量删除用户
	 */
	@Override
	public int deleteUsers(String userIds) throws AppException {
		String sql = "update user set del=? where id in ("+userIds+")";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 1);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 更新店铺信息
	 */
	@Override
	public int updateShop(Shop shop) throws AppException {
		String sqlString = "update shop set shopname=?,address=?,tel=?,comment=?,money=? where id=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, shop.getShopname());
			preparedStatement.setString(2, shop.getAddress());
			preparedStatement.setString(3, shop.getTel());
			preparedStatement.setString(4, shop.getComment());
			preparedStatement.setFloat(5, shop.getMoney());
			preparedStatement.setInt(6, shop.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *删除店铺
	 */
	@Override
	public int deleteShop(String shopId) throws AppException {
		String sqlString = "update shop set del=? where id=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, Integer.parseInt(shopId));
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 批量删除店铺
	 */
	@Override
	public int deleteShops(String shopIds) throws AppException {
		String sql = "update shop set del=? where id in ("+shopIds+")";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 1);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 对店铺申请进行审批
	 */
	@Override
	public int shopApprove(String shopId, String approve) throws AppException {
		String sqlString = "update shop set state=? where id=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, Integer.parseInt(approve));
			preparedStatement.setInt(2, Integer.parseInt(shopId));
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取统计信息详情
	 */
	@Override
	public ResultSet getDetailInfo(String shopId, String formatMonth, PageBean pageBean) {
		String sql = "select s.shopname,ordernum,u.account,DATE_FORMAT(pay_time, '%Y-%m-%d %h:%i %p') as payTime," +
				"total_amount,o.state as state,o.comment as comment from orders o,user u,shop s " +
				"where o.user_id=u.id and o.shop_id=s.id and o.shop_id=? and DATE_FORMAT(pay_time, '%Y-%m')=? and o.state<>-1 "+
				" limit "+pageBean.getStart()+", "+pageBean.getRows();
		if (shopId == null || formatMonth == null) {
			return null;
		}
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(shopId));
			preparedStatement.setString(2, formatMonth);
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * 获取统计信息详情总数
	 */
	@Override
	public int getDetailCount(String shopId, String formatMonth) {
		String sql = "select count(*) as total from orders o,user u,shop s " +
				"where o.user_id=u.id and o.shop_id=s.id and o.shop_id=? and DATE_FORMAT(pay_time, '%Y-%m')=?";
		int result = 0;
		ResultSet resultSet = null;
		if (shopId == null || formatMonth == null) {
			return result;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(shopId));
			preparedStatement.setString(2, formatMonth);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
