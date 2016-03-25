package com.ruanku.util;

public class Constant {

	public static class shop
	{
		//未审核
		public final static int uncheck =1;
		//已通过
		public final static int pass =2;
		//已拒绝
		public final static int refuse =3;
	}
	
	public static class recharge
	{
		public final static int recharge = 1;
		public final static int fail = 3;
		public final static int drawback = 2;
	}
	
	public static class order
	{
		//未支付
		public final static int unpaid = 1;
		//待接单
		public final static int pendingOrders =2;
		//取件中
		public final static int pickup = 3;
		//清洗中
		public final static int washing = 4;
		//已送达		
		public final static int received = 5;
		//待评价
		public final static int paid = 11;
		//已评论
		public final static int commented = 12;
		//已取消
		public final static int cancel = -1;
		
	}
	
	public static class mail {
		//验证邮箱的系统ip和端口号
		public final static String url = "http://127.0.0.1:8080/";
		//整个工程的名字
		public final static String projName = "laundry";
		//系统邮箱和密码
		public final static String username="pamleft1994@163.com";
		public final static String password="kurage199402";
		public final static String mail163 ="smtp.163.com";
		
		//qq的smtp的端口是       SMTP服务器（端口465或587）
		public final static String mailqq = "smtp.qq.com";
		
	}

	public static class condb {
		public final static String url = "jdbc:mysql://127.0.0.1:3306/database_1?useUnicode=true&amp;"
				+ "characterEncoding=utf8";
		public final static String user = "root";
		public final static String password = "19940212";
	}
}
