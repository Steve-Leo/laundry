package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;

import com.ruanku.dao.ShopDao;
import com.ruanku.model.Clothes;
import com.ruanku.model.Shop;

import com.ruanku.util.AppException;
import com.ruanku.util.DBUtil;
import com.ruanku.util.JsonUtil;

public class ShopDaoImpl implements ShopDao {
	@Override
	public Shop getShop(int id) throws AppException {
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		Shop shop =null;
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数		
			String sql = "select * from shop where id =?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				
					shop = new Shop();
					shop.setId(rs.getInt("id"));
					shop.setShopname(rs.getString("shopname"));
					shop.setAddress(rs.getString("address"));
					shop.setTel(rs.getString("tel"));
					shop.setComment(rs.getString("comment"));
					shop.setUsername(rs.getString("username"));
					shop.setPassword(rs.getString("password"));
					shop.setGrade(rs.getInt("grade"));
					shop.setPicture(rs.getString("picture"));
					shop.setDel(rs.getInt("del"));
					shop.setState(rs.getInt("state"));
					shop.setProvince(rs.getString("province"));
					shop.setCity(rs.getString("city"));
					shop.setLongitude(rs.getDouble("longitude"));
					shop.setLatitude(rs.getDouble("latitude"));
					shop.setMoney(rs.getFloat("money"));
					
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.getShop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.getShop");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return shop;
	}
	
	@Override
	public boolean registerShop(Shop shop) throws AppException {
		if (isUsernameExist(shop.getUsername())) {
			return false;
		}
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into shop (shopname,address,tel,comment,username,password,grade,picture,del,state,province,city,longitude,latitude,money) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, shop.getShopname());
			psmt.setString(2, shop.getAddress());
			psmt.setString(3, shop.getTel());
			psmt.setString(4, shop.getComment());
			psmt.setString(5, shop.getUsername());
			psmt.setString(6, shop.getPassword());
			psmt.setInt(7, shop.getGrade());
			psmt.setString(8, shop.getPicture());
			psmt.setInt(9, shop.getDel());
			psmt.setInt(10, shop.getState());
			psmt.setString(11, shop.getProvince());
			psmt.setString(12, shop.getCity());
			psmt.setDouble(13, shop.getLongitude());
			psmt.setDouble(14, shop.getLatitude());
			psmt.setFloat(15, shop.getMoney());

			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.registerShop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.registerShop");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean updateShop(Shop shop) throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "update shop set shopname = ?, address = ?, " +
				"tel = ?, password = ?, grade = ?,picture = ?,del = ?," +
				"state = ?,province = ?,city = ?,longitude = ?,latitude = ? ,comment=? where id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, shop.getShopname());
			psmt.setString(2, shop.getAddress());
			psmt.setString(3, shop.getTel());
			psmt.setString(4, shop.getPassword());
			psmt.setInt(5, shop.getGrade());
			psmt.setString(6, shop.getPicture());
			psmt.setInt(7, shop.getDel());
			psmt.setInt(8, shop.getState());
			psmt.setString(9, shop.getProvince());
			psmt.setString(10, shop.getCity());
			psmt.setDouble(11, shop.getLongitude());
			psmt.setDouble(12, shop.getLatitude());
			psmt.setString(13, shop.getComment());
			psmt.setInt(14, shop.getId());
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.updateStore");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.updateStore");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean isUsernameExist(String username) throws AppException {
		
		boolean flag = false;
		// 声明数据库连接对象，预编译对象和结果对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句：根据用户名查询
			String sql = "select id from shop where username = ?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				flag = true;
				//System.out.println("此用户名已存在");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.isUsernameExist");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.isUsernameExist");
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
	public boolean setShopDel(int id, int isDel) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "update shop set del = ? where id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, isDel);
			psmt.setInt(2, id);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.setStoreDel");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.setStoreDel");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean isShopDel(int id) throws AppException {
		boolean flag = false;
		// 声明数据库连接对象，预编译对象和结果对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 声明操作语句：根据用户名查询
			String sql = "select del from shop where id = ?";
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			// 执行查询操作
			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				int del = rs.getInt("del");
				if (del == 1) {
					flag = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.isStoreDel");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.isStoreDel");
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
	public List<Shop> getAllShops() throws AppException {
		List<Shop> list = new ArrayList<Shop>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数		
			String sql = "select * from shop";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				if (!(rs.getInt("del") == 1)) {
					Shop shop = new Shop();
					shop.setId(rs.getInt("id"));
					shop.setShopname(rs.getString("shopname"));
					shop.setAddress(rs.getString("address"));
					shop.setTel(rs.getString("tel"));
					shop.setComment(rs.getString("comment"));
					shop.setUsername(rs.getString("username"));
					shop.setPassword(rs.getString("password"));
					shop.setGrade(rs.getInt("grade"));
					shop.setPicture(rs.getString("picture"));
					shop.setDel(rs.getInt("del"));
					shop.setState(rs.getInt("state"));
					shop.setProvince(rs.getString("province"));
					shop.setCity(rs.getString("city"));
					shop.setLongitude(rs.getDouble("longitude"));
					shop.setLatitude(rs.getDouble("latitude"));
					shop.setMoney(rs.getFloat("money"));
					list.add(shop);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.getAllShops");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.getAllShops");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<Shop> getShopsByState(int state) throws AppException {
		List<Shop> list = new ArrayList<Shop>();
		
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		
		try {
			//创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			
//			String sql = "select id, shopname, address, tel, comment, username, password, grade, picture, del, state, province, city, longitude, latitude, money from shop";
			String sql = "select * from shop where state = ? ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, state);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				if (!(rs.getInt("del") == 1)) {
					Shop shop = new Shop();
					shop.setId(rs.getInt("id"));
					shop.setShopname(rs.getString("shopname"));
					shop.setAddress(rs.getString("address"));
					shop.setTel(rs.getString("tel"));
					shop.setComment(rs.getString("comment"));
					shop.setUsername(rs.getString("username"));
					shop.setPassword(rs.getString("password"));
					shop.setGrade(rs.getInt("grade"));
					shop.setPicture(rs.getString("picture"));
					shop.setDel(rs.getInt("del"));
					shop.setState(rs.getInt("state"));
					shop.setProvince(rs.getString("province"));
					shop.setCity(rs.getString("city"));
					shop.setLongitude(rs.getDouble("longitude"));
					shop.setLatitude(rs.getDouble("latitude"));
					shop.setMoney(rs.getFloat("money"));
					list.add(shop);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.getAllShops");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.getAllShops");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

/*	@Override
	public boolean delShop(int id) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "delete from shop where id = ?";
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
					"com.ruanku.dao.impl.ShopDaoImpl.delShop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ShopDaoImpl.delShop");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}*/
	
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
			String sql = "select id from shop where del = 0 and username = ? and password = ?";
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
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.login");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.ShopDaoImpl.login");
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
	public JSONArray getShopsByLocation(String province, String city)
			throws AppException {
		String sql = "select * from shop where province=? and city=? and state=2";
		Connection connection = DBUtil.getConnection();
		ResultSet resultSet = null;
		JSONArray jsonArray = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, province);
			preparedStatement.setString(2, city);
			resultSet = preparedStatement.executeQuery();
			jsonArray = JsonUtil.formatRsToJsonArray(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(connection);
		}
		return jsonArray;
	}

	@Override
	public Shop getShopsById(String id) throws AppException {
		if (id == null) {
			return null;
		}
		String sql = "select id,shopname,address,tel,comment,grade,picture from" +
				" shop where id=?";
		String clothesSql = "select * from clothes where del=0 and shop_id=?";
		Connection connection = DBUtil.getConnection();
		Shop shop = null;
		try {
			PreparedStatement clothesStatement = connection.prepareStatement(clothesSql);
			clothesStatement.setInt(1, Integer.parseInt(id));
			ResultSet resultSet = clothesStatement.executeQuery();
			List<Clothes> cloList = new LinkedList<Clothes>();
			while (resultSet.next()) {
				Clothes clothes = new Clothes();
				clothes.setId(resultSet.getInt("id"));
				clothes.setShopId(resultSet.getInt("shop_id"));
				clothes.setName(resultSet.getString("name"));
				clothes.setType(resultSet.getInt("type"));
				clothes.setMode(resultSet.getInt("wash_mode"));
				clothes.setPicture(resultSet.getString("picture"));
				clothes.setPrice1(resultSet.getFloat("price1"));
				clothes.setComment(resultSet.getString("comment"));
				clothes.setPrice2(resultSet.getFloat("price2"));
				clothes.setDel(resultSet.getInt("del"));
				cloList.add(clothes);
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				shop = new Shop();
				shop.setId(rs.getInt("id"));
				shop.setShopname(rs.getString("shopname"));
				shop.setAddress(rs.getString("address"));
				shop.setTel(rs.getString("tel"));
				shop.setComment(rs.getString("comment"));
				shop.setGrade(rs.getInt("grade"));
				shop.setPicture(rs.getString("picture"));
				shop.setClothes(cloList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(connection);
		}
		return shop;
	}
	

	@Override
	public List<Shop> getShopsBySearch(String searchText) throws AppException {
		String sql = "select * from shop where state=2 and (shopname like '%"+searchText+"%' or " +
				"address like '%"+searchText+"%' or comment like '%"+searchText+"%')";
		ResultSet resultSet = null;
		Connection connection = DBUtil.getConnection();
		List<Shop> shops = null;
		try {
			shops = new LinkedList<Shop>();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Shop shop = new Shop();
				shop.setId(resultSet.getInt("id"));
				shop.setShopname(resultSet.getString("shopname"));
				shop.setAddress(resultSet.getString("address"));
				shop.setTel(resultSet.getString("tel"));
				shop.setComment(resultSet.getString("comment"));
				shop.setGrade(resultSet.getInt("grade"));
				shop.setPicture(resultSet.getString("picture"));
				shops.add(shop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(connection);
		}
		return shops;
	}

}
