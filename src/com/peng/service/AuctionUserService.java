package com.peng.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.peng.model.Auction;
import com.peng.model.AuctionUser;

public interface AuctionUserService {
	// public abstract 不用写
	String auctionUserLogin(String userName, String PassWord, String inputCode,
			String sysCode, HttpSession session);

}
