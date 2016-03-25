package com.ruanku.dao;

import java.sql.ResultSet;
import java.util.List;

import com.ruanku.model.Order;
import com.ruanku.model.OrderBusiUser;
import com.ruanku.model.OrderBusiShop;
import com.ruanku.model.Statistics;
import com.ruanku.util.AppException;

public interface OrderDao {
	/**
	 * 保存订单信息
	 * @param order 订单对象
	 * @return id
	 * @throws AppException
	 */
	public int save(Order order) throws AppException;
	/**
	 * 查找全部订单信息
	 * @return 包含对象为Order的List 
	 * @throws AppException
	 */
	//管理员用到
	public List<Order> findAllOrder()throws AppException;
	/**
	 * 根据shopid返回订单信息
	 * @param ShopId 店铺id
	 * @return 包含对象为Order的List 
	 * @throws AppException
	 */
	public List<Order> findOrderByShopId(int ShopId,int page,int type) throws AppException;
	/**
	 * 根据用户id 返回 全部订单信息
	 * @param UserId 用户id
	 * @return 包含对象为Order的List 
	 * @throws AppException
	 */
	public List<Order> findOrderByUserId(int UserId) throws AppException;
	//用于查询站内消费
	/**
	 * 根据 用户id 和支付方式 返回订单信息
	 * @param UserId  用户id 
	 * @param PayMode 支付方式
	 * @return 包含对象为Order的List 
	 * @throws AppException
	 */
	public List<Order> findOrderByUserIdAndPayMode(int UserId, int PayMode) throws AppException;
	//查询最近一笔订单
	/**
	 * 根据用户id 查看最近订单信息
	 * @param UserId 用户id
	 * @return 包含对象为Order的List 
	 * @throws AppException
	 */
	public OrderBusiUser findRecentOrderBusi(int UserId)throws AppException;
	/**
	 * 根据店铺id 和 和状态返回订单信息
	 * @param ShopId 店铺id
	 * @param state 
	 * @return 包含对象为Order的List 
	 * @throws AppException
	 */
	public List<Order> findOrderByShopIdAndState(int ShopId,int state)throws AppException;
	/**
	 * 根据 用户id 和状态返回订单信息
	 * @param UseId
	 * @param state
	 * @return 包含对象为Order的List 
	 * @throws AppException
	 */
	public List<Order> findOrderByUserIdAndState(int UseId,int state)throws AppException;
	/**
	 * 分页返回订单列表
	 * @param UseId 用户id
	 * @param state
	 * @param page  
	 * @return 包含对象为Order的List 
	 * @throws AppException
	 */
	public List<OrderBusiUser> findOrderBusi(int UseId,Integer state,Integer page)throws AppException;
	/**
	 * 根据订单编号返回  OrderBusiUser
	 * @param ordernum 订单编号
	 * @return OrderBusiUser 对象
	 * @throws AppException
	 */
	public OrderBusiUser findOrderBusiByOrdernum(String ordernum)throws AppException;
	/**
	 * 根据订单id 搜索 OrderBusiUser 对象
	 * @param orderId 订单id
	 * @return  OrderBusiUser 对象
	 * @throws AppException
	 */
	public OrderBusiUser findOrderBusiByOrderId(int orderId) throws AppException;
	/**
	 * 根据订单编号 返回订单id
	 * @param ordernum
	 * @return 订单id
	 * @throws AppException
	 */
	public int findOrderId(String ordernum)throws AppException;
	/**
	 * 根据orderid返回order对象
	 * @param orderId
	 * @return order 对象
	 * @throws AppException
	 */
	public Order getOrderById(int orderId) throws AppException;
	/**
	 * 根据order 编号 返回order对象
	 * @param ordernum
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public Order getOrderByOrdernum(String ordernum) throws AppException;
	/**
	 * 根据订单id 更新订单状态
	 * @param orderId
	 * @param state 订单状态
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean updateState(int orderId,int state)throws AppException;
	/**
	 * 根据订单id 更改订单评价
	 * @param orderId
	 * @param comment 评价
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	//更改评论，同时更改评论时间
	public boolean updateComment(int orderId,String comment)throws AppException;
	/**
	 * 根据订单id 修改收货时间
	 * @param orderId 订单id
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean updateReceicedTime(int orderId)throws AppException;
	/**
	 * 根据订单id 修改支付时间
	 * @param orderId
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean updateChargeTime(int orderId)throws AppException;
	/**
	 * 根据订单id 修改支付状态
	 * @param orderId
	 * @param mode
	 * @param state
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean updateOrderPaid(int orderId,int mode,int state)throws AppException;
	/**
	 * 根据订单编号 修改支付状态
	 * @param ordernum
	 * @param mode
	 * @param state
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public boolean updateOrderPaidByOrdernum(String ordernum,int mode,int state)throws AppException;
	/**
	 * 根据店铺id 返回统计信息
	 * @param shopId
	 * @return 操作成功返回true，错误返回false
	 * @throws AppException
	 */
	public List<Statistics> queryShopStatistics(int shopId)throws AppException;
	/**
	 * 根据店铺id 返回统计信息
	 * @param shopId year
	 * @return 
	 * @throws AppException
	 */
	public List<Statistics> queryShopStatisticsByYear(int shopId,String year)throws AppException;
	/**
	 * 根据用户id 返回 某一状态的所有订单数量
	 * @param userId
	 * @param state
	 * @return 订单数量
	 * @throws AppException
	 */
	
	public int getTotalOrderNum(int userId,Integer state)throws AppException;
	public int getTotalShopOrderNum(int shopId,int state)throws AppException;
	public ResultSet shopComment(int shopID)throws AppException;
	
}
