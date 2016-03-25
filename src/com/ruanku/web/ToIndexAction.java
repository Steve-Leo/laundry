package com.ruanku.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.model.Shop;
import com.ruanku.service.ShopService;
import com.ruanku.util.AppException;

public class ToIndexAction extends ActionSupport {
	private ShopService shopservice = new ShopService();
	/**
	 * 处理主页面的跳转
	 */
	private List<Shop> list;
	private boolean flag;

	/**
	 * 根据用户与店家的距离排序显示店铺信息
	 * 若进入时，未获取到用户的位置，则显示全部店家信息
	 * 编写人：曾盼
	 */
	@Override
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie allCookie[] = request.getCookies();
		String keyname = null;
		String lng = null;//经度
		String lat = null;//维度
		flag = false;
		//从cookies中获取用户的位置信息
		if (allCookie != null && allCookie.length != 0) {
			for (int i = 0; i < allCookie.length; i++) {
				keyname = allCookie[i].getName();
				if (keyname.equals("lng"))
					lng = allCookie[i].getValue();
				else if (keyname.equals("lat")) {
					flag = true;
					lat = allCookie[i].getValue();
				}

			}
		}
		//判断是否获取到用户的位置信息
		if (false == flag) {
			try {
				//未获取到用户的位置信息
				list = shopservice.getPassShop();
				request.setAttribute("list", list);
				//不展示距离
				request.setAttribute("f", String.valueOf("0"));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//获取到用户的位置信息
			double lngd = Double.parseDouble(lng);
			double latd = Double.parseDouble(lat);
			try {
				list = shopservice.getPassShop();
				for (Shop shop : list) {
					double shoplat = shop.getLatitude();
					double shoplng = shop.getLongitude();
					//根据用户和商铺的经纬度给商铺赋一个距离
					double distance = this.Distance(lngd, latd, shoplng,shoplat);
					shop.setDistant(distance);
				}
				//根据距离排序
				Comparator<Shop> comparator = new Comparator<Shop>() {
					public int compare(Shop s1, Shop s2) {
						// 距离排序
						if (s1.getDistant() > s2.getDistant()) {
							return 1;
						} else if (s1.getDistant() == s2.getDistant()) {
							return 0;
						}
						return -1;
					}
				};
				Collections.sort(list, comparator);
				request.setAttribute("list", list);
				//展示距离
				request.setAttribute("f", String.valueOf("1"));
				for (int i = 0; i < list.size(); i++) {
					Shop shop = list.get(i);
					System.out.println("id:" + shop.getId() + "   店名:"
							+ shop.getShopname() + "距离	" + shop.getDistant());
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return "index";
	}

	/**
	 * 实现人：曾盼
	 * 方法作用：返回两点间的距离
	 * 参数：两个坐标的经纬度
	 * @return 返回double，
	 */
	public static double Distance(double long1, double lat1, double long2,
			double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2
				* R
				* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
						* Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	public List<Shop> getList() {
		return list;
	}

	public void setList(List<Shop> list) {
		this.list = list;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
	public String searchShops() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie allCookie[] = request.getCookies();
		String keyname = null;
		String lng = null;
		String lat = null;
		flag = false;
		if (allCookie != null && allCookie.length != 0) {
			for (int i = 0; i < allCookie.length; i++) {
				keyname = allCookie[i].getName();
				if (keyname.equals("lng"))
					lng = allCookie[i].getValue();
				else if (keyname.equals("lat")) {
					flag = true;
					lat = allCookie[i].getValue();
				}

			}
		}
		if (false == flag) {
			try {
				String searchText = request.getParameter("searchText");
				if (searchText == null) {
					searchText = "";
				}
				list = shopservice.getShopsBySearch(searchText);
				request.setAttribute("list", list);
				request.setAttribute("f", String.valueOf("0"));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			double lngd = Double.parseDouble(lng);
			double latd = Double.parseDouble(lat);
			try {
				String searchText = request.getParameter("searchText");
				if (searchText == null) {
					searchText = "";
				}
				System.out.println(searchText);
				list = shopservice.getShopsBySearch(searchText);
				request.setAttribute("list", list);
				for (Shop shop : list) {
					double shoplat = shop.getLatitude();
					double shoplng = shop.getLongitude();
					double distance = this.Distance(lngd, latd, shoplng,shoplat);
					shop.setDistant(distance);
				}

				Comparator<Shop> comparator = new Comparator<Shop>() {
					public int compare(Shop s1, Shop s2) {
						// 先排年龄
						if (s1.getDistant() > s2.getDistant()) {
							return 1;
						} else if (s1.getDistant() == s2.getDistant()) {
							return 0;
						}
						return -1;
					}
				};
				Collections.sort(list, comparator);
				request.removeAttribute("list");
				request.setAttribute("list", list);
				request.setAttribute("f", String.valueOf("1"));
				for (int i = 0; i < list.size(); i++) {
					Shop shop = list.get(i);
					System.out.println("id:" + shop.getId() + "   店名:"
							+ shop.getShopname() + "距离	" + shop.getDistant());
				}
			} catch (AppException e) {
				e.printStackTrace();
			}
		}
		return "index";
	}
}
