package com.ruanku.dao;

import java.sql.ResultSet;
import java.util.List;

import net.sf.json.JSONArray;

import com.ruanku.model.Shop;
import com.ruanku.util.AppException;
public interface ShopDao {
	/**
	 * 编写人：曾盼
	 * 作用：保存一个店铺用户的信息
	 * @param shop对象
	 * @return 插入数据是否成功
	 * @throws AppException
	 */
public boolean registerShop(Shop shop) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：更新一个店铺用户信息
	 * @param shop
	 * @return 更新是否成功
	 * @throws AppException
	 */
	public boolean updateShop(Shop shop) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：根据用户账号，查询是否存在这个用户
	 * @param username 店铺用户账号
	 * @return 根据用户账号，查询是否存在这个用户
	 * @throws AppException
	 */
	public boolean isUsernameExist(String username) throws AppException;
	
	/**
	 * 编写人：删除
	 * 判断商户是否删除
	 * @param id 商铺id
	 * @return 是否被删除
	 * @throws AppException
	 */
	public boolean isShopDel(int id) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：根据店铺id删除店铺记录
	 * @param id 店铺id
	 * @param isDel
	 * @return 删除店铺是否成功
	 * @throws AppException
	 */
	public boolean setShopDel(int id,int isDel) throws AppException;
	

	//public boolean delShop(int id) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：根据id，返回一个店铺对象
	 * @param id
	 * @return 根据店铺id，返回该店铺对象
	 * @throws AppException
	 */
	public Shop getShop(int id) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 返回所有店铺信息列表
	 * @return 所有店铺信息
	 * @throws AppException
	 */
	public List<Shop> getAllShops() throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 根据店铺状态返回所有店铺的信息
	 * @param state
	 * @return 根据店铺状态返回所有店铺的信息
	 * @throws AppException
	 */
	public List<Shop> getShopsByState(int state) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * @param name 店铺账号
	 * @param password 店铺密码
	 * @return int 查询到的id
	 * @throws AppException
	 */
	public int login(String name,String password) throws AppException;
	
	/**
	 * 
	 * @param province
	 * @param city
	 * @return 
	 * @throws AppException
	 */
	public JSONArray getShopsByLocation(String province, String city) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * @param id 
	 * @return
	 * @throws AppException
	 */
	public Shop getShopsById(String id) throws AppException;
	
	/**
	 * 
	 * @param searchText
	 * @return
	 * @throws AppException
	 */
	public List<Shop> getShopsBySearch(String searchText) throws AppException;
}
