package com.peng.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.peng.model.Auction;
import com.peng.model.AuctionUser;
import com.peng.servlet.AuctionSearchServlet;

public interface AuctionService {

	List<Auction> auctionListByPage(BigDecimal pageIndex, BigDecimal pageNum,
			String msg);

	BigDecimal getTotalCount();

	String auctionSave(HttpServletRequest request,
			HttpServletResponse response, ServletConfig config);
	
	Auction findAuctionByID(int auctionID);
	
	String auctionUpdate(HttpServletRequest request,
			HttpServletResponse response, ServletConfig config);

	String auctionDelByID(int auctionID,String serverPath);
	
	List<Auction> auctionSearch(String auctionName, String startTime, String endTime, String startPrice);
	
	List<Auction> searchNotEndAuction();
	
	List<Auction> searchEndAuction();
	
}
