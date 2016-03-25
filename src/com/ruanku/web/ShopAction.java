package com.ruanku.web;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.model.Clothes;
import com.ruanku.model.OrderBusiShop;
import com.ruanku.model.Shop;
import com.ruanku.model.Statistics;
import com.ruanku.model.Worker;
import com.ruanku.service.ShopService;
import com.ruanku.util.AppException;
import com.ruanku.util.Constant;
import com.ruanku.util.JsonUtil;
import com.ruanku.util.ResponseUtil;
import com.ruanku.util.UploadProcess;

public class ShopAction extends ActionSupport {
	private File pic; // 保存图片
	private String picFileName; // 图片名称
	private String message;// 一些返回信息

	private String username;// 用户名
	private String password;// 密码
	private String city;// 城市
	private String address;// 详细地市
	private String shopname;// 商铺名
	private String telephone;// 电话
	private String comment;// 商铺的描述信息
	private List<String> allComments;// 所有评论
	private Shop shop;// 商铺对象
	private int id;// 商铺id
	private int state; // 商铺的审核状态
	private ShopService shopService = new ShopService();

	private List<Worker> workerList;// 员工列表

	private List<OrderBusiShop> obslist;// 订单列表

	private String name;// 员工姓名
	private int sex;// 员工性别
	private String birthday;// 员工出生年月
	private String tel;// 员工的电话号码
	private String origin;// 员工的籍贯

	private String clothesname;
	private int type = 0;
	private double priceDry;// 商品的干洗价格
	private double priceWater;// 商品的水洗价格
	private int washMode;// 商品的清洗方式
	private Clothes clothes;// 店铺的商品

	private List<Clothes> clist;// 商品列表

	private String ids;

	private double latitude;// 商铺位置的纬度
	private double longitude;// 商铺位置的经度

	private String s_province;// 商铺所在省省
	private String s_city;// 商铺所在城市

	private int page = 1;// 分页信息
	private int total = 1;// 数据总数

	private List<Statistics> slist;
	private String data;

	/**
	 * 查询商铺每个月的交易记录，盈利
	 * 
	 * @return 返回String，用于跳转
	 */
	public String queryShopStatistics() {

		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");
		System.out.println(data);

		System.out.println("------------测试测试测试---------------------");
		if (shop != null) {

			if (shop.getState() == Constant.shop.pass) {

				session.put("data", data);
			}
			return "statistics";
		} else {
			return "tologin";
		}
	}

	/**
	 * 商铺用户登出
	 * 
	 * @return 返回String，用于跳转，跳转到首页
	 */
	public String logout() {
		// 获取session
		Map session = ActionContext.getContext().getSession();
		// 移除session中的用户对象
		session.remove("shop");
		// 跳转到主页
		return "toindex";
	}

	/**
	 * 删除多条商铺员工记录
	 * 
	 * @return 返回String，用于跳转
	 */
	public String delMultiWorker() {
		Map session = ActionContext.getContext().getSession();
		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {
			String[] workerids = ids.split(",");
			// int clothid[];
			int i;

			try {
				for (i = 0; i < workerids.length; i++) {
					shopService.delWorker(Integer.parseInt(workerids[i]));
				}

				return null;

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "workerlist";
		} else {
			return "tologin";
		}
	}

	/**
	 * 删除多条商铺商铺记录
	 * 
	 * @return 返回String，用于跳转
	 */
	public String delMultiClothes() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {
			String[] clothids = ids.split(",");
			// int clothid[];
			int i;

			try {
				for (i = 0; i < clothids.length; i++) {
					shopService.deleteCloth(Integer.parseInt(clothids[i]));
				}

				return null;

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "clothesList";
		} else {
			return "tologin";
		}
	}

	public String modifyCloth() {

		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {
			Clothes cloth = new Clothes();
			Worker worker = new Worker();
			try {
				cloth.setId(id);
				cloth.setComment(comment);
				cloth.setDel(0);
				cloth.setMode(washMode);
				cloth.setName(clothesname);
				if (picFileName != null) {
					String path = this.uploadPicture();
					cloth.setPicture(path);
				} else {
					cloth.setPicture("");
				}
				cloth.setPrice1(priceDry);
				cloth.setPrice2(priceWater);
				cloth.setShopId(shop.getId());
				cloth.setType(type);

				boolean flag = shopService.modifyCloth(cloth);

				return "toShopIndex";

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "clothesList";
		} else {
			return "tologin";
		}
	}

	/**
	 * 删除店铺一条商品记录
	 * 
	 * @return 返回String，用于跳转
	 */
	public String deleteCloth() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {

			try {
				if (shopService.deleteCloth(id))

					return null;

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "clothesList";
		} else {
			return "tologin";
		}

	}

	/**
	 * 显示商铺的商品信息
	 * 
	 * @return 返回String，用于跳转
	 */
	public String clothesList() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {

			try {
				if (shop.getState() == Constant.shop.pass)
					clist = shopService.findClothesbyShopId(shop.getId());

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "clothesList";
		} else {
			return "tologin";
		}
	}

	/**
	 * 商铺增加一条商品记录
	 * 
	 * @return 返回String，用于跳转
	 */
	public String addCloth() {

		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {

			Clothes cloth = new Clothes();
			try {
				if (shop.getState() == Constant.shop.pass) {
					cloth.setName(clothesname);
					cloth.setShopId(shop.getId());
					cloth.setMode(washMode);
					cloth.setType(type);
					cloth.setPrice1(priceDry);
					// cloth.setPrice2(priceWater);
					cloth.setComment(comment);
					cloth.setDel(0);

					String path = this.uploadPicture();
					cloth.setPicture(path);
					shopService.addCloth(cloth);
				}
				return "toShopIndex";

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "tologin";
		} else {
			return "tologin";
		}
	}

	/**
	 * 商铺修改员工信息
	 * 
	 * @return 返回String，用于跳转
	 */
	public String modifyWorker() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {
			Worker worker = new Worker();
			try {
				worker.setId(id);
				worker.setBirthday(birthday);
				// worker.setId(id)
				worker.setName(name);
				worker.setOrigin(origin);
				worker.setSex(sex);
				worker.setShopId(shop.getId());
				worker.setTel(telephone);

				boolean flag = shopService.updateWorker(worker);

				return null;

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "workerlist";
		} else {
			return "tologin";
		}
	}

	/**
	 * 增加商铺员工记录
	 * 
	 * @return 返回String，用于跳转
	 */
	public String addworker() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {
			Worker worker = new Worker();
			try {
				worker.setBirthday(birthday);
				// worker.setId(id)
				worker.setName(name);
				worker.setOrigin(origin);
				worker.setSex(sex);
				worker.setShopId(shop.getId());
				worker.setTel(telephone);

				boolean flag = shopService.addworker(worker);

				return null;

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "workerlist";
		} else {
			return "tologin";
		}
	}

	/**
	 * 商铺更改订单的状态
	 * 
	 * @return 返回String，用于跳转
	 */
	public String changeOrderState() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if ((shop != null)) {

			try {
				if (shop.getState() == Constant.shop.pass) {
					switch (state) {
					case Constant.order.pendingOrders:
						shopService.changeOrderState(id, Constant.order.pickup);
						break;
					case Constant.order.pickup:
						shopService
								.changeOrderState(id, Constant.order.washing);
						break;
					case Constant.order.washing:
						shopService.changeOrderState(id,
								Constant.order.received);
						break;
					default:
						break;
					}
				}
				return null;

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "error";
		} else {
			return "tologin";
		}
	}

	/**
	 * 商铺显示商铺订单信息
	 * 
	 * @return 返回String，用于跳转
	 */
	public String shopOrderList() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {
			try {
				if (shop.getState() == Constant.shop.pass) {
					total = shopService.getTotalOrderNum(shop.getId(), type);
					obslist = shopService.findAllOrderBusiShop(shop.getId(),
							page, type);
				}

				// return "shopOrderList";

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "shopOrderList";
		} else {
			return "tologin";
		}

	}

	public String singleShopOrder() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {
			try {
				if (shop.getState() == Constant.shop.pass) {
					setObslist(shopService.findOrderBusiByOrdernum(
							shop.getId(), message));
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "shopOrderList";
		} else {
			return "tologin";
		}

	}

	/**
	 * 删除一条商铺员工记录
	 * 
	 * @return 返回String，用于跳转
	 */
	public String delWorker() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {

			try {
				if (shopService.delWorker(id))

					return null;

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "workerlist";
		} else {
			return "tologin";
		}

	}

	/**
	 * 修改店铺信息
	 * 
	 * @return 返回String，用于跳转
	 */
	public String modifyShop() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {
			int shopId = shop.getId();
			try {
				if (Constant.shop.refuse == shop.getState())
				{
					shop.setState(Constant.shop.uncheck);
				}
				else
				{
					shop.setState(shop.getState());
				}
				shop.setShopname(shopname);
				shop.setTel(telephone);
				shop.setComment(comment);
				String path = this.uploadPicture();
				shop.setPicture(path);
				// this.uploadPicture();
				if (shopService.modifyShop(shop))
					return "shopIndex";

			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "shopIndex";// 暂时这样写
		} else {
			return "tologin";
		}

	}

	/**
	 * 显示商铺的所有员工
	 * 
	 * @return 返回String，用于跳转
	 */
	public String workerList() {
		Map session = ActionContext.getContext().getSession();

		// 获取user并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {
			int shopId = shop.getId();
			try {
				workerList = shopService.workerList(shopId);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "workerlist";
		} else {
			return "tologin";
		}

	}

	/**
	 * 
	 * @return 返回String，用于跳转 跳转到登录界面
	 */
	public String toLogin() {
		return "login";
	}

	/**
	 * 
	 * @return 返回String，用于跳转 跳转到注册
	 */
	public String toRegister() {
		return "register";
	}

	/**
	 * 
	 * @return 返回String，用于跳转 跳转到商铺首页
	 */
	public String toShopIndex() {
		Map session = ActionContext.getContext().getSession();

		// 获取shop并验证
		shop = (Shop) session.get("shop");

		if (shop != null) {

			return "shopIndex";
		} else {
			return "tologin";
		}

	}

	/**
	 * 商铺用户登录到网站
	 * 
	 * @return 返回String，用于跳转
	 */
	public String login() {
		/*
		 * 接受登录信息，调用逻辑层UserService的login方法，返回用户对象
		 */
		try {
			shop = shopService.login(username, password);
			if (shop != null) {
				// 登录成功，将用户对象保存到session
				// 获取session
				Map session = ActionContext.getContext().getSession();
				// 将用户对象保存到session中
				session.put("shop", shop);
				return "toShopIndex";
			} else {
				// 登录失败，跳转到登录界面
				message = "用户名或密码错误！";
				return "login";
			}
		} catch (AppException e) {
			e.printStackTrace();
			// 系统异常，跳转到异常页面
			return "error";
		}
	}

	/**
	 * 商铺用户注册
	 * 
	 * @return 返回String，用于跳转
	 */
	public String register() {
		shop = new Shop();
		shop.setShopname(shopname);
		shop.setAddress(address);
		shop.setTel(telephone);
		shop.setComment(comment);
		shop.setUsername(username);
		shop.setPassword(password);
		shop.setGrade(0);
		shop.setDel(0);
		shop.setState(Constant.shop.uncheck);
		shop.setCity(s_city);
		shop.setProvince(s_province);
		shop.setLongitude(longitude);
		shop.setLatitude(latitude);
		shop.setMoney(0.0f);
		try {
			String path = this.uploadPicture();
			shop.setPicture(path);
			if (shopService.register(shop)) {
				// 1.注册成功
				return "tologin";
			} else {
				// 2.注册失败
				message = "用户名重复！";
				return "register";
			}

		} catch (AppException e) {
			e.printStackTrace();
			// 3.系统异常
			return "error";
		}
	}

	/**
	 * 展示商品用户的基本信息
	 * 
	 * @return 返回String，用于跳转
	 */
	public String shopDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		try {
			Shop shopById = shopService.getShopsById(id);
			setShop(shopById);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "shop";
	}

	/**
	 * 展示商铺用户的
	 * 
	 * @return 返回String，用于跳转
	 */
	public String clothesDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		try {
			clothes = shopService.getClothesById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;

		}
		return "shop";
	}

	public String searchClothes() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String searchText = request.getParameter("searchText");
		String shopId = request.getParameter("shopId");
		if (shopId == null) {
			return ERROR;
		}
		try {
			clist = shopService.finClothesBySearchAndShopId(searchText,
					Integer.parseInt(shopId));
		} catch (AppException e) {
			e.printStackTrace();
			return "error";
		}
		return "shop";
	}

	/**
	 * 用于商品和商铺等上传图片用的方法
	 * 
	 * @return 返回上传后的路径
	 */
	public String uploadPicture() throws AppException {
		String UPLOAD_FOLDER = "upload";
		// 原图与压缩图的保存路径
		String srcImagePath = new String("");

		// 获取服务器实际路径
		String serverRealPath = ServletActionContext.getServletContext()
				.getRealPath("");

		// 循环处理上传的图片

		String filename = picFileName;

		/* 生成图片文件上传的相对路径 */
		int index = filename.lastIndexOf(".");
		String extention = filename.substring(index + 1);// 获取后缀
		String uploadFileName = System.currentTimeMillis() + "";// 根据时间与编号生成图片名
		// 上传图片的文件相对路径
		srcImagePath = "\\" + UPLOAD_FOLDER + "\\" + uploadFileName + "."
				+ extention;
		// 压缩图片的文件相对路径

		/* 文件上传 */
		// 生成绝对路径
		String srcImgRealPath = serverRealPath + srcImagePath;// 原图的绝对路径

		// 上传原图和压缩图
		try {
			UploadProcess.uploadSrcImage(pic, srcImgRealPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* 保存图片路径 */
		// 将相对地址中的"\\"替换成"/",以符合网页上的显示路径
		srcImagePath = srcImagePath.replace("\\", "/");
		srcImagePath = srcImagePath.substring(1);

		// shop.setPicture(srcImagePath);
		return srcImagePath;

	}

	/**
	 * 获取所有店铺评论
	 * 
	 * @return
	 */
	public String shopComment() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String shopid = request.getParameter("id");
		try {
			ResultSet resultSet = shopService.shopComment(Integer
					.parseInt(shopid));
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(resultSet);
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "shop";
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<String> getAllComments() {
		return allComments;
	}

	public void setAllComments(List<String> allComments) {
		this.allComments = allComments;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Worker> getWorkerList() {
		return workerList;
	}

	public void setWorkerList(List<Worker> workerList) {
		this.workerList = workerList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderBusiShop> getObslist() {
		return obslist;
	}

	public void setObslist(List<OrderBusiShop> obslist) {
		this.obslist = obslist;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getClothesname() {
		return clothesname;
	}

	public void setClothesname(String clothesname) {
		this.clothesname = clothesname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getPriceDry() {
		return priceDry;
	}

	public void setPriceDry(double priceDry) {
		this.priceDry = priceDry;
	}

	public double getPriceWater() {
		return priceWater;
	}

	public void setPriceWater(double priceWater) {
		this.priceWater = priceWater;
	}

	public int getWashMode() {
		return washMode;
	}

	public void setWashMode(int washMode) {
		this.washMode = washMode;
	}

	public List<Clothes> getClist() {
		return clist;
	}

	public void setClist(List<Clothes> clist) {
		this.clist = clist;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getS_province() {
		return s_province;
	}

	public void setS_province(String s_province) {
		this.s_province = s_province;
	}

	public String getS_city() {
		return s_city;
	}

	public void setS_city(String s_city) {
		this.s_city = s_city;
	}

	public List<Statistics> getSlist() {
		return slist;
	}

	public void setSlist(List<Statistics> slist) {
		this.slist = slist;
	}

	public Clothes getClothes() {
		return clothes;
	}

	public void setClothes(Clothes clothes) {
		this.clothes = clothes;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
