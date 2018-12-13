package com.example.demo.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
	// 进行加法运算
	public static double add(double d1, double d2) { 
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.add(b2).doubleValue();
	}

	// 进行加法运算
	public static double add(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}
	// 进行加法运算
	public static String strAdd(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return round2String(b1.add(b2).doubleValue(),2);
	}
	// 进行减法运算
	public static double sub(double d1, double d2) { 
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.subtract(b2).doubleValue();
	}
	// 进行乘法运算
	public static double mul(double d1, double d2) { 
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.multiply(b2).doubleValue();
	}

	// 进行乘法运算
	public static double mul(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2).doubleValue();
	}

	// 进行除法运算(注意除数不能为0或者NULL)
	public static double div(double d1, double d2, int len) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	// 进行四舍五入操作
	public static double round(double d, int len) { 
		BigDecimal b1 = new BigDecimal(Double.toString(d));
		BigDecimal b2 = new BigDecimal("1");
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	// 进行四舍五入操作重载方法
	public static double round(String d, int len) { 
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal("1");
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	// 进行四舍五入操作重载方法
	public static String round2String(double d, int len) {
		BigDecimal b1 = new BigDecimal(Double.toString(d));
		BigDecimal b2 = new BigDecimal("1");
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).toString();
	}

	public static String round2String(String d, int len) {
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal("1");
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).toString();
	}

	public static void main(String args[]){
		System.out.println("加法运算：" + BigDecimalUtils.add(0.1, 0.111));
		System.out.println("减法运算：" + BigDecimalUtils.sub(0.1, 0.111));
		System.out.println("乘法运算：" + BigDecimalUtils.mul(0.1, 0.111));
		System.out.println("除法运算：" + BigDecimalUtils.div(0.1, 0.111, 8));

		System.out.println(BigDecimalUtils.round2String(5d,2));
	}
	// 四组加法运算
	public static double addFour(double d1, double d2,double d3, double d4) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		BigDecimal b3 = new BigDecimal(Double.toString(d3));
		BigDecimal b4 = new BigDecimal(Double.toString(d4));
		BigDecimal temp1 = b1.add(b2);
		BigDecimal temp2 = b3.add(b4);
		return temp1.add(temp2).doubleValue();
	}

	// 四组加法运算
	public static double addEight(double d1, double d2,double d3, double d4,double d5, double d6,double d7, double d8) {
		BigDecimal b1 = new BigDecimal(Double.toString(addFour(d1,d2,d3,d4)));
		BigDecimal b2 = new BigDecimal(Double.toString(addFour(d5,d6,d7,d8)));
		return b1.add(b2).doubleValue();
	}
}
