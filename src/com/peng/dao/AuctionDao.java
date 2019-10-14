package com.peng.dao;

import java.math.BigDecimal;
import java.util.List;

import com.peng.model.Auction;
import com.peng.model.AuctionUser;

public interface AuctionDao {

	List<Auction> auctionListByPage(BigDecimal pageIndex, BigDecimal pageNum);

	BigDecimal getTotalCount();
	
	int auctionSave(Auction auction);
	
	Auction findAuctionByID(int auctionID);
	
	int auctionUpdate(Auction auction);
	
	int auctionDelByID(int auctionID);
	
	List<Auction> auctionSearch(String sql);
	
	List<Auction> searchNotEndAuction();
	
	List<Auction> searchEndAuction();
}
