package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruanku.dao.UserDao;
import com.ruanku.model.User;
import com.ruanku.util.AppException;
import com.ruanku.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean isExist(String name) throws AppException {
		// 操作标记
		boolean flag = false;
		// 声明数据库连接对象，预编译对象和结果对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句：根据用户名查询
			String sql = "select id from user where account = ?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.isExist");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.isExist");
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
	public boolean save(User user) throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into user (account,password,address,tel,mail,money,picture,del,pay_pwd,name) values (?,?,?,?,?,?,?,?,?,?)";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getAccount());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getAddress());
			psmt.setString(4, user.getTel());
			psmt.setString(5, user.getMail());
			psmt.setDouble(6, user.getMoney());
			psmt.setString(7, user.getPicture());
			psmt.setInt(8, user.getDel());
			psmt.setString(9, user.getPayPwd());
			psmt.setString(10, user.getName());
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.save");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.save");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

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
			String sql = "select id from user where del = 0 and account = ? and password = ?";
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
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.login");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.login");
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
	public User getUser(int id) throws AppException {
		// 数据库操作对象和编号
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句
			String sql = "select * from user where id = ?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setAccount(rs.getString("account"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setTel(rs.getString("tel"));
				user.setMail(rs.getString("mail"));
				user.setMoney(rs.getFloat("money"));
				user.setDel(rs.getInt("del"));
				user.setPicture(rs.getString("picture"));
				user.setPayPwd(rs.getString("pay_pwd"));
				user.setName(rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.getUser");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.getUser");
		} finally {
			// 关闭数据库查询结果集
			DBUtil.closeResultSet(rs);
			// 关闭数据库查询指令
			DBUtil.closeStatement(psmt);
			// 关闭数据库连接，释放资源
			DBUtil.closeConnection(conn);
		}
		return user;
	}

	@Override
	public boolean changePassword(String account, String oldpassword,
			String newpassword) throws AppException {
boolean flag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			
			String sql = "update user set password =? where account =? and password =?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, newpassword);
			
			psmt.setString(2, account);
			psmt.setString(3, oldpassword);
			
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.ChangePassword");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.ChangePassword");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean UpdateUserInfo(int userId,String name,String address,String tel) throws AppException {
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			
			String sql = "update user set name=?,address=?," +
					"tel=? where id=? and del=0;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, name);
			psmt.setString(2, address);
			psmt.setString(3, tel);
			psmt.setInt(4, userId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.UpdateUserInfo");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.UpdateUserInfo");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	@Override
	public boolean changePayPassword(int id, String oldpassword,
			String newpassword) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "update user set pay_pwd = ? where id = ? and pay_pwd = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, newpassword);
			psmt.setInt(2, id);
			psmt.setString(3, oldpassword);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.changePayPassword");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.changePayPassword");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public List<User> getAllUser() throws AppException {
		List<User> list = new ArrayList<User>();

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select * from user";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setAccount(rs.getString("account"));
				user.setId(rs.getInt("id"));
				user.setAddress(rs.getString("address"));
				user.setDel(rs.getInt("del"));
				user.setMail(rs.getString("mail"));
				user.setMoney(rs.getDouble("money"));
				user.setPicture(rs.getString("picture"));
				user.setName(rs.getString("name"));
				user.setPayPwd(rs.getString("pay_pwd"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.getAllUser");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.getAllUser");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<User> getUserWithDel(int del) throws AppException {
		List<User> list = new ArrayList<User>();

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select * from user where del = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, del);
			rs = psmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setAccount(rs.getString("account"));
				user.setId(rs.getInt("id"));
				user.setAddress(rs.getString("address"));
				user.setDel(rs.getInt("del"));
				user.setMail(rs.getString("mail"));
				user.setMoney(rs.getDouble("money"));
				user.setPicture(rs.getString("picture"));
				user.setName(rs.getString("name"));
				user.setPayPwd(rs.getString("pay_pwd"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.getAllUser");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.getAllUser");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public boolean updateUserAccount(int id,double value) throws AppException {
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		boolean flag = false;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "update user set money = (money + ?) where id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setDouble(1, value);
			psmt.setInt(2, id);
			
			int result = psmt.executeUpdate();
			if(result > 0){
				flag = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.updateUserAccount");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.updateUserAccount");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean verifyUser(User user) throws AppException {
		String sql = "select count(*) from user where account=? and password=?";
		Connection connection = DBUtil.getConnection();
		boolean success = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getAccount());
			preparedStatement.setString(2, user.getPassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				success = resultSet.getInt(1)>0?true:false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return success;
		}finally{
			DBUtil.closeConnection(connection);
		}
		return success;
	}
}
