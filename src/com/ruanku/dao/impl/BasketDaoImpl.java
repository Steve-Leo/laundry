package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ruanku.dao.BasketDao;
import com.ruanku.model.Basket;
import com.ruanku.model.Clothes;
import com.ruanku.util.AppException;
import com.ruanku.util.DBUtil;

public class BasketDaoImpl implements BasketDao {

	@Override
	public boolean addClothes(Clothes clothes, int count, int userId)
			throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into basket (cloth_id, user_id, clothes_type, wash_mode, count, shop_id) values (?,?,?,?,?,?)";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, clothes.getId());
			psmt.setInt(2, userId);
			psmt.setInt(3, clothes.getType());
			psmt.setInt(4, clothes.getMode());
			psmt.setInt(5, count);
			psmt.setInt(6, clothes.getShopId());
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.addClothes");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.addClothes");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean delClothesByBasketId(int basketId) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from basket where id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, basketId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.addClothes");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException( 
					"com.ruanku.dao.impl.BasketDaoImpl.addClothes");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public List<Basket> getAllClothesByUserid(int userId) throws AppException {
		List<Basket> list = new ArrayList<Basket>();
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select b.*,s.shopname,c.price1,c.name as clothname from " +
					"(basket b left join shop s on b.shop_id = s.id) left join " +
					"clothes c on b.cloth_id = c.id where user_id = ? and c.del != 1";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Basket basket = new Basket();
				basket.setId(rs.getInt("id"));
				basket.setClothesType(rs.getInt("cloth_id"));
				basket.setClothId(rs.getInt("cloth_id"));
				basket.setCount(rs.getInt("count"));
				basket.setShopId(rs.getInt("shop_id"));
				basket.setUserId(rs.getInt("user_id"));
				basket.setWashMode(rs.getInt("wash_mode"));
				basket.setShopName(rs.getString("shopname"));
				basket.setClothName(rs.getString("clothname"));
				basket.setPrice(rs.getDouble("price1"));
				list.add(basket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.BasketDaoImpl.getAllClothesByUserid");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.BasketDaoImpl.getAllClothesByUserid");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public boolean delClothesByShopId(int shopId) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from basket where shop_id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, shopId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.delClothesByShopId");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException( 
					"com.ruanku.dao.impl.BasketDaoImpl.delClothesByShopId");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean changeBasket(Basket basket) throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "update basket set count = ? where id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, basket.getCount());
			psmt.setInt(2, basket.getId());
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.changeBasket");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.changeBasket");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean delAllClothes(int userId) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from basket where user_id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, userId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.delAllClothes");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException( 
					"com.ruanku.dao.impl.BasketDaoImpl.delAllClothes");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean addBasket(Basket basket) throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into basket (cloth_id, user_id, clothes_type, wash_mode, count, shop_id) values (?,?,?,?,?,?)";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, basket.getClothId());
			psmt.setInt(2, basket.getUserId());
			psmt.setInt(3, basket.getClothesType());
			psmt.setInt(4, basket.getWashMode());
			psmt.setInt(5, basket.getCount());
			psmt.setInt(6, basket.getShopId());
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.addBasket");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.addBasket");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean updateBasket(Map<Integer, Integer> values)
			throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into basket (id, count) values ";
		String value = "";
		String key = "";
		Iterator it=values.keySet().iterator();
		while(it.hasNext()){
			key = it.next().toString();
		    value += "(" + key + "," + values.get(Integer.valueOf(key)) + "),"; 
		}
		value = value.substring(0,value.length()-1);
		sql += value + "on duplicate key update count = values(count)";

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
					"com.ruanku.dao.impl.BasketDaoImpl.updateBasket");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.updateBasket");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	@Override
	public boolean deleteBasket(String ids) throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from basket where id in (" + ids + ")";
		
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
					"com.ruanku.dao.impl.BasketDaoImpl.deleteBasket");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.deleteBasket");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	@Override
	public boolean deleteBasketByShopId(int userId,int shopId)throws AppException{
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from basket where user_id = ? and shop_id = ?";
		
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);
			psmt.setInt(2, shopId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.deleteBasketByShopId");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.BasketDaoImpl.deleteBasketByShopId");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public List<Basket> getClothesByShop(int userId, String shopId)
			throws AppException {
		List<Basket> list = new ArrayList<Basket>();
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
			String sql = "select b.*,s.shopname,c.price1,c.name as clothname from " +
					"(basket b left join shop s on b.shop_id = s.id) left join " +
					"clothes c on b.cloth_id = c.id where user_id = ? and b.shop_id in (";
			sql += shopId + ")and c.del != 1";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				Basket basket = new Basket();
				basket.setId(rs.getInt("id"));
				basket.setClothesType(rs.getInt("cloth_id"));
				basket.setClothId(rs.getInt("cloth_id"));
				basket.setCount(rs.getInt("count"));
				basket.setShopId(rs.getInt("shop_id"));
				basket.setUserId(rs.getInt("user_id"));
				basket.setWashMode(rs.getInt("wash_mode"));
				basket.setShopName(rs.getString("shopname"));
				basket.setClothName(rs.getString("clothname"));
				basket.setPrice(rs.getDouble("price1"));
				list.add(basket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.BasketDaoImpl.getClothesByShop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.BasketDaoImpl.getClothesByShop");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public boolean addClothes(int id, int count, int userId, int shopId)
			throws AppException {
		String sql = "select * from basket where cloth_id=? and shop_id=? and user_id=?";
		Connection connection = DBUtil.getConnection();
		int result = 0;
		try {
			PreparedStatement prePreparedStatement=connection.prepareStatement(sql);
			prePreparedStatement.setInt(1, id);
			prePreparedStatement.setInt(2, shopId);
			prePreparedStatement.setInt(3, userId);
			ResultSet resultSet = prePreparedStatement.executeQuery();
			String basketSql=null;
			if (resultSet.next()) {
				basketSql = "update basket set count=count+? where cloth_id=? and shop_id=? and user_id=?";
				PreparedStatement basket = connection.prepareStatement(basketSql);
				basket.setInt(1, count);
				basket.setInt(2, id); 
				basket.setInt(3, shopId); 
				basket.setInt(4, userId); 
				result = basket.executeUpdate();
			}else {
				basketSql = "insert into basket(cloth_id,user_id,shop_id,clothes_type,wash_mode,count)" +
						" select ?,?,shop_id,type,wash_mode,? from clothes where id=?";
				PreparedStatement basket = connection.prepareStatement(basketSql);
				basket.setInt(1, id);
				basket.setInt(2, userId);
				basket.setInt(3, count);
				basket.setInt(4, id);
				result = basket.executeUpdate();
			}
			if (result == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean checkUpdateLegal(int userId, String ids){
		String sql = "select count(*) as count from basket where user_id = ? and id in ("+ids+")";
		Connection connection = DBUtil.getConnection();
		int result = 0;
		try {
			PreparedStatement prePreparedStatement=connection.prepareStatement(sql);
			prePreparedStatement.setInt(1, userId);
			ResultSet resultSet = prePreparedStatement.executeQuery();
			
			if (resultSet.next()) {
				result = resultSet.getInt("count");
			}
			String args[] = ids.split(",");
			if (result < args.length) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
