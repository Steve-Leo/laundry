package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruanku.dao.OrderDao;
import com.ruanku.model.COOBusi;
import com.ruanku.model.ClothesOfOrder;
import com.ruanku.model.Order;
import com.ruanku.model.OrderBusiUser;
import com.ruanku.model.OrderBusiShop;
import com.ruanku.model.Statistics;
import com.ruanku.util.AppException;
import com.ruanku.util.Constant;
import com.ruanku.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public int save(Order order) throws AppException {
		// 操作标记
		int id = 0;
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into orders (user_id,shop_id,ordernum,state,"
				+ "receiver,address,tel,pay_time,recieved_time,charge_time,"
				+ "comment,comment_time,total_amount,pay_mode) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, order.getUserId());
			psmt.setInt(2, order.getShopId());
			psmt.setString(3, order.getOrdernum());
			psmt.setInt(4, order.getState());
			psmt.setString(5, order.getReceiver());
			psmt.setString(6, order.getAddress());
			psmt.setString(7, order.getTel());
			psmt.setString(8, order.getPaytime());
			psmt.setString(9, order.getReceivedTime());
			psmt.setString(10, order.getChargeTime());
			psmt.setString(11, order.getComment());
			psmt.setString(12, order.getCommentTime());
			psmt.setFloat(13, order.getTotalAmount());
			psmt.setInt(14, order.getPayMode());
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				// flag = true;
				psmt = conn.prepareStatement("select last_insert_id() as id");
				ResultSet rs = psmt.executeQuery();
				if (rs.next()) {
					id = rs.getInt("id");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.OrderDaoImpl.save");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.OrderDaoImpl.save");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return id;
	}

	@Override
	public List<Order> findAllOrder() throws AppException {
		List<Order> list = new ArrayList<Order>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select user_id,shop_id,ordernum,state," +
			"receiver,address,tel,pay_time,recieved_time,charge_time," +
			"comment,comment_time,total_amount,pay_mode,id"+
			" from orders ;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setUserId(rs.getInt("user_id"));
				order.setShopId(rs.getInt("shop_id"));
				order.setOrdernum(rs.getString("ordernum"));
				order.setState(rs.getInt("state"));
				order.setReceiver(rs.getString("receiver"));
				order.setAddress(rs.getString("address"));
				order.setTel(rs.getString("tel"));
				order.setPaytime(rs.getString("pay_time"));
				order.setReceivedTime(rs.getString("recieved_time"));
				order.setChargeTime(rs.getString("charge_time"));
				order.setComment(rs.getString("comment"));
				order.setCommentTime(rs.getString("comment_time"));
				order.setTotalAmount(rs.getFloat("total_amount"));
				order.setPayMode(rs.getInt("pay_mode"));
				order.setId(rs.getInt("id"));
				list.add(order);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findAllOrder");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findAllOrder");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<Order> findOrderByShopId(int ShopId,int page,int type) throws AppException {
		List<Order> list = new ArrayList<Order>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select user_id,shop_id,ordernum,state," +
			"receiver,address,tel,pay_time,recieved_time,charge_time," +
			"comment,comment_time,total_amount,pay_mode,id"+
			" from orders where shop_id =?";
			
			if(type!=0){
				sql += " and state="+type;
			}
			sql += " order by pay_time desc";
			sql += " limit "+((page-1)*10)+","+10;
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ShopId);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setUserId(rs.getInt("user_id"));
				order.setShopId(ShopId);
				order.setOrdernum(rs.getString("ordernum"));
				order.setState(rs.getInt("state"));
				order.setReceiver(rs.getString("receiver"));
				order.setAddress(rs.getString("address"));
				order.setTel(rs.getString("tel"));
				order.setPaytime(rs.getString("pay_time"));
				order.setReceivedTime(rs.getString("recieved_time"));
				order.setChargeTime(rs.getString("charge_time"));
				order.setComment(rs.getString("comment"));
				order.setCommentTime(rs.getString("comment_time"));
				order.setTotalAmount(rs.getFloat("total_amount"));
				order.setPayMode(rs.getInt("pay_mode"));
				order.setId(rs.getInt("id"));
				list.add(order);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByShopId");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByShopId");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<Order> findOrderByUserId(int UserId) throws AppException {
		List<Order> list = new ArrayList<Order>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select user_id,shop_id,ordernum,state," +
			"receiver,address,tel,pay_time,recieved_time,charge_time," +
			"comment,comment_time,total_amount,pay_mode,id"+
			" from orders where user_id =?;";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, UserId);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setUserId(UserId);
				order.setShopId(rs.getInt("shop_id"));
				order.setOrdernum(rs.getString("ordernum"));
				order.setState(rs.getInt("state"));
				order.setReceiver(rs.getString("receiver"));
				order.setAddress(rs.getString("address"));
				order.setTel(rs.getString("tel"));
				order.setPaytime(rs.getString("pay_time"));
				order.setReceivedTime(rs.getString("recieved_time"));
				order.setChargeTime(rs.getString("charge_time"));
				order.setComment(rs.getString("comment"));
				order.setCommentTime(rs.getString("comment_time"));
				order.setTotalAmount(rs.getFloat("total_amount"));
				order.setPayMode(rs.getInt("pay_mode"));
				order.setId(rs.getInt("id"));
				list.add(order);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByUserId");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByUserId");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<Order> findOrderByShopIdAndState(int ShopId, int state)
			throws AppException {
List<Order> list = new ArrayList<Order>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select user_id,shop_id,ordernum,state," +
			"receiver,address,tel,pay_time,recieved_time,charge_time," +
			"comment,comment_time,total_amount,pay_mode,id"+
			" from orders where shop_id =? and state=?;";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ShopId);
			psmt.setInt(2, state);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setUserId(rs.getInt("user_id"));
				order.setShopId(rs.getInt("shop_id"));
				order.setOrdernum(rs.getString("ordernum"));
				order.setState(rs.getInt("state"));
				order.setReceiver(rs.getString("receiver"));
				order.setAddress(rs.getString("address"));
				order.setTel(rs.getString("tel"));
				order.setPaytime(rs.getString("pay_time"));
				order.setReceivedTime(rs.getString("recieved_time"));
				order.setChargeTime(rs.getString("charge_time"));
				order.setComment(rs.getString("comment"));
				order.setCommentTime(rs.getString("comment_time"));
				order.setTotalAmount(rs.getFloat("total_amount"));
				order.setPayMode(rs.getInt("pay_mode"));
				order.setId(rs.getInt("id"));
				list.add(order);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByShopIdAndState");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByShopIdAndState");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<Order> findOrderByUserIdAndState(int UseId, int state)
			throws AppException {
List<Order> list = new ArrayList<Order>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select user_id,shop_id,ordernum,state," +
			"receiver,address,tel,pay_time,recieved_time,charge_time," +
			"comment,comment_time,total_amount,pay_mode,id"+
			" from orders where user_id =? and state=?;";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, UseId);
			psmt.setInt(2, state);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setUserId(rs.getInt("user_id"));
				order.setShopId(rs.getInt("shop_id"));
				order.setOrdernum(rs.getString("ordernum"));
				order.setState(rs.getInt("state"));
				order.setReceiver(rs.getString("receiver"));
				order.setAddress(rs.getString("address"));
				order.setTel(rs.getString("tel"));
				order.setPaytime(rs.getString("pay_time"));
				order.setReceivedTime(rs.getString("recieved_time"));
				order.setChargeTime(rs.getString("charge_time"));
				order.setComment(rs.getString("comment"));
				order.setCommentTime(rs.getString("comment_time"));
				order.setTotalAmount(rs.getFloat("total_amount"));
				order.setPayMode(rs.getInt("pay_mode"));
				order.setId(rs.getInt("id"));
				list.add(order);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByUserIdAndState");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByUserIdAndState");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public Order getOrderById(int orderId) throws AppException {
		// 数据库操作对象
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				// 获取数据库连接
				Order order = null;
				try {
					//创建数据库连接
					conn = DBUtil.getConnection();
					// 预处理，设置参数
					
					String sql = "select user_id,shop_id,ordernum,state," +
					"receiver,address,tel,pay_time,recieved_time,charge_time," +
					"comment,comment_time,total_amount,pay_mode,id"+
					" from orders where id =? ;";
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, orderId);
					
					rs = psmt.executeQuery();
					
					if(rs.next()){
						order = new Order();
						order.setUserId(rs.getInt("user_id"));
						order.setShopId(rs.getInt("shop_id"));
						order.setOrdernum(rs.getString("ordernum"));
						order.setState(rs.getInt("state"));
						order.setReceiver(rs.getString("receiver"));
						order.setAddress(rs.getString("address"));
						order.setTel(rs.getString("tel"));
						order.setPaytime(rs.getString("pay_time"));
						order.setReceivedTime(rs.getString("recieved_time"));
						order.setChargeTime(rs.getString("charge_time"));
						order.setComment(rs.getString("comment"));
						order.setCommentTime(rs.getString("comment_time"));
						order.setTotalAmount(rs.getFloat("total_amount"));
						order.setPayMode(rs.getInt("pay_mode"));
						order.setId(rs.getInt("id"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException("com.ruanku.dao.impl.orderDaoImpl.getOrderById");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException("com.ruanku.dao.impl.orderDaoImpl.getOrderById");
				} finally {
					// 关闭数据库连接，释放资源
					DBUtil.closeResultSet(rs);
					DBUtil.closeStatement(psmt);
					DBUtil.closeConnection(conn);
				}
				return order;
	}
	
	
	@Override
	public Order getOrderByOrdernum(String ordernum) throws AppException {
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		Order order = null;
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数

			String sql = "select user_id,shop_id,ordernum,state,"
					+ "receiver,address,tel,pay_time,recieved_time,charge_time,"
					+ "comment,comment_time,total_amount,pay_mode,id"
					+ " from orders where ordernum =? ;";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ordernum);

			rs = psmt.executeQuery();

			if (rs.next()) {
				order = new Order();
				order.setUserId(rs.getInt("user_id"));
				order.setShopId(rs.getInt("shop_id"));
				order.setOrdernum(rs.getString("ordernum"));
				order.setState(rs.getInt("state"));
				order.setReceiver(rs.getString("receiver"));
				order.setAddress(rs.getString("address"));
				order.setTel(rs.getString("tel"));
				order.setPaytime(rs.getString("pay_time"));
				order.setReceivedTime(rs.getString("recieved_time"));
				order.setChargeTime(rs.getString("charge_time"));
				order.setComment(rs.getString("comment"));
				order.setCommentTime(rs.getString("comment_time"));
				order.setTotalAmount(rs.getFloat("total_amount"));
				order.setPayMode(rs.getInt("pay_mode"));
				order.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.getOrderById");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.getOrderById");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return order;
	}

	@Override
	public boolean updateState(int orderId, int state) throws AppException {
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			
			String sql = "update orders set state=? where id=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, state);
			psmt.setInt(2, orderId);
			
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateState");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateState");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean updateComment(int orderId, String comment)
			throws AppException {
		// TODO Auto-generated method stub  Calendar.getInstance().getTimeInMillis(); 
		
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String commentTime = sdf.format(date);
		
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			
			String sql = "update orders set state=? , comment=?,comment_time =? where id=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, Constant.order.commented);
			psmt.setString(2, comment);
			psmt.setString(3, commentTime);
			psmt.setInt(4,orderId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateComment");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateComment");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean updateReceicedTime(int orderId)
			throws AppException {
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String receivedTime = sdf.format(date);
		
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			
			String sql = "update orders set state=? , recieved_time =? where id=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, Constant.order.received);
			psmt.setString(2, receivedTime);
			
			psmt.setInt(3,orderId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateReceicedTime");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateReceicedTime");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean updateChargeTime(int orderId) throws AppException {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String chargeTime = sdf.format(date);
		
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			
			String sql = "update orders set state=? , charge_time =? where id=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, Constant.order.paid);
			psmt.setString(2, chargeTime);
			
			psmt.setInt(3,orderId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateChargeTime");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateChargeTime");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean updateOrderPaid(int orderId, int mode, int state)
			throws AppException {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String chargeTime = sdf.format(date);
		
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			
			String sql = "update orders set state=? , charge_time =?,pay_mode=? where id=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, state);
			psmt.setString(2, chargeTime);
			psmt.setInt(3, mode);
			psmt.setInt(4,orderId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateOrderPaid");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateOrderPaid");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	@Override
	public boolean updateOrderPaidByOrdernum(String ordernum, int mode, int state)
			throws AppException {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String chargeTime = sdf.format(date);
		
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			
			
			String sql = "update orders set state=? , charge_time =?,pay_mode=? where ordernum=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, state);
			psmt.setString(2, chargeTime);
			psmt.setInt(3, mode);
			psmt.setString(4,ordernum);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}


		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateOrderPaidByOrdernum");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.workerDaoImpl.updateOrderPaidByOrdernum");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}


	@Override
	public List<Statistics> queryShopStatistics(int shopId) throws AppException {
		List<Statistics> list = new ArrayList<Statistics>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select extract(year_month from charge_time),sum(total_amount)" +
					",count(*)  FROM orders where shop_id = ? and state > 10 GROUP BY " +
					"extract(year_month from charge_time) ORDER BY extract(year_month from charge_time) DESC;";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, shopId);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Statistics s = new Statistics();
				s.setOrderNum(rs.getInt("count(*)"));
				s.setSumTotal(rs.getDouble("sum(total_amount)"));
				s.setYearMonth(rs.getString("extract(year_month from charge_time)"));
				list.add(s);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.queryShopStatistics");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.queryShopStatistics");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}


	@Override
	public List<OrderBusiUser> findOrderBusi(int UseId,Integer state,Integer page) throws AppException {
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		List<OrderBusiUser> orderBusiList=new ArrayList<OrderBusiUser>();
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select o.user_id,o.shop_id,o.ordernum,o.state," +
			"o.pay_time,o.total_amount,o.id,o.pay_mode,s.shopname"+
			" from orders o,shop s where o.user_id =?";
			
			if(state!=null){
				sql += " and o.state="+state;
			}
			sql += " and o.shop_id=s.id order by o.pay_time desc";
			if(page != null){
				sql += " limit "+((page-1)*10)+","+10;
			}else{
				sql += " limit 0,10";
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, UseId);
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				OrderBusiUser orderBusi = new OrderBusiUser();
				orderBusi.setUserId(rs.getInt("user_id"));
				orderBusi.setShopId(rs.getInt("shop_id"));
				orderBusi.setOrdernum(rs.getString("ordernum"));
				orderBusi.setState(rs.getInt("state"));
				orderBusi.setPaytime(rs.getString("pay_time"));
				orderBusi.setTotalAmount(rs.getFloat("total_amount"));
				orderBusi.setId(rs.getInt("id"));
				orderBusi.setPayMode(rs.getInt("pay_mode"));
				orderBusi.setShopName(rs.getString("shopname"));
				orderBusiList.add(orderBusi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderBusi");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderBusi");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return orderBusiList;
	}

	@Override
	public OrderBusiUser findOrderBusiByOrdernum(String ordernum) throws AppException{
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		OrderBusiUser orderBusiUser = new OrderBusiUser();
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select o.user_id,o.shop_id,o.ordernum,o.state,"
					+ "o.pay_time,o.total_amount,o.id,s.shopname"
					+ " from orders o,shop s where o.ordernum = ? and o.shop_id=s.id order by o.pay_time desc";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ordernum);

			rs = psmt.executeQuery();
			//标记id为-1时未赋值
			orderBusiUser.setId(-1);
			while(rs.next()){
				orderBusiUser.setUserId(rs.getInt("user_id"));
				orderBusiUser.setShopId(rs.getInt("shop_id"));
				orderBusiUser.setOrdernum(rs.getString("ordernum"));
				orderBusiUser.setState(rs.getInt("state"));
				orderBusiUser.setPaytime(rs.getString("pay_time"));
				orderBusiUser.setTotalAmount(rs.getFloat("total_amount"));
				orderBusiUser.setId(rs.getInt("id"));
				orderBusiUser.setShopName(rs.getString("shopname"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.findOrderBusiByOrdernum");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.findOrderBusiByOrdernum");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return orderBusiUser;
	}
	
	@Override
	public OrderBusiUser findOrderBusiByOrderId(int orderId) throws AppException{
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		OrderBusiUser orderBusiUser = new OrderBusiUser();
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select o.user_id,o.shop_id,o.ordernum,o.state,"
					+ "o.pay_time,o.total_amount,o.id,s.shopname"
					+ " from orders o,shop s where o.id = ? and o.shop_id=s.id order by o.pay_time desc";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, orderId);

			rs = psmt.executeQuery();
			//标记id为-1时未赋值
			orderBusiUser.setId(-1);
			while(rs.next()){
				orderBusiUser.setUserId(rs.getInt("user_id"));
				orderBusiUser.setShopId(rs.getInt("shop_id"));
				orderBusiUser.setOrdernum(rs.getString("ordernum"));
				orderBusiUser.setState(rs.getInt("state"));
				orderBusiUser.setPaytime(rs.getString("pay_time"));
				orderBusiUser.setTotalAmount(rs.getFloat("total_amount"));
				orderBusiUser.setId(rs.getInt("id"));
				orderBusiUser.setShopName(rs.getString("shopname"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.findOrderBusiByOrdernum");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.findOrderBusiByOrdernum");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return orderBusiUser;
	}
	
	
	public int findOrderId(String ordernum)throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int id = -1;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select id from orders where ordernum = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ordernum);
			rs = psmt.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.findOrderId");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.findOrderId");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return id;
	}

	
	public int getTotalOrderNum(int userId,Integer state)throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select count(*) as total from orders where user_id = ?";
			if(state != null){
				sql += " and state = "+state;
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs = psmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.getTotalOrderNum");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.getTotalOrderNum");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return count;
	}

	//用于查询站内消费
	public List<Order> findOrderByUserIdAndPayMode(int UserId, int PayMode)
			throws AppException {
		List<Order> list = new ArrayList<Order>();

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接

		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数

			String sql = "select user_id,shop_id,ordernum,state," +
					"receiver,address,tel,pay_time,recieved_time,charge_time," +
					"comment,comment_time,total_amount,pay_mode,id"+
					" from orders where user_id =? and pay_mode=? order by charge_time desc;";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, UserId);
			psmt.setInt(2, PayMode);
			rs = psmt.executeQuery();

			while(rs.next())
			{
				Order order = new Order();
				order.setUserId(rs.getInt("user_id"));
				order.setShopId(rs.getInt("shop_id"));
				order.setOrdernum(rs.getString("ordernum"));
				order.setState(rs.getInt("state"));
				order.setReceiver(rs.getString("receiver"));
				order.setAddress(rs.getString("address"));
				order.setTel(rs.getString("tel"));
				order.setPaytime(rs.getString("pay_time"));
				order.setReceivedTime(rs.getString("recieved_time"));
				order.setChargeTime(rs.getString("charge_time"));
				order.setComment(rs.getString("comment"));
				order.setCommentTime(rs.getString("comment_time"));
				order.setTotalAmount(rs.getFloat("total_amount"));
				order.setPayMode(rs.getInt("pay_mode"));
				order.setId(rs.getInt("id"));
				list.add(order);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByUserIdAndPaymode");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.findOrderByUserIdAndPaymode");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

//	查询最近订单
	@Override
	public OrderBusiUser findRecentOrderBusi(int UserId) throws AppException {
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		OrderBusiUser orderBusiUser = new OrderBusiUser();
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select o.user_id,o.shop_id,o.ordernum,o.state,"
					+ "o.pay_time,o.total_amount,o.id,s.shopname"
					+ " from orders o,shop s where o.user_id = ? and o.shop_id=s.id order by o.pay_time asc";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, UserId);

			rs = psmt.executeQuery();
			//标记id为-1时未赋值
			orderBusiUser.setId(-1);
			while(rs.next()){
				orderBusiUser.setUserId(rs.getInt("user_id"));
				orderBusiUser.setShopId(rs.getInt("shop_id"));
				orderBusiUser.setOrdernum(rs.getString("ordernum"));
				orderBusiUser.setState(rs.getInt("state"));
				orderBusiUser.setPaytime(rs.getString("pay_time"));
				orderBusiUser.setTotalAmount(rs.getFloat("total_amount"));
				orderBusiUser.setId(rs.getInt("id"));
				orderBusiUser.setShopName(rs.getString("shopname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.findRecentOrderBusi");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.findRecentOrderBusi");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return orderBusiUser;
	}

	@Override
	public int getTotalShopOrderNum(int shopId, int state)
			throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select count(*) as total from orders where shop_id = ?";
			if(state != 0){
				sql += " and state = "+state;
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, shopId);
			rs = psmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.getTotalOrderNum");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.orderDaoImpl.getTotalOrderNum");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return count;
	}

	@Override
	public ResultSet shopComment(int shopID) throws AppException {
		String sql = "select u.name, o.comment,DATE_FORMAT(comment_time,'%Y-%m-%d %h:%i %p') as comment_time " +
				"from orders o,user u where o.shop_id=? and o.user_id=u.id and o.comment<>''";
		ResultSet resultSet = null;
		Connection connection = DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, shopID);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	@Override
	public List<Statistics> queryShopStatisticsByYear(int shopId, String year)
			throws AppException {
		List<Statistics> list = new ArrayList<Statistics>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			//由于查询到的结果要用图表，所以需求改变，在这个方法上改
			String sql = "select MONTH(charge_time),sum(total_amount),count(*)  FROM orders " +
					"where shop_id = ? and state > 10 and YEAR(charge_time)=? GROUP BY " +
					"extract(year_month from charge_time) ORDER BY extract(year_month from charge_time) ASC;";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, shopId);
			psmt.setString(2,year);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Statistics s = new Statistics();
				s.setOrderNum(rs.getInt("count(*)"));
				s.setSumTotal(rs.getDouble("sum(total_amount)"));
				s.setYearMonth(rs.getString("MONTH(charge_time)"));
				list.add(s);
			}			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.queryShopStatisticsByYear");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.orderDaoImpl.queryShopStatisticsByYear");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}
}
