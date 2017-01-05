package com.high.food.libraries;

public class UserLevelEncode {

	private final static String common = "common";
	private final static String envester = "envester";
	private final static String loaner = "loaner";
	
	
	private static UserLevelEncode encode;
	public static UserLevelEncode getEncode(){
		if(encode == null) encode = new UserLevelEncode();
		return encode;
	}
	
	public String levelEncode(int level){
		
		if(level == 0) return common;
		else if (level == 1) return loaner;
		else return envester;
		
	}
	
}
