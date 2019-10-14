package com.peng.util;

public class StringUtil {
	// 工具类的所有函数都是静态的
	// 判断null 是为了防止用户没传对应的参数到服务器
	// 判断“”（空字符串）是为了防止用户 传的参数是空
	public static boolean isEmpty(String args) {

		return args == null || args.equals("");
	}

	public static boolean notEmpty(String args) {
		return args != null && !args.equals("");
	}
	
	
}
