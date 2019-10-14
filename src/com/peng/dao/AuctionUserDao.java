package com.peng.dao;

import java.math.BigDecimal;
import java.util.List;

import com.peng.model.Auction;
import com.peng.model.AuctionUser;

public interface AuctionUserDao {
	AuctionUser auctionUserLogin(String userName, String PassWord);
	
	

	
}
