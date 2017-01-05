package com.high.food.models.response;

import com.high.food.models.entity.User;

public class LoginResponse {

	public final static String FAIL = "fail";
	public final static String SUCCESS = "success";
	public final static String SUCCESS_PASSWORD = "success_password";//로그인 패스워드 변경요청
	public final static String HALF = "half";//메일인증 안된 계정
	public final static String NULL_MSG = "email or password is null";
	
	private String result;
	private String reason;
	private String tokenKey;
	private User user;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTokenKey() {
		return tokenKey;
	}
	public void setTokenKey(String token) {
		this.tokenKey = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
