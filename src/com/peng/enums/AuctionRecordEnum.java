package com.peng.enums;

public enum AuctionRecordEnum {
	AUCTION_SUCCESS("auction_success", "竞拍成功"),
	AUCTION_FAIL("auction_fail","竞拍失败");
	private String value;
	private String desc;
	
	
	private AuctionRecordEnum(String value, String desc) {
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
