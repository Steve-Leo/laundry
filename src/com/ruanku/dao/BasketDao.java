package com.ruanku.dao;

import java.util.List;
import java.util.Map;

import com.ruanku.model.Basket;
import com.ruanku.model.Clothes;
import com.ruanku.util.AppException;

public interface BasketDao {
	/**
	 * 编写人 刘佳林
	 * 作用  添加单件衣服到洗衣篮
	 * @param clothes 单件衣服
	 * @param count 数量
	 * @param userId 用户
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean addClothes(Clothes clothes, int count, int userId)throws AppException;
	/**
	 * 作用 将basket 项添加到数据库
	 * @param id 洗衣篮id
	 * @param count 衣服的数量
	 * @param userId 用户 
	 * @param shopId 店铺 id
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean addClothes(int id, int count, int userId, int shopId)throws AppException;
	/**
	 * 将洗衣篮 对象添加到数据库
	 * @param basket 洗衣篮对象
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean addBasket(Basket basket)throws AppException;
	/**
	 * 根据洗衣篮id 删除洗衣篮
	 * @param basketId 洗衣篮id
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean delClothesByBasketId(int basketId)throws AppException;
	/**
	 * 根据用户id 返回所有的洗衣篮对象
	 * @param userId 用户id
	 * @return 包含洗衣篮对象的 List
	 * @throws AppException
	 */
	public List<Basket> getAllClothesByUserid(int userId)throws AppException;
	/**
	 * 根据店铺id和用户id返回所有洗衣篮对象
	 * @param userId 用户id
	 * @param shopId 店铺id
	 * @return 包含为洗衣篮对象的List
	 * @throws AppException
	 */
	public List<Basket> getClothesByShop(int userId,String shopId)throws AppException;
	/**
	 * 更新洗衣篮
	 * @param values
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean updateBasket(Map<Integer,Integer> values)throws AppException;
	/**
	 * 根据店铺id删除洗衣篮
	 * @param shopId 店铺id
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean delClothesByShopId(int shopId)throws AppException;
	/**
	 * 修改basket
	 * @param basket 洗衣篮对象
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean changeBasket(Basket basket)throws AppException;
	/**
	 * 根据用户id 删除全部洗衣篮
	 * @param userId
	 * @return操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean delAllClothes(int userId)throws AppException;
	/**
	 * 根据洗衣篮id 删除洗衣篮
	 * @param ids 洗衣篮id
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean deleteBasket(String ids)throws AppException;
	/**
	 * 根据店铺id，用户id 删除洗衣篮
	 * @param userId 用户id 
	 * @param shopId 店铺id
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean deleteBasketByShopId(int userId,int shopId)throws AppException;
	/**
	 * 根据店铺id，用户id 删除洗衣篮
	 * @param userId 用户id 
	 * @param shopId 店铺id
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean checkUpdateLegal(int userId,String ids)throws AppException;
}
