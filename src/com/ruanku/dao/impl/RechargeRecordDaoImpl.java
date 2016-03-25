package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruanku.dao.RechargeRecordDao;
import com.ruanku.model.RechargeRecord;
import com.ruanku.util.AppException;
import com.ruanku.util.DBUtil;

public class RechargeRecordDaoImpl implements RechargeRecordDao {

	@Override
	public boolean reCharge(RechargeRecord rechargeRecord) throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		//String sql = "insert into recharge_record (user_id, serial_number, money, status, time) values (?,?,?,?,?)";
		String sql = "insert into recharge_record (user_id, serial_number, money, status) values (?,?,?,?)";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, rechargeRecord.getUserId());
			psmt.setString(2, rechargeRecord.getSerialNum());
			psmt.setDouble(3,rechargeRecord.getMoney());
			psmt.setInt(4, rechargeRecord.getStatus());
			//psmt.setString(5, rechargeRecord.getTime());
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.RechargeReordImpl.reCharge");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.RechargeReordImpl.reCharge");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	@Override
	public boolean deleteRechargeRecord(int id) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from recharge_record where id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.RechargeRecordImpl.deleteRechargeRecord");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.RechargeRecordImpl.deleteRechargeRecord");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	@Override
	public List<RechargeRecord> getAllRechargeRecords() throws AppException {
		List<RechargeRecord> list = new ArrayList<RechargeRecord>();
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select * from recharge_record";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				RechargeRecord record = new RechargeRecord();
				record.setId(rs.getInt("id"));
				record.setMoney(rs.getDouble("money"));
				record.setSerialNum(rs.getString("serial_number"));
				record.setStatus(rs.getInt("status"));
				record.setTime(rs.getString("time"));
				record.setUserId(rs.getInt("user_id"));
				list.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.RechargeRecordImpl.getAllRechargeRecords");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.RechargeRecordImpl.getAllRechargeRecords");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	public List<RechargeRecord> getRechargeRecordsByUserid(int user_id) throws AppException {
		List<RechargeRecord> list = new ArrayList<RechargeRecord>();
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select * from recharge_record where user_id = ? ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, user_id);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				RechargeRecord record = new RechargeRecord();
				record.setId(rs.getInt("id"));
				record.setMoney(rs.getDouble("money"));
				record.setSerialNum(rs.getString("serial_number"));
				record.setStatus(rs.getInt("status"));
				record.setTime(rs.getString("time"));
				record.setUserId(rs.getInt("user_id"));
				list.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.RechargeRecordImpl.getRechargeRecordsByUserid");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.RechargeRecordImpl.getRechargeRecordsByUserid");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<RechargeRecord> getRechargeRecordsByUseridAndStatus(int user_id, int status)
			throws AppException {
		List<RechargeRecord> list = new ArrayList<RechargeRecord>();
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select * from recharge_record where user_id = ? and status = ? ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, user_id);
			psmt.setInt(2, status);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				RechargeRecord record = new RechargeRecord();
				record.setId(rs.getInt("id"));
				record.setMoney(rs.getDouble("money"));
				record.setSerialNum(rs.getString("serial_number"));
				record.setStatus(rs.getInt("status"));
				record.setTime(rs.getString("time"));
				record.setUserId(rs.getInt("user_id"));
				list.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.RechargeRecordImpl.getRechargeRecordsByUserid");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.RechargeRecordImpl.getRechargeRecordsByUserid");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}
}
