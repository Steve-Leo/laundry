package com.ruanku.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.dao.OrderDao;
import com.ruanku.dao.impl.OrderDaoImpl;
import com.ruanku.model.BasketBusi;
import com.ruanku.model.Order;
import com.ruanku.model.OrderBusiUser;
import com.ruanku.model.RechargeRecord;
import com.ruanku.model.User;
import com.ruanku.service.ShopService;
import com.ruanku.service.UserService;
import com.ruanku.util.AppException;
import com.ruanku.util.Constant;

public class UserManageAction extends ActionSupport {

	private String name;
	private String address;
	private String telephone;
	private String message;
	private double money;
	private User user;
	private String new_pas;
	private String old_pas;
	private String comfirm;
	private RechargeRecord recharge;
	private List<RechargeRecord> rlist;
	private List<Order> clist;//消费记录
	private List<OrderBusiUser> oblist;
	private List<BasketBusi> bblist;
	private Integer type = null;
	private String ordernum = "";
	private int orderId;
	private String result = "failed";
	private Order myOrder;
	private Integer page = null;
	private int total;
	private int  shopid;
	private int id;
	private int count;
	private boolean verified = false;
	//private String userLogin = "true";
	
	private UserService userService = new UserService();
	

	
	public String modifyPayPassword()
	{
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		String msg = null;
		if(user == null){
			return "login";
		}
		try
		{
			int id = user.getId();
			boolean flag = userService.modifyPayPassword(id, old_pas, new_pas);
			if(flag)
			{
				msg = "修改用户信息成功";
				session.put("msg", msg);
				return null;
			}
			else
			{
				msg = "修改用户信息失败";
				session.put("msg", msg);
				return null;
			}
		}catch(AppException e)
		{
			return "error";
		}		 
	}
	
	public String verifyUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String password = request.getParameter("password");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User currentUser = (User)session.get("user");
		User nUser = new User();
		nUser.setAccount(currentUser.getAccount());
		nUser.setPassword(password);
		try {
			verified = userService.verifyUser(nUser);
		} catch (AppException e) {
			e.printStackTrace();
		}
		return "json";
	}
	
	public String modifyPassword()
	{
		Map session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String password = request.getParameter("password");
		User user = (User) session.get("user");
		if(user == null){
			return "login";
		}
		try
		{
			String account = user.getAccount();
			boolean flag = userService.modifyPassword(account, user.getPassword(), password);
			if(flag)
			{
				message = "修改密码成功";
				session.remove("user");
				verified = true;
				return "json";
			}
			else
			{
				message = "密码错误，请核对原密码是否正确";
				verified = false;
				return "json";
			}
		}catch(AppException e)
		{
			return "error";
		}		 
	}
	
	public String modifyUser()
	{
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if(user == null){
			return "login";
		}
		try
		{
			int id = user.getId();
			boolean flag = userService.modifyUserInfo(id, name, address, telephone);
			if(flag)
			{
				user.setName(name);
				user.setAddress(address);
				user.setTel(telephone);
				session.put("user", user);
				message = "修改用户信息成功";
				return null;
			}
			else
			{
				message = "修改用户信息失败";
				return "error";
			}
		}catch(AppException e)
		{
			return "error";
		}		 
		
	}
	
	public String showTransactionRecord()
	{
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if(user == null){
			return "login";
		}
		try
		{
			int id = user.getId();
			rlist = userService.rechargeDetail(id);
			OrderDao odao = new OrderDaoImpl();
			clist = odao.findOrderByUserIdAndPayMode(id, 1); //1为站内支付
			return "transactionRecord";
		}catch(AppException e)
		{
			e.printStackTrace();
			return "error";
		}		 
					
	}
	
	public String myOrders(){
		//获取session对象
		Map session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		
		if(user != null){
			/*try {
				int id = user.getId();
				if(type != null && type.equals(0)){
					type = null;
				}
				setOblist(userService.getOrderBusi(id, type));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			return "my_order";
		}
		else {
			return "login";
		}
	}
	
	public String orderContent(){
		Map session = ActionContext.getContext().getSession();
		user = (User)(session.get("user"));
		UserService userService = new UserService();	
		try {
			if (user == null) {
				return "login";
			}
			int id = user.getId();
			if(page == null)
				page = 1;
			if(type != null && type.equals(0)){
				type = null;
			}
			total = userService.getTotalOrderNum(id, type);
			setOblist(userService.getOrderBusi(id, type,page));
		} catch (AppException e) {
			e.printStackTrace();
		}
		return "order_content";
	}
	
	public String findOrder(){
		//获取session对象
		Map session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		
		if(user != null){
			try {
				setOblist(userService.getOrderBusiByOrdernum(ordernum));
				page = 1;
				total = 1;
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "order_content";
		}
		else {
			return "login";
		}
	}
	
	public String cancelOrder(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		if(user != null){
			if(!isOrderOperationLegal(orderId,Constant.order.unpaid)){
				result = "changed";
				return "json";
			}
			try {
				if(userService.cancelOrder(orderId)){
					result = "success";
				}else{
					result = "failed";
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "json";
	}
	
	public String cancelOrderWithRefund(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		if(user != null){
			try {
				Order order = userService.getOrderById(orderId);
				if(order.getState() != Constant.order.pendingOrders){
					result = "changed";
					return "json";
				}
				if(userService.cancelOrder(orderId)){
					boolean temp = userService.cancelOrderWithRefund(user.getId(),orderId,order.getTotalAmount());
					if(temp){
						session.put("user",userService.getUserById(user.getId()));
					}
					result = "success";
				}else{
					result = "failed";
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "json";
	}
	
	//确认收货
	public String confirmOrder(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		if(user != null){
			if(!isOrderOperationLegal(orderId,Constant.order.received)){
				result = "changed";
				return "json";
			}
			try {
				if(userService.confirmOrder(orderId)){
					result = "success";
				}else{
					result = "failed";
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "json";
	}
	
	public String toPayOrder() throws UnsupportedEncodingException{
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		
		if(user != null){
			if(!isOrderOperationLegal(orderId,Constant.order.unpaid)){
				result = "changed";
				return "json";
			}
			try {
				List<OrderBusiUser> orders = userService.getOrderBusiByOrderId(orderId);
				if(orders.size() > 0){
					user = userService.getUserById(user.getId());
					OrderBusiUser order = orders.get(0);
					ordernum = order.getOrdernum();
					orderId = order.getId();
					message = String.valueOf(order.getTotalAmount());
					address = order.getShopName();
					money = user.getMoney();
					
					//第三方支付需要的数据
					HttpServletRequest request = ServletActionContext.getRequest();
					request.setAttribute("ordernum", ordernum);
					request.setAttribute("total", "0.01");
				}
				else
					return "error";
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "payorder";
	}
	
	public String payOrder(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		
		if(user != null){
			try {
				//站内支付
				if(type == 1){
					if(userService.checkPayPass(user.getId(), old_pas)){
						if(userService.payOrder(user.getId(),String.valueOf(orderId), type))
							result = "success";
						else
							result = "failed";
					}else
						result = "wrongpass";
				}
				else{
					if(userService.payOrder(user.getId(),ordernum, type))
						result = "success";
					else
						result = "failed";
				}
				session.put("user", userService.getUserById(user.getId()));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "json";
	}
	
	public String toEvaluate(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		//获取user并验证
		user = (User)(session.get("user"));
		if(user != null){
			return "evaluate";
		}
		else {
			return "login";
		}
	}
	
	public String evaluate(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		
		if(user != null){
			try {
				if(message.equals("")){
					message = "默认好评";
				}
				if(userService.evaluate(orderId,message)){
					result = "success";
				}else{
					result = "failed";
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "json";
	}
	
	public String myBasket(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		
		if(user != null){
			try {
				setBblist(userService.getBasket(user.getId()));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "mybasket";
	}
	
	public String updateBasket(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		result = "failed";
		if(user != null){
			try {
				message = message.substring(0,message.length()-1);
				String [] strs = message.split(",");
				Map<Integer,Integer> values = new HashMap<Integer,Integer>();
				for(String str:strs){
					String [] temp = str.split(":");
					values.put(Integer.valueOf(temp[0]), Integer.valueOf(temp[1]));
				}
				//判断是否能够提交，若是从支付页回退过来则拒绝
				if(!userService.checkUpdateLegal(user.getId(), values)){
					result = "changed";
				}else {
					if(userService.updateBasket(user.getId(),values)){
						result = "success";
					}
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "json";
	}
	
	public String deleteBasket(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		result = "failed";
		if(user != null){
			try {
				message = message.substring(0,message.length()-1);
				if(userService.deleteBasket(message)){
					result = "success";
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "json";
	}
	
	public String toOrderConfirm(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		
		if(user != null){
			try {
				User temp = userService.login(user.getAccount(), user.getPassword());
				address = temp.getAddress();
				name = temp.getName();
				telephone = temp.getTel();
				
				//以购物车方式进入订单确认页
				if(session.get("submit_mode").equals("basket")){
					//message中记录shopId
					message = message.substring(0,message.length()-1);
					
					//判断购物车项是否为空
					if(userService.getBasketByShop(temp.getId(), message).isEmpty()){
						message = "提交失败，购物车内容已更改！";
						return "mybasket";
					}
					//更新购物车
					String [] strs = ordernum.split(",");
					Map<Integer,Integer> values = new HashMap<Integer,Integer>();
					for(String str:strs){
						String [] tempArray = str.split(":");
						values.put(Integer.valueOf(tempArray[0]), Integer.valueOf(tempArray[1]));
					}
					if(!userService.checkUpdateLegal(user.getId(), values)){
						message = "提交失败，购物车内容已更改！";
						return "mybasket";
					}
					userService.updateBasket(user.getId(),values);
					//获取确认页需要显示的内容
					setBblist(userService.getBasketByShop(temp.getId(), message));
				}else if(session.get("submit_mode").equals("direct")){
					String [] strs = message.split(",");
					setBblist(userService.getSingleBasketBusi(String.valueOf(shopid), Integer.valueOf(strs[0]), Integer.valueOf(strs[1])));
				}else{
					return "error";
				}
				
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "order_confirm";
	}
	
	public String orderConfirm(){
		//获取session对象
		Map<String,Object> session = ActionContext.getContext().getSession();
		//获取user并验证
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		result = "failed";
		if(user != null){
			try {
				User userTemp = userService.login(user.getAccount(), user.getPassword());
				message = message.substring(0,message.length()-1);
				Order order = new Order();
				order.setAddress(address);
				order.setTotalAmount((float)money);
				//用orderId暂存shopId的数据
				int shopId = orderId;
				order.setShopId(orderId);
				order.setTel(telephone);
				order.setReceiver(name);
				order.setUserId(userTemp.getId());
				String [] strs = message.split(",");
				Map<String,Integer> values = new HashMap<String,Integer>();
				for(String str:strs){
					String [] temp = str.split(":");
					values.put(temp[0], Integer.valueOf(temp[1]));
				}
				orderId = userService.createOrder(order,values);
				if(session.get("submit_mode").equals("basket")){
					userService.deleteBasketByShopId(userTemp.getId(),shopId);
				}
				result = "success";
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "json";
	}
	
	
	public String recharge(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		user = (User)(session.get("user"));
		UserService userService = new UserService();
		result = "failed";
		if(user != null){
			try {
				if(userService.recharge(user.getId(), money)){
					session.put("user", userService.getUserById(user.getId()));
					result = "success";
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return "login";
		}
		return "json";
	}
	
	
	public String checkOrderOperationLegal(){
		// 获取session对象
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 获取user并验证
		result = "failed";
		user = (User) (session.get("user"));
		if (user != null) {
			int state = 0;
			if(message.equals("evaluate")){
				state = Constant.order.paid; 
			}else if(message.equals("pay")){
				state = Constant.order.unpaid; 
			}
			if(isOrderOperationLegal(orderId,state)){
				result = "success";
			}else{
				result = "changed";
			}
		}
		return "json";
	}
	
	/**
	 * 界面加入购物车方法
	 * 杨扬
	 * @return
	 */
	public String addClothes() {
		Map<String,Object> session = ActionContext.getContext().getSession();
		//获取user并验证
		user = (User)(session.get("user"));
		if (user ==null) {
			result = "failed";
			return "json";
		}
		if (count == 0) {
			count = 1;
		}
		boolean success = false;
		try {
			success = userService.addClothes(id, count, 
					user.getId(),shopid);
			if (!success) {
				return "error";
			}
			result = "success";
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json";
	}
	
	public boolean isOrderOperationLegal(int orderid,int state){
		try{
			Order order = userService.getOrderById(orderId);
			if(order == null || order.getState() != state){
				return false;
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNew_pas() {
		return new_pas;
	}

	public void setNew_pas(String new_pas) {
		this.new_pas = new_pas;
	}

	public String getOld_pas() {
		return old_pas;
	}

	public void setOld_pas(String old_pas) {
		this.old_pas = old_pas;
	}

	public String getComfirm() {
		return comfirm;
	}

	public void setComfirm(String comfirm) {
		this.comfirm = comfirm;
	}

	public List<RechargeRecord> getRlist() {
		return rlist;
	}

	public void setRlist(List<RechargeRecord> rlist) {
		this.rlist = rlist;
	}

	public RechargeRecord getRecharge() {
		return recharge;
	}

	public void setRecharge(RechargeRecord recharge) {
		this.recharge = recharge;
	}
	
	public List<OrderBusiUser> getOblist() {
		return oblist;
	}

	public void setOblist(List<OrderBusiUser> oblist) {
		this.oblist = oblist;
	}
	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}

	public double getMoney() {
		return money;
	}
	
	public Order getMyOrder() {
		return myOrder;
	}

	public void setMyOrder(Order myOrder) {
		this.myOrder = myOrder;
	}

	public List<BasketBusi> getBblist() {
		return bblist;
	}

	public void setBblist(List<BasketBusi> bblist) {
		this.bblist = bblist;
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	public String orderDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String orderId = request.getParameter("orderId");
		try {
			if (orderId == null || orderId.equals("")) {
				return ERROR;
			}
			myOrder = userService.getSingleOrderById(Integer.parseInt(orderId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (AppException e) {
			e.printStackTrace();
		}
		return "json";
	}
	
	public List<Order> getClist() {
		return clist;
	}

	public void setClist(List<Order> clist) {
		this.clist = clist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	
}
