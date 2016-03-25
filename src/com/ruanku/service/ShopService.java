package com.ruanku.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.ruanku.dao.ClothesDao;
import com.ruanku.dao.ClothesOfOrderDao;
import com.ruanku.dao.OrderDao;
import com.ruanku.dao.ShopDao;
import com.ruanku.dao.WorkerDao;
import com.ruanku.dao.impl.BasketDaoImpl;
import com.ruanku.dao.impl.ClothesDaoImpl;
import com.ruanku.dao.impl.ClothesOfOrderDaoImpl;
import com.ruanku.dao.impl.OrderDaoImpl;
import com.ruanku.dao.impl.ShopDaoImpl;
import com.ruanku.dao.impl.WorkerDaoImpl;
import com.ruanku.model.Clothes;
import com.ruanku.model.ClothesOfOrder;
import com.ruanku.model.Order;
import com.ruanku.model.OrderBusiShop;
import com.ruanku.model.Shop;
import com.ruanku.model.Statistics;
import com.ruanku.model.Worker;
import com.ruanku.util.AppException;
import com.ruanku.util.Constant;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ShopService {
	private ShopDao shopDao = new ShopDaoImpl();
	private WorkerDao workerDao = new WorkerDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	private ClothesOfOrderDao cooDao = new ClothesOfOrderDaoImpl();
	private ClothesDao cdao = new ClothesDaoImpl();
	
	public List<Shop> getPassShop()throws AppException
	{
		List<Shop> list = null;
		try {
			
			list = shopDao.getShopsByState(Constant.shop.pass);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.getAllShop");
		}
		return list;
	}
	
	public List<Statistics> queryShopStatistics(int  shopId)throws AppException
	{
		List<Statistics> list = null;
		try {
			
			list = orderDao.queryShopStatistics(shopId);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.queryShopStatistics");
		}
		return list;
	}
	
	public boolean modifyCloth(Clothes cloth)throws AppException
	{
		boolean flag = false;
		try {
			
			flag = cdao.updateClothes(cloth);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.modifyCloth");
		}
		return flag;
	}
	
	public boolean deleteCloth(int clothId)throws AppException
	{
		boolean flag = false;
		try {
			
			flag = cdao.deleteClothes(clothId);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.deleteCloth");
		}
		return flag;
	}
	
	public List<Clothes> findClothesbyShopId(int  shopId)throws AppException
	{
		List<Clothes> list = null;
		try {
			
			list = cdao.findClothesbyShopId(shopId);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.findClothesbyShopId");
		}
		return list;
	}
	
	public boolean addCloth(Clothes clothes)throws AppException
	{
		boolean flag = false;
		try {
			
			flag = cdao.saveClothes(clothes);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.addCloth");
		}
		return flag;
	}
	
	public boolean updateWorker(Worker worker)throws AppException
	{
		boolean flag = false;
		try {
			
			flag = workerDao.updateWorker(worker);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.updateWorker");
		}
		return flag;
	}
	
	public boolean addworker(Worker worker)throws AppException
	{
		boolean flag = false;
		try {
			
			flag = workerDao.save(worker);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.addworker");
		}
		return flag;
	}
	
	public boolean changeOrderState(int orderId,int state)throws AppException
	{
		boolean flag = false;
		try {
			
			flag = orderDao.updateState(orderId, state);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.changeOrderState");
		}
		return flag;
	}
	
	public int getTotalOrderNum(int shopId,int type) throws AppException {
		int num = 0;
		try {
			num = orderDao.getTotalShopOrderNum(shopId, type);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.getTotalOrderNum");
		}
		return num;
	}
	
	public List<OrderBusiShop> findAllOrderBusiShop(int shopId,int page,int type) throws AppException {

		List<OrderBusiShop> list = new ArrayList<OrderBusiShop>();
		List<Order> olist = null;
		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			olist = orderDao.findOrderByShopId(shopId,page,type);
			for(Order order:olist)
			{
				OrderBusiShop obs = new OrderBusiShop();
				obs.setAddress(order.getAddress());
				obs.setChargeTime(order.getChargeTime());
				obs.setComment(order.getComment());
				obs.setCommentTime(order.getCommentTime());
				obs.setGrade(order.getGrade());
				obs.setId(order.getId());
				obs.setOrdernum(order.getOrdernum());
				obs.setPayMode(order.getPayMode());
				obs.setPaytime(order.getPaytime());
				obs.setReceivedTime(order.getReceivedTime());
				obs.setReceiver(order.getReceiver());
				obs.setShopId(order.getShopId());
				obs.setState(order.getState());
				obs.setTel(order.getTel());
				obs.setTotalAmount(order.getTotalAmount());
				obs.setUserId(order.getUserId());
				List<ClothesOfOrder> coolist = new ArrayList<ClothesOfOrder>();
				coolist = cooDao.findClothesOfOrder(order.getId());
				obs.setItems(coolist);
				list.add(obs);
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.findAllOrderBusiShop");
		}
		return list;
	}
	
	public List<OrderBusiShop> findOrderBusiByOrdernum(int shopId,String ordernum) throws AppException {

		List<OrderBusiShop> list = new ArrayList<OrderBusiShop>();
		List<Order> olist = new ArrayList<Order>();
		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			Order item = orderDao.getOrderByOrdernum(ordernum);
			if(item == null)
				return list;
			olist.add(item);
			for(Order order:olist)
			{
				OrderBusiShop obs = new OrderBusiShop();
				obs.setAddress(order.getAddress());
				obs.setChargeTime(order.getChargeTime());
				obs.setComment(order.getComment());
				obs.setCommentTime(order.getCommentTime());
				obs.setGrade(order.getGrade());
				obs.setId(order.getId());
				obs.setOrdernum(order.getOrdernum());
				obs.setPayMode(order.getPayMode());
				obs.setPaytime(order.getPaytime());
				obs.setReceivedTime(order.getReceivedTime());
				obs.setReceiver(order.getReceiver());
				obs.setShopId(order.getShopId());
				obs.setState(order.getState());
				obs.setTel(order.getTel());
				obs.setTotalAmount(order.getTotalAmount());
				obs.setUserId(order.getUserId());
				List<ClothesOfOrder> coolist = new ArrayList<ClothesOfOrder>();
				coolist = cooDao.findClothesOfOrder(order.getId());
				obs.setItems(coolist);
				list.add(obs);
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.findOrderBusiByOrdernum");
		}
		return list;
	}
	
	public boolean delWorker(int id) throws AppException {

		boolean flag = false;

		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			
				flag = workerDao.delWorker(id);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.delWorker");
		}
		return flag;
	}
	
	public boolean modifyShop(Shop shop) throws AppException {

		boolean flag = false;

		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			
				flag = shopDao.updateShop(shop);
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.modifyShop");
		}
		return flag;
	}
	
	 public List<Worker> workerList(int shopId) throws AppException {

		 List<Worker> list;

		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			list = workerDao.findByShopId(shopId);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.workerList");
		}
		return list;
	}

	public boolean register(Shop shop) throws AppException {

		boolean flag = false;

		/*
		 * 调用UserDao的isExist()方法验证，然后调用save(),返回操作标记
		 */
		try {
			if (!shopDao.isUsernameExist(shop.getUsername())) {
				flag = shopDao.registerShop(shop);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.register");
		}
		return flag;
	}

	public Shop login(String name, String password) throws AppException {
		/*
		 * 获取用户编号
		 */
		int id = 0;
		Shop shop = null;
		try {
			id = shopDao.login(name, password);
			if (id > 0) {
				shop = shopDao.getShop(id);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.ShopService.login");
		}
		return shop;
	}
	
	public JSONArray getShopByLocation(String province, String city) throws AppException {
		return shopDao.getShopsByLocation(province, city);
	}
	
	public Shop getShopsById(String id) throws AppException{
		return shopDao.getShopsById(id);
	}
	
	public Clothes getClothesById(String id) throws Exception {
		if (id ==null) {
			return null;
		}
		return cdao.findClothesById(Integer.parseInt(id));
	}
	public List<Clothes> finClothesBySearch(String searchText) throws AppException {
		return cdao.findClothesbySearch(searchText);
	}
	public List<Clothes> finClothesBySearchAndShopId(String searchText,int shopId) throws AppException {
		return cdao.findClothesbySearchAndShopId(shopId,searchText);
	}
	public List<Shop> getShopsBySearch(String searchText) throws AppException {
		return shopDao.getShopsBySearch(searchText);
	}
	/**
	 * 返回店铺的所有评论
	 * @param shopID
	 * @return
	 * @throws AppException
	 */
	public ResultSet shopComment(int shopID) throws AppException {
		return orderDao.shopComment(shopID);
	}
}
