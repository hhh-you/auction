package com.peng.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Auction implements Serializable {
	private int auctionID;
	private String auctionName;
	private double auctionStartPrice;
	private double autionUpSet;
	private Timestamp auctionStartTime;
	private Timestamp auctionEndTime;
	private String auctionDESC;
	private String auctionPicpath;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	public Auction() {
	}
	public Auction(int auctionID) {
		this.auctionID = auctionID;
	}

	@Override
	public String toString() {
		return "Auction [auctionID=" + auctionID + ", auctionName="
				+ auctionName + ", auctionStartPrice=" + auctionStartPrice
				+ ", autionUpSet=" + autionUpSet + ", auctionStartTime="
				+ auctionStartTime + ", auctionEndTime=" + auctionEndTime
				+ ", auctionDESC=" + auctionDESC + ", auctionPicpath="
				+ auctionPicpath + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	
	


	public int getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	public double getAutionUpSet() {
		return autionUpSet;
	}

	public void setAutionUpSet(double autionUpSet) {
		this.autionUpSet = autionUpSet;
	}

	public Timestamp getAuctionStartTime() {
		return auctionStartTime;
	}

	public void setAuctionStartTime(Timestamp auctionStartTime) {
		this.auctionStartTime = auctionStartTime;
	}

	public Timestamp getAuctionEndTime() {
		return auctionEndTime;
	}

	public void setAuctionEndTime(Timestamp auctionEndTime) {
		this.auctionEndTime = auctionEndTime;
	}

	public String getAuctionDESC() {
		return auctionDESC;
	}

	public void setAuctionDESC(String auctionDESC) {
		this.auctionDESC = auctionDESC;
	}

	public String getAuctionPicpath() {
		return auctionPicpath;
	}

	public void setAuctionPicpath(String auctionPicpath) {
		this.auctionPicpath = auctionPicpath;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getAuctionName() {
		return auctionName;
	}

	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}

	public double getAuctionStartPrice() {
		return auctionStartPrice;
	}

	public void setAuctionStartPrice(double auctionStartPrice) {
		this.auctionStartPrice = auctionStartPrice;
	}

}
