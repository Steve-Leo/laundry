package com.ruanku.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ruanku.dao.BasketDao;
import com.ruanku.dao.ClothesDao;
import com.ruanku.dao.ClothesOfOrderDao;
import com.ruanku.dao.OrderDao;
import com.ruanku.dao.RechargeRecordDao;
import com.ruanku.dao.ShopDao;
import com.ruanku.dao.UserDao;
import com.ruanku.dao.impl.BasketDaoImpl;
import com.ruanku.dao.impl.ClothesDaoImpl;
import com.ruanku.dao.impl.ClothesOfOrderDaoImpl;
import com.ruanku.dao.impl.OrderDaoImpl;
import com.ruanku.dao.impl.RechargeRecordDaoImpl;
import com.ruanku.dao.impl.ShopDaoImpl;
import com.ruanku.dao.impl.UserDaoImpl;
import com.ruanku.model.Basket;
import com.ruanku.model.BasketBusi;
import com.ruanku.model.Clothes;
import com.ruanku.model.ClothesOfOrder;
import com.ruanku.model.Order;
import com.ruanku.model.OrderBusiUser;
import com.ruanku.model.RechargeRecord;
import com.ruanku.model.Shop;
import com.ruanku.model.User;
import com.ruanku.util.AppException;
import com.ruanku.util.Constant;
import com.ruanku.util.PrimaryGenerater;

public class UserService {
	
	//数据库访问层的实例
	UserDao userDao = new UserDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	private BasketDao basketDao = new BasketDaoImpl();
	private ClothesOfOrderDao cooDao = new ClothesOfOrderDaoImpl();
	private ClothesDao clothesDao = new ClothesDaoImpl();
	private ShopDao shopDao = new ShopDaoImpl();
	/**
	 * 注册
	 * @param user
	 * @return 成功返回true,否则返回false
	 * @throws AppException
	 */
	
	public boolean modifyPayPassword(int id,String oldPasswold,String newPassword) throws AppException{
		
		boolean flag = false;
		
		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			if(userDao.changePayPassword(id,oldPasswold,newPassword)){
				flag = true;
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.modifyPayPassword");
		}
		return flag;
	}
	
	public List<RechargeRecord> rechargeDetail(int userId) throws AppException{
		
		List<RechargeRecord> list;
		
		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			RechargeRecordDao dao = new RechargeRecordDaoImpl();
			list = dao.getRechargeRecordsByUserid(userId);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.rechargeDetail");
		}
		return list;
	}
	
	public boolean modifyPassword(String name,String oldPasswold,String newPassword) throws AppException{
		
		boolean flag = false;
		
		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			if(userDao.changePassword(name,oldPasswold,newPassword)){
				flag = true;
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.modifyPassword");
		}
		return flag;
	}
	
	public boolean register(User user) throws AppException{
		
		boolean flag = false;
		
		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			if(!userDao.isExist(user.getAccount())){
				flag = userDao.save(user);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.register");
		}
		return flag;
	}
	
	/**
	 * 登录
	 * @param name
	 * @param password
	 * @return user 用户
	 * @throws AppException
	 */
	public User login(String name,String password) throws AppException{
		/*
		 * 获取用户编号
		 */
		int id = 0;
		User user = null;
		try {
			id = userDao.login(name,password);
			if(id > 0){
				user = userDao.getUser(id);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.register");
		}
		return user;
	}
	
	public boolean modifyUserInfo(int id,String name,String address,String tel) throws AppException{
		
		boolean flag = false;
		
		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			if(userDao.UpdateUserInfo(id, name, address, tel)){
				flag = true;
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.register");
		}
		return flag;
	}
	
	public List<OrderBusiUser> getOrderBusi(int userId,Integer state,Integer page) throws AppException{
		List<OrderBusiUser> orders=null;
		
		try {
			orders = orderDao.findOrderBusi(userId,state,page);
			for(OrderBusiUser orderBusiUser:orders){
				orderBusiUser.setOrderItems(cooDao.findClothesOfOrder(orderBusiUser.getId()));
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.getOrderBusi");
		}
		return orders;
	}
	
	public int getTotalOrderNum(int userId,Integer state) throws AppException{
		int num = 0;
		try {
			num = orderDao.getTotalOrderNum(userId, state);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.getTotalOrderNum");
		}
		return num;
	}
	
	public List<OrderBusiUser> getOrderBusiByOrdernum(String ordernum) throws AppException{
		List<OrderBusiUser> orders=new ArrayList<OrderBusiUser>();

		try {
			OrderBusiUser order = new OrderBusiUser();
			order = orderDao.findOrderBusiByOrdernum(ordernum);
			if(order.getId() == -1){
				return null;
			}
			order.setOrderItems(cooDao.findClothesOfOrder(order.getId()));
			orders.add(order);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.getOrderBusiByOrdernum");
		}
		return orders;
	}
	
	public List<OrderBusiUser> getOrderBusiByOrderId(int id) throws AppException{
		List<OrderBusiUser> orders=new ArrayList<OrderBusiUser>();

		try {
			OrderBusiUser order = new OrderBusiUser();
			order = orderDao.findOrderBusiByOrderId(id);
			if(order.getId() == -1){
				return null;
			}
			order.setOrderItems(cooDao.findClothesOfOrder(order.getId()));
			orders.add(order);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.getOrderBusiByOrderId");
		}
		return orders;
	}
	
	public boolean payOrder(int userId,String orderId,int mode) throws AppException{
		boolean flag = false;
		try {
			//站内支付时更新用户存款
			if(mode==1){
				int id = Integer.valueOf(orderId);
				Order order = orderDao.getOrderById(id);
				if(order.getState() != Constant.order.unpaid){
					return false;
				}
				//更新订单状态
				userDao.updateUserAccount(userId, order.getTotalAmount()*(-1));
				orderDao.updateOrderPaid(id,mode,Constant.order.pendingOrders);
			}
			else{
				Order order = orderDao.getOrderByOrdernum(orderId);
				if(order.getState() != Constant.order.unpaid){
					return false;
				}
				orderDao.updateOrderPaidByOrdernum(orderId,mode,Constant.order.pendingOrders);
			}
			flag = true;
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.payOrder");
		}
		return flag;
	}
	
	public boolean confirmOrder(int orderId) throws AppException{
		boolean flag = false;
		try {
			orderDao.updateState(orderId,Constant.order.paid);
			flag = true;
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.confirmOrder");
		}
		return flag;
	}
	
	public boolean evaluateOrder(int orderId) throws AppException{
		boolean flag = false;
		try {
			orderDao.updateState(orderId,Constant.order.commented);
			flag = true;
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.evaluateOrder");
		}
		return flag;
	}
	
	public boolean cancelOrder(int orderId) throws AppException{
		boolean flag = false;
		try {
			orderDao.updateState(orderId,Constant.order.cancel);
			flag = true;
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.cancelOrder");
		}
		return flag;
	}
	
	public boolean evaluate(int orderId,String comment) throws AppException{
		boolean flag = false;
		try {
			orderDao.updateComment(orderId,comment);
			orderDao.updateState(orderId,Constant.order.commented);
			flag = true;
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.evaluate");
		}
		return flag;
	}
	
	public boolean checkPayPass(int userId,String password) throws AppException{
		boolean flag = false;
		try {
			User user = userDao.getUser(userId);
			if(user.getPayPwd().equals(password)){
				flag = true;
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.evaluate");
		}
		return flag;
	}
	
	public User getUserById(int userId) throws AppException{
		User user = new User();
		//标记为未找到用户
		user.setId(-1);
		try {
			user = userDao.getUser(userId);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.evaluate");
		}
		return user;
	}
	
	public List<BasketBusi> getBasket(int userId) throws AppException{
		List<BasketBusi> basketBusiList = new ArrayList<BasketBusi>();
		
		try {
			List<Basket> basketlist = basketDao.getAllClothesByUserid(userId);
			while(!basketlist.isEmpty()){
				BasketBusi b = new BasketBusi();
				List<Basket> itemList = new ArrayList<Basket>();
				int temp = basketlist.get(0).getShopId();
				b.setShopId(basketlist.get(0).getShopId());
				b.setShopName(basketlist.get(0).getShopName());
				
				Iterator<Basket> iter = basketlist.iterator();  
				while(iter.hasNext()){
					Basket item = iter.next();  
					if(item.getShopId() == temp){
						itemList.add(item);
						iter.remove();
					} 
				}
				b.setItems(itemList);
				basketBusiList.add(b);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.getBasket");
		}
		
		return basketBusiList;
	}
	
	public boolean checkUpdateLegal(int userId,Map<Integer,Integer> values) throws AppException{
		String str = "";
		Iterator<Integer> iter = values.keySet().iterator();
		while(iter.hasNext()){  
			Integer key = iter.next();
			str += key.toString()+",";
		}
		str = str.substring(0,str.length()-1);
		return basketDao.checkUpdateLegal(userId, str);
	}
	
	public boolean updateBasket(int userId,Map<Integer,Integer> values) throws AppException{
		boolean flag = false;
		
		try {
			basketDao.updateBasket(values);
			flag = true;
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.updateBasket");
		}
		
		return flag;
	}
	
	public boolean deleteBasket(String ids) throws AppException{
		boolean flag = false;
		
		try {
			flag = basketDao.deleteBasket(ids);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.deleteBasket");
		}
		
		return flag;
	}
	
	public boolean deleteBasketByShopId(int userId,int shopId) throws AppException{
		boolean flag = false;
		
		try {
			flag = basketDao.deleteBasketByShopId(userId,shopId);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.deleteBasketByShopId");
		}
		
		return flag;
	}
	
	public List<BasketBusi> getBasketByShop(int userId,String shopId) throws AppException{
		List<BasketBusi> basketBusiList = new ArrayList<BasketBusi>();
		
		try {
			List<Basket> basketlist = basketDao.getClothesByShop(userId, shopId);
			while(!basketlist.isEmpty()){
				BasketBusi b = new BasketBusi();
				List<Basket> itemList = new ArrayList<Basket>();
				int temp = basketlist.get(0).getShopId();
				b.setShopId(basketlist.get(0).getShopId());
				b.setShopName(basketlist.get(0).getShopName());
				
				Iterator<Basket> iter = basketlist.iterator();  
				while(iter.hasNext()){  
					Basket item = iter.next();  
					if(item.getShopId() == temp){
						itemList.add(item);
						iter.remove();
					} 
				}
				b.setItems(itemList);
				basketBusiList.add(b);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.getBasketByShop");
		}
		
		return basketBusiList;
	}
	
	public int createOrder(Order order,Map<String,Integer> values) throws AppException{
		int orderId = 0;
		try {
			//完善订单信息并保存
			String ordernum = PrimaryGenerater.getInstance().generaterNextNumber();
			order.setOrdernum(ordernum);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			String paytime = sdf.format(date);
			order.setPaytime(paytime);
			order.setState(Constant.order.unpaid);
			orderId = orderDao.save(order);
			if(orderId != 0){
				//保存订单项
				//获取商品信息
				Iterator<String> iter = values.keySet().iterator();
				String temp = "";
				while(iter.hasNext()){  
					temp += (String)iter.next() + ",";
				}
				temp = temp.substring(0,temp.length()-1);
				Map<Integer,Clothes> clothesMap = clothesDao.findClothesByIds(temp);
				//生成订单项
				List<ClothesOfOrder> list = new ArrayList<ClothesOfOrder>();
				iter = values.keySet().iterator();
				while(iter.hasNext()){  
					ClothesOfOrder orderitem = new ClothesOfOrder();
					String key = (String)iter.next();
					Clothes cloth = clothesMap.get(Integer.valueOf(key));
					orderitem.setClothName(cloth.getName());
					orderitem.setAmount(values.get(key));
					orderitem.setClothId(cloth.getId());
					orderitem.setOrderId(orderId);
					orderitem.setPrice(cloth.getPrice1());
					orderitem.setWashMode(cloth.getMode());
					list.add(orderitem);
				}
				//保存订单项
				if(!cooDao.multiSave(list)){
					return 0;
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.createOrder");
		}
		
		return orderId;
	}
	
	public Order getOrderById(int orderId) throws AppException{
		Order order = null;
		try {
			order = orderDao.getOrderById(orderId);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.getOrderById");
		}
		return order;
	}
	
	public boolean cancelOrderWithRefund(int userId,int orderId,double account) throws AppException{
		boolean flag = false;
		try {
			orderDao.updateState(orderId, Constant.order.cancel);
			userDao.updateUserAccount(userId, account);
			RechargeRecordDao rrDao = new RechargeRecordDaoImpl();
			RechargeRecord r= new RechargeRecord();
			r.setMoney(account);
			r.setSerialNum(PrimaryGenerater.getInstance().generaterNextNumber());
			r.setStatus(Constant.recharge.drawback);
			r.setUserId(userId);
			rrDao.reCharge(r);
			flag = true;
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.cancelOrderWithRefund");
		}
		return flag;
	}
	
	public boolean recharge(int userId,double account) throws AppException{
		boolean flag = false;
		try {
			RechargeRecordDao rrDao = new RechargeRecordDaoImpl();
			RechargeRecord r= new RechargeRecord();
			r.setMoney(account);
			r.setSerialNum(PrimaryGenerater.getInstance().generaterNextNumber());
			r.setStatus(Constant.recharge.recharge);
			r.setUserId(userId);
			rrDao.reCharge(r);
			userDao.updateUserAccount(userId, account);
			flag = true;
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.recharge");
		}
		return flag;
	}
	
	public boolean addClothes(Clothes clothes, int count, int userId) throws Exception {
		return basketDao.addClothes(clothes, count, userId);
	}
	public boolean addClothes(int clothesId, int count, int userId,int shopId) throws Exception {
		return basketDao.addClothes(clothesId, count, userId, shopId);
	}
	public Order getSingleOrderById(int orderId) throws AppException {
		Order myOrder = orderDao.getOrderById(orderId);
		List<ClothesOfOrder> myClothes = cooDao.findClothesOfOrder(orderId);
		String shopName = shopDao.getShop(myOrder.getShopId()).getShopname();
		myOrder.setClothes(myClothes);
		myOrder.setShopName(shopName);
		return myOrder;
	}
	public List<BasketBusi> getSingleBasketBusi(String shopId,int itemId,int count) throws AppException{
		List<BasketBusi> bblist = new ArrayList<BasketBusi>();
		try {
			Shop shop = shopDao.getShopsById(shopId);
			Clothes item = clothesDao.findClothesById(itemId);
			BasketBusi bb = new BasketBusi();
			bb.setShopId(Integer.valueOf(shopId));
			bb.setShopName(shop.getShopname());
			Basket basket = new Basket();
			basket.setClothId(item.getId());
			basket.setClothName(item.getName());
			basket.setPrice(item.getPrice1());
			basket.setCount(count);
			basket.setWashMode(item.getMode());
			List<Basket> list = new ArrayList<Basket>();
			list.add(basket);
			bb.setItems(list);
			bblist.add(bb);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.getSingleBasketBusi");
		}
		return bblist;
	}
	
	/**
	 * 用户验证
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public boolean verifyUser(User user) throws AppException {
		return userDao.verifyUser(user);
	}
}
