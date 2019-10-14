package com.peng.service;

import java.util.List;

import com.peng.model.AuctionRecord;
import com.peng.servlet.AuctionRecordAddServlet;

public interface AuctionRecordService {
	List<AuctionRecord> findAuctionRecordListAuctionUserID(int userID);
	String AuctionRecordAddServlet(AuctionRecord auctionRecord);
}
