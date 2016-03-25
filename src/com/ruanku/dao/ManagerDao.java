package com.ruanku.dao;

import com.ruanku.model.Manager;
import com.ruanku.util.AppException;

public interface ManagerDao {
	
	public int login(String name,String password) throws AppException ;
	
	public Manager getManager(int id) throws AppException;
	
	public boolean managerExpend(int id,float expend)throws AppException;
	
	public boolean managerIncome(int id,float income)throws AppException;
}
