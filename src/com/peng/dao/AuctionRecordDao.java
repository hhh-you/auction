package com.peng.dao;

import java.util.List;

import com.peng.model.AuctionRecord;

public interface AuctionRecordDao {
	List<AuctionRecord> findAuctionRecordListAuctionUserID(int userID);
	
	int AuctionRecordAddServlet(AuctionRecord auctionRecord);
}
