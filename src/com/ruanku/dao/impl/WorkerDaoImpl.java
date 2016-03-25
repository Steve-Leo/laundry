package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruanku.dao.WorkerDao;
import com.ruanku.model.Worker;
import com.ruanku.util.AppException;
import com.ruanku.util.DBUtil;

public class WorkerDaoImpl implements WorkerDao {

	@Override
	public boolean save(Worker worker) throws AppException {
		boolean flag = false;
		//判断房屋是否为空，是则不进行操作
		if(worker == null){
			return flag;
		}
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			String sql = "insert into worker (shop_id,name,sex," +
					"birthday,tel,origin)" +
					" values (?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, worker.getShopId());
			psmt.setString(2, worker.getName());
			psmt.setInt(3, worker.getSex());
			psmt.setString(4, worker.getBirthday());
			psmt.setString(5, worker.getTel());
			psmt.setString(6, worker.getOrigin());
			
			
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.save");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.save");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public List<Worker> findByShopId(int shopId) throws AppException {
		List<Worker> list = new ArrayList<Worker>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select id,shop_id,name,sex," +
					"birthday,tel,origin"+
			" from worker where shop_id=? order by id asc;";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, shopId);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Worker worker = new Worker();
				worker.setId(rs.getInt("id"));
				worker.setShopId(shopId);
				worker.setName(rs.getString("name"));
				worker.setSex(rs.getInt("sex"));
				worker.setBirthday(rs.getString("birthday"));
				worker.setTel(rs.getString("tel"));
				worker.setOrigin(rs.getString("origin"));		
				
				list.add(worker);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.findByShopId");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.findByShopId");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public boolean updateWorker(Worker worker) throws AppException {
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			
			String sql = "update worker set name=?,sex=?," +
					"birthday=?,tel=?,origin=? where id=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, worker.getName());
			psmt.setInt(2, worker.getSex());
			psmt.setString(3, worker.getBirthday());
			psmt.setString(4, worker.getTel());
			psmt.setString(5, worker.getOrigin());
			psmt.setInt(6, worker.getId());
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateWorker");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateWorker");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public List<Worker> findByName(String name) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public boolean delWorker(int id) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from worker where id = ?";
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
					"com.ruanku.dao.impl.workerDaoImpl.delWorker");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.workerDaoImpl.delWorker");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

}




