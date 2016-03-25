package com.ruanku.dao;

import java.util.List;

import com.ruanku.model.RechargeRecord;
import com.ruanku.util.AppException;

public interface RechargeRecordDao {
	/**
	 * 编写人：曾盼
	 * 作用：保存一条充值记录
	 * @param rechargeRecord 
	 * @return 保存是否成功
	 * @throws AppException
	 */
	public boolean reCharge(RechargeRecord rechargeRecord) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：删除一条充值记录
	 * @param id
	 * @return 删除是否成功
	 * @throws AppException
	 */
	public boolean deleteRechargeRecord(int id)throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：返回所有的充值记录
	 * @return 返回所有的充值记录
	 * @throws AppException
	 */
	public List<RechargeRecord> getAllRechargeRecords() throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：返回某个用户的所有充值记录
	 * @param user_id
	 * @return 返回某个用户的所有充值记录
	 * @throws AppException
	 */
	public List<RechargeRecord> getRechargeRecordsByUserid(int user_id) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：根据用户id和充值状态，返回用户的所有充值记录
	 * @param user_id 用户id
	 * @param status 充值状态
	 * @return 根据用户id和充值状态，返回用户的所有充值记录
	 * @throws AppException
	 */
	public List<RechargeRecord> getRechargeRecordsByUseridAndStatus(int user_id, int status) throws AppException;
}
