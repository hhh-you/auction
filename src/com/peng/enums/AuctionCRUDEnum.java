package com.peng.enums;

public enum AuctionCRUDEnum {
	AUCTION_ADD_SUCCESS("add_success","添加成功"),
	AUCTION_ADD_FAIL("add_fail","添加失败"),
	AUCTION_UPDATE_SUCCESS("update_success","更新成功"),
	AUCTION_UPDATE_FAIL("update_fail","更新失败"),
	AUCTION_DELETE_SUCCESS("dalete_success","删除成功"),
	AUCTION_DELETE_FAIL("delete_fail","删除失败");
	
	private String vaule;
	private String desc;
	
	
	private AuctionCRUDEnum(String vaule, String desc) {
		this.vaule = vaule;
		this.desc = desc;
	}
	public String getVaule() {
		return vaule;
	}
	public void setVaule(String vaule) {
		this.vaule = vaule;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
