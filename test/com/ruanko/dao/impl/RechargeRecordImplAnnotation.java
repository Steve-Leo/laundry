package com.ruanko.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.dao.RechargeRecordDao;
import com.ruanku.dao.impl.RechargeRecordDaoImpl;
import com.ruanku.model.RechargeRecord;

public class RechargeRecordImplAnnotation {
	private RechargeRecordDao recordDao = new RechargeRecordDaoImpl();
	@Before
	public void before(){
		System.out.println("before");
	}
	
//	@Test
//	public void TestRegisterShop(){
//		RechargeRecord record = new RechargeRecord();
//		record.setMoney(1000);
//		record.setSerialNum("111sss");
//		record.setStatus(4);
//		record.setTime("2016-3-1");
//		record.setUserId("ss");
//		try {
//			if(recordDao.reCharge(record))
//				System.out.println("sussess");
//			else System.out.println("fail");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	} 
	
//	@Test
//	public void TestgetAllRechargeRecords(){
//		List<RechargeRecord> list = new ArrayList<RechargeRecord>();
//		try {
//			list = recordDao.getAllRechargeRecords();
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (RechargeRecord record:list) {
//					System.out.println(record.getId() +" "+ record.getStatus());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void TestDelRechargeRecord(){
//		try {
//			if (recordDao.deleteRechargeRecord(1)) {
//				System.out.println("sucess");
//			}else {
//				System.out.println("failed");
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void TestgetRechargeRecordsByUserid(){
//		List<RechargeRecord> list = new ArrayList<RechargeRecord>();
//		try {
//			list = recordDao.getRechargeRecordsByUserid(0);
//			if (list.isEmpty()) {
//				System.out.println("failed");
//			}
//			else {
//				for (RechargeRecord record:list) {
//					System.out.println(record.getId() +" "+ record.getStatus()+ " " + record.getUserId());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	
	@Test
	public void TestgetRechargeRecordsByUseridAndStatus(){
		List<RechargeRecord> list = new ArrayList<RechargeRecord>();
		try {
			list = recordDao.getRechargeRecordsByUseridAndStatus(0, 4);
			if (list.isEmpty()) {
				System.out.println("failed");
			}
			else {
				for (RechargeRecord record:list) {
					System.out.println(record.getId() +" "+ record.getStatus()+ " " + record.getUserId());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
