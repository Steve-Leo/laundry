package com.ruanku.dao;

import java.util.List;

import com.ruanku.model.COOBusi;
import com.ruanku.model.ClothesOfOrder;
import com.ruanku.util.AppException;

public interface ClothesOfOrderDao {
	/**
	 * 根据订单id 查找订单信息
	 * @param orderId 订单id
	 * @return 包含对象为COOB 的List
	 * @throws AppException
	 */
	public List<COOBusi> findCOOBusi(int orderId)throws AppException;
	/**
	 * 保存衣服订单
	 * @param items
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean save(ClothesOfOrder items) throws AppException;
	/**
	 * 保存多个订单
	 * @param items 包含对象为COOB 的List
	 * @return 
	 * @throws AppException
	 */
	public boolean multiSave(List<ClothesOfOrder> items) throws AppException;
	/**
	 * 根据订单id 查找订单
	 * @param orderId 
	 * @return 包含对象为COOB 的List
	 * @throws AppException
	 */
	public List<ClothesOfOrder> findClothesOfOrder(int orderId)throws AppException;
}