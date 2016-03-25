package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ruanku.dao.ManagerDao;
import com.ruanku.model.Manager;
import com.ruanku.util.AppException;
import com.ruanku.util.DBUtil;

public class ManagerDaoImpl implements ManagerDao {

	@Override
	public int login(String name, String password) throws AppException {
		// 数据库操作对象和编号
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int id = 0;

		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句
			String sql = "select id from super_admin where username = ? and password = ?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, password);
			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ManagerDaoImpl.login");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ManagerDaoImpl.login");
		} finally {
			// 关闭数据库查询结果集
			DBUtil.closeResultSet(rs);
			// 关闭数据库查询指令
			DBUtil.closeStatement(psmt);
			// 关闭数据库连接，释放资源
			DBUtil.closeConnection(conn);
		}
		return id;
	}

	@Override
	public Manager getManager(int id) throws AppException {
		// 数据库操作对象和编号
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Manager manager = new Manager();

		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句
			String sql = "select * from super_admin where id = ?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				manager.setId(rs.getInt("id"));
				manager.setName(rs.getString("username"));
				manager.setPassword(rs.getString("password"));
				manager.setMoney(rs.getFloat("money"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ManagerDaoImpl.getManager");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ManagerDaoImpl.getManager");
		} finally {
			// 关闭数据库查询结果集
			DBUtil.closeResultSet(rs);
			// 关闭数据库查询指令
			DBUtil.closeStatement(psmt);
			// 关闭数据库连接，释放资源
			DBUtil.closeConnection(conn);
		}
		return manager;
	}
	
	@Override
	public boolean managerExpend(int id,float expend) throws AppException {
		// 数据库操作对象和编号
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		float fund = 0.0f;
		
		int result;
		boolean flag = false;
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句
			// "select id from super_admin where username = ? and password = ?";
			String sql = "select money from super_admin where id = ?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setLong(1, id);

			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				fund = rs.getInt("money");
				System.out.println(fund);
				if (fund >= expend) {
					
					sql = "update  super_admin set money=money -? where id = ?";
					// 预处理，设置参数
					psmt = conn.prepareStatement(sql);
					psmt.setFloat(1, expend);
					psmt.setInt(2, id);
					
					result = psmt.executeUpdate();
					if(result >0) flag = true;
					
				}
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ManagerDaoImpl.managerExpend");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ManagerDaoImpl.managerExpend");
		} finally {
			// 关闭数据库查询结果集
			DBUtil.closeResultSet(rs);
			// 关闭数据库查询指令
			DBUtil.closeStatement(psmt);
			// 关闭数据库连接，释放资源
			DBUtil.closeConnection(conn);
		}
		return flag;
		
	}

	@Override
	public boolean managerIncome(int id,float income) throws AppException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		double fund = 0.0;
		double result = 0.0;

		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句
			String sql = "update super_admin set money=money+? where id = ?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setDouble(1, income);
			psmt.setLong(2, id);
			// 执行查询操作
			
			// 查到用户存在则flag为true
			if (psmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ManagerDaoImpl.login");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ManagerDaoImpl.login");
		} finally {
			// 关闭数据库查询结果集
			DBUtil.closeResultSet(rs);
			// 关闭数据库查询指令
			DBUtil.closeStatement(psmt);
			// 关闭数据库连接，释放资源
			DBUtil.closeConnection(conn);
		}
		return false;
	}

}
