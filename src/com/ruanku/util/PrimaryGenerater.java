package com.ruanku.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrimaryGenerater {

	private static final String SERIAL_NUMBER = "XXXXXXXX"; // 流水号格式
	private static PrimaryGenerater primaryGenerater = null;
	private static String SERIAL = "XXXXXXXX00000001";
	private PrimaryGenerater() {
	}

	/**
	 * 取得PrimaryGenerater的单例实现
	 * 
	 * @return
	 */
	public static PrimaryGenerater getInstance() {
		if (primaryGenerater == null) {
			synchronized (PrimaryGenerater.class) {
				if (primaryGenerater == null) {
					primaryGenerater = new PrimaryGenerater();
				}
			}
		}
		return primaryGenerater;
	}

	/**
	 * 生成下一个编号
	 */
	/*public synchronized String generaterNextNumber(String sno) {
		String id = null;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		if (sno == null) {
			id = formatter.format(date) + "00000001";
		} else {
			int count = SERIAL_NUMBER.length();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < count; i++) {
				sb.append("0");
			}
			DecimalFormat df = new DecimalFormat("00000000");
			if(formatter.format(date).equals(sno.substring(0, 8)))
			{
			id = formatter.format(date)
					+ df.format(1 + Integer.parseInt(sno.substring(8, 16)));
			}
			else
			{
				id = formatter.format(date) + "00000001";
				//id = formatter.format(date)
				//		+ df.format(1 + Integer.parseInt(sno.substring(8, 16)));
			}
		}
		return id;
	}*/
	public synchronized String generaterNextNumber() {
		String id = null;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		if (SERIAL == null) {
			id = formatter.format(date) + "00000001";
		} else {
			int count = SERIAL_NUMBER.length();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < count; i++) {
				sb.append("0");
			}
			DecimalFormat df = new DecimalFormat("00000000");
			if(formatter.format(date).equals(SERIAL.substring(0, 8)))
			{
			id = formatter.format(date)
					+ df.format(1 + Integer.parseInt(SERIAL.substring(8, 16)));
			}
			else
			{
				id = formatter.format(date) + "00000001";
				//id = formatter.format(date)
				//		+ df.format(1 + Integer.parseInt(sno.substring(8, 16)));
			}
		}
		SERIAL = id;
		return id;
	}
}