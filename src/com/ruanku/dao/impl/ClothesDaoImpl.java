package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruanku.dao.ClothesDao;
import com.ruanku.model.Clothes;
import com.ruanku.util.AppException;
import com.ruanku.util.DBUtil;

public class ClothesDaoImpl implements ClothesDao {

	@Override
	public boolean saveClothes(Clothes clothes) throws AppException {
		// 操作标记
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "insert into clothes (shop_id, name, type, wash_mode, picture, price1, comment, price2, del) values (?,?,?,?,?,?,?,?,?)";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, clothes.getShopId());
			psmt.setString(2, clothes.getName());
			psmt.setInt(3, clothes.getType());
			psmt.setInt(4, clothes.getMode());
			psmt.setString(5, clothes.getPicture());
			psmt.setDouble(6, clothes.getPrice1());
			psmt.setString(7, clothes.getComment());
			psmt.setDouble(8, clothes.getPrice2());
			psmt.setInt(9, clothes.getDel());
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.saveClothes");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.saveClothes");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean updateClothes(Clothes clothes) throws AppException {
//		if (isClothseDel(clothes.getId())) {
//			return false;
//		}

		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "";
		if(clothes.getPicture().equals("")){
			sql = "update clothes set shop_id = ?, name = ?, type = ?, wash_mode = ?, price1 = ?, comment = ? ,del = ?  where id = ? and del=0";
		}else{
			sql = "update clothes set shop_id = ?, name = ?, type = ?, wash_mode = ?, picture = ?, price1 = ?, comment = ? ,del = ?  where id = ? and del=0";
		}

		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, clothes.getShopId());
			psmt.setString(2, clothes.getName());
			psmt.setInt(3, clothes.getType());
			psmt.setInt(4, clothes.getMode());
			if(clothes.getPicture().equals("")){
				psmt.setDouble(5, clothes.getPrice1());
				psmt.setString(6, clothes.getComment());
				psmt.setInt(7, clothes.getDel());
				psmt.setInt(8, clothes.getId());
			}else{
				psmt.setString(5, clothes.getPicture());
				psmt.setDouble(6, clothes.getPrice1());
				psmt.setString(7, clothes.getComment());
				psmt.setInt(8, clothes.getDel());
				psmt.setInt(9, clothes.getId());
			}
			
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.updateClothes");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.updateClothes");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean deleteClothes(int clothesId) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "update clothes set del = 1 where id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, clothesId);
			// 执行更新操作
			int result = psmt.executeUpdate();
			// 处理执行结果
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.deleteClothes");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.deleteClothes");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public boolean isClothesDel(int clothesId) throws AppException {
		boolean flag = false;
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "select del from clothes where id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, clothesId);
			// 执行更新操作
			rs = psmt.executeQuery();
			// 处理执行结果
			if (rs.next()) {
				if (rs.getInt("del") == 1) {
					flag = true;
					System.out.println("clothes deleted");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.isClothseDel");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.isClothseDel");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	@Override
	public List<Clothes> findAllClothes() throws AppException {
		List<Clothes> list = new ArrayList<Clothes>();

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select * from clothes where  del=0";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Clothes clothes = new Clothes();
				clothes.setId(rs.getInt("id"));
				clothes.setShopId(rs.getInt("shop_id"));
				clothes.setName(rs.getString("name"));
				clothes.setType(rs.getInt("type"));
				clothes.setMode(rs.getInt("wash_mode"));
				clothes.setPicture(rs.getString("picture"));
				clothes.setPrice1(rs.getDouble("price1"));
				clothes.setComment(rs.getString("comment"));
				clothes.setPrice2(rs.getDouble("price2"));
				clothes.setDel(rs.getInt("del"));
				list.add(clothes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findAllClothes");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findAllClothes");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<Clothes> findClothesbyShopId(int shopId) throws AppException {
		List<Clothes> list = new ArrayList<Clothes>();

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select * from clothes where del=0 and shop_id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, shopId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Clothes clothes = new Clothes();
				clothes.setDel(rs.getInt("del"));
				if (!(clothes.getDel() == 1)) {
					clothes.setId(rs.getInt("id"));
					clothes.setShopId(rs.getInt("shop_id"));
					clothes.setName(rs.getString("name"));
					clothes.setType(rs.getInt("type"));
					clothes.setMode(rs.getInt("wash_mode"));
					clothes.setPicture(rs.getString("picture"));
					clothes.setPrice1(rs.getDouble("price1"));
					clothes.setComment(rs.getString("comment"));
					clothes.setPrice2(rs.getDouble("price2"));
					list.add(clothes);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesbyShopId");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesbyShopId");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public Clothes findClothesById(int id) throws AppException {
		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Clothes clothes = null;
		// 获取数据库连接
		conn = DBUtil.getConnection();
		// 提供SQL语句
		String sql = "select * from clothes where del=0 and id = ?";
		try {
			// 预处理，设置参数
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, id);

			rs = psmt.executeQuery();
			// 查到用户存在则flag为true
			if (rs.next()) {
				if (!(rs.getInt("del") == 1)) {
					clothes = new Clothes();
					clothes.setId(rs.getInt("id"));
					clothes.setComment(rs.getString("comment"));
					clothes.setDel(rs.getInt("del"));
					clothes.setMode(rs.getInt("wash_mode"));
					clothes.setName(rs.getString("name"));
					clothes.setPicture(rs.getString("picture"));
					clothes.setPrice1(rs.getDouble("price1"));
					clothes.setPrice2(rs.getDouble("price2"));
					clothes.setShopId(rs.getInt("shop_id"));
					clothes.setType(rs.getInt("type"));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesById");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesById");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return clothes;
	}

	@Override
	public List<Clothes> findClothesbySearch(String queryStr)throws AppException {
		List<Clothes> list = new ArrayList<Clothes>();

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select * from clothes where  del=0 and name like ? or comment like ? ";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%"+queryStr+"%");
			psmt.setString(2, "%"+queryStr+"%");
			rs = psmt.executeQuery();

			while (rs.next()) {
				if (!(rs.getInt("del") == 1)) {
					Clothes clothes = new Clothes();
					clothes.setId(rs.getInt("id"));
					clothes.setShopId(rs.getInt("shop_id"));
					clothes.setName(rs.getString("name"));
					clothes.setType(rs.getInt("type"));
					clothes.setMode(rs.getInt("wash_mode"));
					clothes.setPicture(rs.getString("picture"));
					clothes.setPrice1(rs.getDouble("price1"));
					clothes.setComment(rs.getString("comment"));
					clothes.setPrice2(rs.getDouble("price2"));
					clothes.setDel(rs.getInt("del"));
					list.add(clothes);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesbySearch");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesbySearch");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<Clothes> findClothesbySearchAndShopId(int shopId,
			String queryStr) throws AppException {
		List<Clothes> list = new ArrayList<Clothes>();

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select * from (select * from clothes where  del=0 and shop_id = ?) as total where name like ? or comment like ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, shopId);
			psmt.setString(2, "%"+queryStr+"%");
			psmt.setString(3, "%"+queryStr+"%");
			rs = psmt.executeQuery();

			while (rs.next()) {
				if (!(rs.getInt("del") == 1)) {
					Clothes clothes = new Clothes();
					clothes.setId(rs.getInt("id"));
					clothes.setShopId(rs.getInt("shop_id"));
					clothes.setName(rs.getString("name"));
					clothes.setType(rs.getInt("type"));
					clothes.setMode(rs.getInt("wash_mode"));
					clothes.setPicture(rs.getString("picture"));
					clothes.setPrice1(rs.getDouble("price1"));
					clothes.setComment(rs.getString("comment"));
					clothes.setPrice2(rs.getDouble("price2"));
					clothes.setDel(rs.getInt("del"));
					list.add(clothes);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesbySearchAndShopId");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesbySearchAndShopId");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	public Map<Integer,Clothes> findClothesByIds(String ids)throws AppException{
		Map<Integer,Clothes> clothList = new HashMap<Integer,Clothes>();

		// 数据库操作对象
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// 获取数据库连接
		try {
			// 创建数据库连接
			conn = DBUtil.getConnection();
			// 预处理，设置参数
			String sql = "select * from clothes where del=0 and id in (" + ids + ")";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Clothes clothes = new Clothes();
				clothes.setId(rs.getInt("id"));
				clothes.setShopId(rs.getInt("shop_id"));
				clothes.setName(rs.getString("name"));
				clothes.setType(rs.getInt("type"));
				clothes.setMode(rs.getInt("wash_mode"));
				clothes.setPicture(rs.getString("picture"));
				clothes.setPrice1(rs.getDouble("price1"));
				clothes.setComment(rs.getString("comment"));
				clothes.setPrice2(rs.getDouble("price2"));
				clothes.setDel(rs.getInt("del"));
				clothList.put(clothes.getId(), clothes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesByIds");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.ClothesDaoImpl.findClothesByIds");
		} finally {
			// 关闭数据库连接，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return clothList;
	}
}
