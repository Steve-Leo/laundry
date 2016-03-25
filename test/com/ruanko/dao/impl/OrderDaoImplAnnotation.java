package com.ruanko.dao.impl;

import java.util.List;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.ruanku.dao.OrderDao;
import com.ruanku.dao.impl.OrderDaoImpl;
import com.ruanku.model.Order;

public class OrderDaoImplAnnotation {

private OrderDao dao = new OrderDaoImpl();
	

	@Before
	public void before(){
		System.out.println("before");
	}

//	@Test
//	public void TestSave()
//	{
//		Order order = new Order();
//		order.setUserId(2);
//		order.setShopId(2);
//		order.setState(1);
//		
//
//		try {
//			if(dao.save(order))
//				System.out.println("sussess");
//			else System.out.println("fail");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//
//	
//	@Test
//	public void TestUpdateOrderPaid()
//	{
//		try {
//			if( dao.updateOrderPaid(2,12,33))
//				System.out.println("success");
//			else System.out.println("fail");
//				
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void TestUpdateChargeTime()
//	{
//		try {
//			if( dao.updateChargeTime(2))
//				System.out.println("success");
//			else System.out.println("fail");
//				
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void TestUpdateReceicedTime()
//	{
//		try {
//			if( dao.updateReceicedTime(2))
//				System.out.println("success");
//			else System.out.println("fail");
//				
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void TestUpdateComment()
//	{
//		try {
//			if( dao.updateComment(2,"hello world"))
//				System.out.println("success");
//			else System.out.println("fail");
//				
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	
//	@Test
//	public void TestUpdateState()
//	{
//		try {
//			if( dao.updateState(2,3))
//				System.out.println("success");
//			else System.out.println("fail");
//				
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	
//	@Test
//	public void TestGetOrderById()
//	{
//		try {
//			Order order = dao.getOrderById(2);
//			
//				System.out.println(order.getId());
//				System.out.println(order.getUserId());
//				System.out.println(order.getShopId());
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void TestFindOrderByUserIdAndState()
//	{
//		try {
//			List<Order> list = dao.findOrderByUserIdAndState(2,1);
//			for(Order order:list)		
//			{
//				System.out.println(order.getId());
//				System.out.println(order.getUserId());
//				System.out.println(order.getShopId());
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void TestFindOrderByShopIdAndState()
//	{
//		try {
//			List<Order> list = dao.findOrderByShopIdAndState(2,1);
//			for(Order order:list)		
//			{
//				System.out.println(order.getId());
//				System.out.println(order.getUserId());
//				System.out.println(order.getShopId());
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void TestFindOrderByUserId()
//	{
//		try {
//			List<Order> list = dao.findOrderByUserId(17);
//			for(Order order:list)		
//			{
//				System.out.println(order.getId());
//				System.out.println(order.getUserId());
//				System.out.println(order.getShopId());
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	
//	@Test
//	public void TestOrderByShopId()
//	{
//		try {
//			List<Order> list = dao.findOrderByShopId(2);
//			for(Order order:list)		
//			{
//				System.out.println(order.getId());
//				System.out.println(order.getUserId());
//				System.out.println(order.getShopId());
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void TestFindAllOrder()
//	{
//		try {
//			List<Order> list = dao.findAllOrder();
//			for(Order order:list)		
//			{
//				System.out.println(order.getId());
//				System.out.println(order.getUserId());
//				System.out.println(order.getShopId());
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}

	
}
