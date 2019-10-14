package com.peng.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuctionRecord implements Serializable{
	
	private int id;
	//ORM思想范畴内
	//
	private AuctionUser auctionUser;
	private Auction auction;
	private Timestamp auctionTime;
	private double auctionPrice;
	
	
	public AuctionRecord(int id, AuctionUser auctionUser, Auction auction,
			Timestamp auctionTime, double auctionPrice) {
		super();
		this.id = id;
		this.auctionUser = auctionUser;
		this.auction = auction;
		this.auctionTime = auctionTime;
		this.auctionPrice = auctionPrice;
	}
	
	
	public AuctionRecord() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "AuctionRecord [id=" + id + ", auctionUser=" + auctionUser
				+ ", auction=" + auction + ", auctionTime=" + auctionTime
				+ ", auctionPrice=" + auctionPrice + "]";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AuctionUser getAuctionUser() {
		return auctionUser;
	}
	public void setAuctionUser(AuctionUser auctionUser) {
		this.auctionUser = auctionUser;
	}
	public Auction getAuction() {
		return auction;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	public Timestamp getAuctionTime() {
		return auctionTime;
	}
	public void setAuctionTime(Timestamp auctionTime) {
		this.auctionTime = auctionTime;
	}
	public double getAuctionPrice() {
		return auctionPrice;
	}
	public void setAuctionPrice(double auctionPrice) {
		this.auctionPrice = auctionPrice;
	}
	
	
	
}
