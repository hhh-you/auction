package com.peng.enums;

public enum auctionLoginEnums {

	AUCTION_LOGIN_SUCCESS("login_success", "登陆成功"), 
	AUCTION_LOGIN_FAIL("login_fail", "用户名密码错误"),
	AUCTION_LOGIN_VALIDATA_CODE_ERROR("validateCodeError", "验证码错误");

	private String value;
	private String desc;

	private auctionLoginEnums(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
