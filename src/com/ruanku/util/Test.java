package com.ruanku.util;

public class Test {
	public static void main(String []args)
	{
		PrimaryGenerater pg = PrimaryGenerater.getInstance();
		String b = "2016022600000001";
		String a = pg.generaterNextNumber(b);
		
		System.out.println(a);
	}
}

