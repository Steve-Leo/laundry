package com.ruanku.web;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.dao.ClothesOfOrderDao;
import com.ruanku.dao.OrderDao;
import com.ruanku.dao.impl.ClothesOfOrderDaoImpl;
import com.ruanku.dao.impl.OrderDaoImpl;
import com.ruanku.model.Order;
import com.ruanku.model.OrderBusiUser;
import com.ruanku.model.User;
import com.ruanku.service.UserService;
import com.ruanku.util.AppException;

public class ToUserCenterAction extends ActionSupport {
	private List<Order> olist;
	private OrderBusiUser recentorder;
	private Order order;
	private User user;


	@Override
	public String execute(){
		/*
		 * 验证用户已经登录，跳转到用户中心
		 * 没有登录则重定向到ToLoginAction
		 */
		//获取session对象
		Map session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)session.get("user");
		
		
		if(user != null){
			try {
				int id = user.getId();
				OrderDao odao = new OrderDaoImpl();
				ClothesOfOrderDao cdao = new ClothesOfOrderDaoImpl();
//				olist = odao.findOrderByUserId(id);
				recentorder = odao.findRecentOrderBusi(id);
				recentorder.setOrderItems((cdao.findClothesOfOrder(recentorder.getId())));
				
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "user_center";
		}
		else {
			return "login";
		}
	}
	

	public List<Order> getOlist() {
		return olist;
	}

	public void setOlist(List<Order> olist) {
		this.olist = olist;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public OrderBusiUser getRecentorder() {
		return recentorder;
	}


	public void setRecentorder(OrderBusiUser recentorder) {
		this.recentorder = recentorder;
	}
}
