package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ruanku.dao.ClothesOfOrderDao;
import com.ruanku.model.COOBusi;
import com.ruanku.model.COOBusi;
import com.ruanku.model.ClothesOfOrder;
import com.ruanku.util.AppException;
import com.ruanku.util.DBUtil;

public class ClothesOfOrderDaoImpl implements ClothesOfOrderDao {


	@Override
	public List<COOBusi> findCOOBusi(int orderId) throws AppException {
		List<COOBusi> list = new ArrayList<COOBusi>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select o.id,o.order_id,o.cloth_id,o.amount ,o.wash_mode" +
					" ,o.cloth_name ,o.price, c.shop_id, c.type ," +
					"c.picture,c.wash_mode,c.comment, c.del from clothes_of_order o " +
					", clothes c where o.cloth_id=c.id and o.order_id = ?;";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, orderId);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				COOBusi cooBusi = new COOBusi();
				cooBusi.setAmount(rs.getInt("o.amount"));
				cooBusi.setClothId(rs.getInt("o.cloth_id"));
				cooBusi.setClothName(rs.getString("o.cloth_name"));
				cooBusi.setCOOid(rs.getInt("o.id"));
				cooBusi.setDel(rs.getInt("c.del"));
				cooBusi.setName(rs.getString("o.cloth_name"));
				cooBusi.setComment(rs.getString("c.comment"));
				cooBusi.setPrice(rs.getDouble("o.price"));
				cooBusi.setPicture(rs.getString("c.picture"));
				cooBusi.setShopId(rs.getInt("c.shop_id"));
				cooBusi.setType(rs.getInt("c.type"));
				cooBusi.setWashMode(rs.getInt("c.wash_mode"));
				list.add(cooBusi);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.COOBusiDaoImpl.findCOOBusi");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.COOBusiDaoImpl.findCOOBusi");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public boolean save(ClothesOfOrder items) throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into clothes_of_order (order_id, cloth_id, amount, wash_mode, cloth_name, price) values (?,?,?,?,?,?)";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, items.getOrderId());
			psmt.setInt(2, items.getClothId());
			psmt.setInt(3, items.getAmount());
			psmt.setInt(4, items.getWashMode());
			psmt.setString(5, items.getClothName());
			psmt.setDouble(6, items.getPrice());

			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.COOBusiDaoImpl.save");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.COOBusiDaoImpl.save");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public List<ClothesOfOrder> findClothesOfOrder(int orderId)
			throws AppException {
		List<ClothesOfOrder> list = new ArrayList<ClothesOfOrder>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select * from clothes_of_order where order_id = ?;";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, orderId);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				ClothesOfOrder clothesOfOrder = new ClothesOfOrder();
				clothesOfOrder.setId(rs.getInt("id"));
				clothesOfOrder.setClothName(rs.getString("cloth_name"));
				clothesOfOrder.setAmount(rs.getInt("amount"));
				clothesOfOrder.setWashMode(rs.getInt("wash_mode"));
				clothesOfOrder.setClothId(rs.getInt("cloth_id"));
				clothesOfOrder.setPrice(rs.getDouble("price"));
				list.add(clothesOfOrder);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.COOBusiDaoImpl.findClothesOfOrder");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.COOBusiDaoImpl.findClothesOfOrder");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public boolean multiSave(List<ClothesOfOrder> items) throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into clothes_of_order (order_id, cloth_id, amount, wash_mode, cloth_name, price) values ";
		String value = "";
		for(ClothesOfOrder item:items){
		    value += "(" + item.getOrderId() + "," + item.getClothId() + "," + 
		    		item.getAmount() + "," + item.getWashMode() + ",'" + item.getClothName() + "'," + 
		    		item.getPrice() + "),";
		}
		value = value.substring(0,value.length()-1);
		sql += value;
		
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
			throw new AppException("com.ruanku.dao.impl.COOBusiDaoImpl.multiSave");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.COOBusiDaoImpl.multiSave");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
}
