package com.ruanku.dao;

import java.util.List;
import java.util.Map;

import com.ruanku.model.Clothes;
import com.ruanku.util.AppException;

public interface ClothesDao {
	/**
	 * 保存衣服
	 * @param clothes 衣服对象
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean saveClothes(Clothes clothes) throws AppException;
	
	//del = 0 And clothes.getId来跟新
	/**
	 * 更新衣服信息
	 * @param clothes 衣服对象
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean updateClothes(Clothes clothes)throws AppException;
	
	//set del=1
	/**
	 * 根据衣服id删除衣服
	 * @param clothesId 衣服id
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean deleteClothes(int clothesId)throws AppException;
	/**
	 * 判断衣服是否删除
	 * @param clothesId 衣服id
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean isClothesDel(int clothesId)throws AppException;
	
	//这个不用管del字段
	/**
	 * 
	 * @return 返回全部衣服信息,包括删除了的
	 * @throws AppException
	 */
	public List<Clothes> findAllClothes()throws AppException;
	/**
	 * 根据店铺返回全部衣服
	 * @param shopId 店铺id
	 * @return 返回包含衣服对象的List（未删除的）
	 * @throws AppException
	 */
	//del = 0
	public List<Clothes> findClothesbyShopId(int shopId)throws AppException;
	/**
	 * 根据衣服id查找未删除的衣服信息
	 * @param id 衣服id
	 * @return 衣服对象
	 * @throws AppException
	 */
	//del = 0
	public Clothes findClothesById(int id)throws AppException;
	/**
	 *  根据查找条件返回相应的衣服信息
	 * @param queryStr 查找条件
	 * @return 返回 衣服对象的List
	 * @throws AppException
	 */
	//del = 0
	public List<Clothes> findClothesbySearch(String queryStr)throws AppException;
	/**
	 * 在当前店铺中查找衣服
	 * @param shopId 店铺id
	 * @param queryStr 寻找条件，
	 * @return返回 衣服对象的List
	 * @throws AppException
	 */
	//del = 0
	public List<Clothes> findClothesbySearchAndShopId(int shopId,String queryStr)throws AppException;
	/**
	 * 根据衣服id 返回衣服 
	 * @param ids 衣服id
	 * @return
	 * @throws AppException
	 */
	public Map<Integer,Clothes> findClothesByIds(String ids)throws AppException;
}
