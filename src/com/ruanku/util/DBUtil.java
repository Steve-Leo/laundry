package com.ruanku.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	public static void main(String args[]){
		getConnection();
	}
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/database_1?useUnicode=true&amp;" + 
		"characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	private static String user = "root";
	private static String password = "root";
	
	/**
	 * 锟斤拷锟斤拷锟斤拷菘锟斤拷锟斤拷锟�
	 * @return
	 */
	public static Connection getConnection(){
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 锟截憋拷锟斤拷菘锟�
	 * @return
	 */
	public static void closeConnection(Connection conn){
		try {
			if(conn != null && !conn.isClosed()){
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 锟截憋拷指锟斤拷
	 * @param st
	 */
	public static void closeStatement(Statement st){
		try {
			if(st != null && !st.isClosed()){
				st.close();
				st = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 锟截闭斤拷锟�
	 * @param st
	 */
	public static void closeResultSet(ResultSet rs){
		try {
			if(rs != null && !rs.isClosed()){
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
