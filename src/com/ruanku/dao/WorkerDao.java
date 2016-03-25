package com.ruanku.dao;

import java.util.List;

import com.ruanku.model.Worker;

import com.ruanku.util.AppException;

public interface WorkerDao {
	/**
	 * 编写人：曾盼
	 * 作用：保存员工信息
	 * @param woker
	 * @return
	 * @throws AppException
	 */
	public boolean save(Worker woker) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：根据商铺id查询所有员工
	 * @param shopId
	 * @return
	 * @throws AppException
	 */
	public List<Worker> findByShopId(int shopId) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：更新某一员工信息
	 * @param woker
	 * @return 是否成功
	 * @throws AppException
	 */
	public boolean updateWorker(Worker woker) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：
	 * @param name
	 * @return
	 * @throws AppException
	 */
	public List<Worker> findByName(String name) throws AppException;
	
	/**
	 * 编写人：曾盼
	 * 作用：删除某一员工信息
	 * @param id
	 * @return 删除是否成功
	 * @throws AppException
	 */
	public boolean delWorker(int id)throws AppException;
}
