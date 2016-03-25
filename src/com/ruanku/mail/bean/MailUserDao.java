package com.ruanku.mail.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ruanku.util.AppException;
import com.ruanku.util.DBUtil;

public class MailUserDao {
	
	public boolean findReset(String name) throws AppException {
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
					String sql = "select * from reset where name = ?";
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
					throw new AppException("com.ruanku.dao.impl.MailUserDao.findReset");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException("com.ruanku.dao.impl.MailUserDao.findReset");
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
	
	public boolean findUserTemp(String account) throws AppException {
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
					String sql = "select * from usertemp where account = ?";
					// 预处理，设置参数
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, account);
					// 执行查询操作
					rs = psmt.executeQuery();
					// 查到用户存在则flag为true
					if (rs.next()) {
						flag = true;
					}

				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException("com.ruanku.dao.impl.MailUserDao.findUserTemp");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException("com.ruanku.dao.impl.MailUserDao.findUserTemp");
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
	
	public boolean addUserTemp(String account, String password, String mail,
			String name, String address, String tel) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into usertemp (account,password,mail,name,address,tel,del,money,pay_pwd"
				+ ") values (?,?,?,?,?,?,0,0,'123')";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, account);
			psmt.setString(2, password);
			psmt.setString(3, mail);
			psmt.setString(4, name);
			psmt.setString(5, address);
			psmt.setString(6, tel);

			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.addUserTemp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.addUserTemp");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public boolean delUserTemp(String account) throws AppException {

		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from usertemp where account='" + account + "'";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);

			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.addUserTemp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.addUserTemp");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;

	}

	public boolean addVerify(String name, String namemd5, String randMD5)
			throws AppException {

		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into verify (name,name_md5,randMD5) values (?,?,?)";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, namemd5);
			psmt.setString(3, randMD5);

			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.addVerify");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.addVerify");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;

	}

	public boolean delVerify(String name) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from verify where name='" + name + "'";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);

			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.delVerify");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.delVerify");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public String getVerify(String nameMd5, String randMd5) throws AppException {
		// 数据库操作对象和编号
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String name = null;
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句
			String sql = "select name from verify where name_md5=? and randMd5=?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nameMd5);
			psmt.setString(2, randMd5);
			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				name = rs.getString("name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.getVerify");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.MailUserDao.getVerify");
		} finally {
			// 关闭数据库查询结果集
			DBUtil.closeResultSet(rs);
			// 关闭数据库查询指令
			DBUtil.closeStatement(psmt);
			// 关闭数据库连接，释放资源
			DBUtil.closeConnection(conn);
		}
		return name;
	}

	public boolean addUser(String account) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();

		// String
		// sql="insert into student select * from studentTemp where stu_name='"+stu_name+"'";

		// 提供SQL语句
		String sql = "insert into user(account,password,mail,name,address,tel,del,money,pay_pwd)"
				+ " select account,password,mail,name,address,tel,del,money,pay_pwd"
				+ " from usertemp where account=?";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, account);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.addUserTemp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.addUserTemp");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public String getUserName(String mail) throws AppException {

		String account = null;
		// 数据库操作对象和编号
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句
			String sql = "select account from user where mail='" + mail + "'";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);

			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				account = rs.getString("account");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.getUserName");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.MailUserDao.getUserName");
		} finally {
			// 关闭数据库查询结果集
			DBUtil.closeResultSet(rs);
			// 关闭数据库查询指令
			DBUtil.closeStatement(psmt);
			// 关闭数据库连接，释放资源
			DBUtil.closeConnection(conn);
		}
		return account;
	}

	public boolean resetPassword(String account, String password)
			throws AppException {

		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "update user set password=? where account=?";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, password);
			psmt.setString(2, account);

			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.resetPassword");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.resetPassword");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public boolean existUser(String account, String password)
			throws AppException {

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
			String sql = "select id from user where account=? and password=?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, account);
			psmt.setString(2, password);
			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.existUser");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.existUser");
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

	public boolean addReset(String name, String nameMd5, String randMd5)
			throws AppException {
		boolean flag = false;

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into reset(name,name_md5,randMD5) values(?,?,?)";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, nameMd5);
			psmt.setString(3, randMd5);

			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.addReset");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.addReset");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public boolean delReset(String name) throws AppException {
		// String sql="delete from reset where stu_name='"+stu_name+"'";
		// sta.execute(sql);

		boolean flag = false;

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from reset where name='" + name + "'";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.delReset");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.mail.bean.MailUserDao.delReset");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public String getLegalReset(String nameMd5, String randMd5)
			throws AppException {

		// 数据库操作对象和编号
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String name = null;
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句
			String sql = "select name, timestampdiff(hour,sent_time,now()) as hours from reset where name_md5=? and randMd5=?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nameMd5);
			psmt.setString(2, randMd5);
			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {

				int h = rs.getInt("hours");
				if (h <= 24) {

					name = rs.getString("name");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.mail.bean.MailUserDao.getLegalReset");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.MailUserDao.getLegalReset");
		} finally {
			// 关闭数据库查询结果集
			DBUtil.closeResultSet(rs);
			// 关闭数据库查询指令
			DBUtil.closeStatement(psmt);
			// 关闭数据库连接，释放资源
			DBUtil.closeConnection(conn);
		}
		return name;
	}

}
