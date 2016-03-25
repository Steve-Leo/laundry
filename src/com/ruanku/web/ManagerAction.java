package com.ruanku.web;

import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.model.Manager;
import com.ruanku.model.PageBean;
import com.ruanku.model.Shop;
import com.ruanku.model.User;
import com.ruanku.service.ManagerService;
import com.ruanku.util.AppException;
import com.ruanku.util.JsonUtil;
import com.ruanku.util.ResponseUtil;

public class ManagerAction extends ActionSupport {

	private String name;
	private String password;
	private String message;
	private String result;
	private int applicationTotal;
	private int userTotal;
	private int shopTotal;
	private ManagerService managerService = new ManagerService();
	
	public int getUserTotal() {
		return userTotal;
	}

	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}

	public int getShopTotal() {
		return shopTotal;
	}

	public void setShopTotal(int shopTotal) {
		this.shopTotal = shopTotal;
	}


	public int getApplicationTotal() {
		return applicationTotal;
	}

	public void setApplicationTotal(int applicationTotal) {
		this.applicationTotal = applicationTotal;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String toLogin(){
		return "adminLogin";
	}
	
	/**
	 * 实现人：杨扬
	 * 方法作用：获得站内的所有用户和店铺数
	 * @return 返回String，用于strust跳转
	 */
	public String userAndShopTotal() {
		try {
			userTotal = managerService.userTotal(null);
			shopTotal = managerService.shopTotal(null);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
	}

	/**
	 * 实现人：杨扬
	 * 方法作用：
	 * @return 返回String，用于strust跳转
	 */
	public String getStatistics() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			String shopName = request.getParameter("shopName");
			if (page == null || rows == null) {
				return NONE;
			}
			String formatMonth = request.getParameter("formatMonth");
			PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			ResultSet resultSet = managerService.getStatistics(pageBean,formatMonth,shopName);
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(resultSet);
			int count = managerService.staTotal(formatMonth,shopName);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", jsonArray);
			jsonObject.put("total", count);
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 实现人：杨扬
	 * 方法作用：返回所有用户的信息
	 * @return 返回String，用于strust跳转
	 */
	public String getUsersInfo() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			String account = request.getParameter("account");
			if (page == null || rows == null) {
				return NONE;
			}
			PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			ResultSet resultSet = managerService.getUsersInfo(pageBean,account);
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(resultSet);
			int count = managerService.userTotal(account);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", jsonArray);
			jsonObject.put("total", count);
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}
	
	
	public String getApplyTotal() {
		try {
			applicationTotal = managerService.shopApplyTotal();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
		
	}
	
	
	public String getShopApplies(){
		try{
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			String page=request.getParameter("page");
			String rows = request.getParameter("rows");
			if (page == null || rows == null) {
				return NONE;
			}
			PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			ResultSet resultSet = managerService.getApplications(pageBean);
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(resultSet);
			int count = managerService.shopApplyTotal();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", jsonArray);
			jsonObject.put("total", count);
			ResponseUtil.write(response, jsonObject);
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}
	
	
	public String updateUser() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String id = request.getParameter("id");
			String account = request.getParameter("account");
			String tel = request.getParameter("tel");
			String mail = request.getParameter("mail");
			String address = request.getParameter("address");
			String name = request.getParameter("name");
			String money = request.getParameter("money");
			User user = new User(Integer.parseInt(id), account, tel, mail, 
					Double.parseDouble(money), address, name);
			int result = managerService.updateUser(user);
			if (result == 0) {
				setResult("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
	}

	public String updateShop() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String id = request.getParameter("id");
			String shopname = request.getParameter("shopname");
			String tel = request.getParameter("tel");
			String comment = request.getParameter("comment");
			String address = request.getParameter("address");
			String state = request.getParameter("state");
			//String money = request.getParameter("money");
			Shop shop = new Shop(Integer.parseInt(id), shopname, address, tel, comment, 
					Integer.parseInt(state), 0);
			int result = managerService.updateShop(shop);
			if (result == 0) {
				setResult("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
	}
	
	public String deleteUser() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String id = request.getParameter("id");
			int result = managerService.deleteUser(id);
			if (result == 0) {
				setResult("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
	}

	public String deleteShop() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String id = request.getParameter("id");
			int result = managerService.deleteShop(id);
			if (result == 0) {
				setResult("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
	}
	
	public String deleteUsers() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userIds = request.getParameter("userIds");
			int result = managerService.deleteUsers(userIds);
			if (result == 0) {
				setResult("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
	}

	public String deleteShops() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String shopIds = request.getParameter("shopIds");
			int result = managerService.deleteShops(shopIds);
			if (result == 0) {
				setResult("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
	}
	
	public String shopApprove() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String shopId = request.getParameter("shopId");
			if (shopId == null) {
				return NONE;
			}
			String approve = request.getParameter("approve");
			int result = managerService.shopApprove(shopId, approve);
			if (result == 0) {
				setResult("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "result";
	}

	public String getShopsInfo(){
		try{
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			String page=request.getParameter("page");
			String rows = request.getParameter("rows");
			String shopName = request.getParameter("shopName");
			if (page == null || rows == null) {
				return NONE;
			}
			PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			ResultSet resultSet = managerService.getShopsInfo(pageBean,shopName);
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(resultSet);
			int count = managerService.shopTotal(shopName);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", jsonArray);
			jsonObject.put("total", count);
			ResponseUtil.write(response, jsonObject);
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}
	
	public String getDetailOrders() {
		try{
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			String page=request.getParameter("page");
			String rows = request.getParameter("rows");
			String shop_id = request.getParameter("shop_id");
			String formatMonth = request.getParameter("formatMonth");
			if (page == null || rows == null) {
				return NONE;
			}
			PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			ResultSet resultSet = managerService.getDetailInfo(shop_id, formatMonth, pageBean);
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(resultSet);
			int count = managerService.getDetailCount(shop_id, formatMonth);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", jsonArray);
			jsonObject.put("total", count);
			ResponseUtil.write(response, jsonObject);
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}

	public String login() {
		try {
			Manager manager = managerService.login(name, password);
			if (manager != null) {
				// 登录成功，将用户对象保存到session
				// 获取session
				Map<String,Object> session = ActionContext.getContext().getSession();
				// 将用户对象保存到session中
				session.put("manager", manager);
				return "adminIndex";
			} else {
				// 登录失败，跳转到登录界面
				message = "用户名或密码错误！";
				return "adminLogin";
			}
		} catch (AppException e) {
			e.printStackTrace();
			// 系统异常，跳转到异常页面
			return "error";
		}
	}
	
	public String logout(){
		//获取session
		Map<String,Object> session = ActionContext.getContext().getSession();
		//移除session中的用户对象
		session.remove("manager");
		//跳转到主页
		return "adminLogin";
	}
	
	public String toAdminIndex(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		Manager manager = (Manager)session.get("manager");
		if(manager != null){
			return "adminIndex";
		}
		else {
			return "adminLogin";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
