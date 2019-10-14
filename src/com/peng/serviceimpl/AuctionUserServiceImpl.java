package com.peng.serviceimpl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.peng.util.MD5;
import com.peng.dao.AuctionUserDao;
import com.peng.daoimpl.AuctionUserDaoImpl;
import com.peng.enums.auctionLoginEnums;
import com.peng.model.Auction;
import com.peng.model.AuctionUser;
import com.peng.service.AuctionUserService;
import com.peng.util.StringUtil;

public class AuctionUserServiceImpl implements AuctionUserService {

	@Override
	public String auctionUserLogin(String userName, String PassWord,
			String inputCode, String sysCode, HttpSession session) {
		//判断验证码和系统验证码 是否合法
		if (StringUtil.isEmpty(inputCode) || StringUtil.isEmpty(sysCode)) {
			return auctionLoginEnums.AUCTION_LOGIN_VALIDATA_CODE_ERROR
					.getValue();
		}
		//判断验证码和系统验证码是否匹对
		if (!inputCode.equals(sysCode)) {
			return auctionLoginEnums.AUCTION_LOGIN_VALIDATA_CODE_ERROR.getValue();
		}
		//判断用户和用户名密码是否合法
		if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(PassWord)) {
			return auctionLoginEnums.AUCTION_LOGIN_FAIL.getValue();
		}
		//判断用户名和密码是否在数据库中存在
		AuctionUserDao auctionUserDao = new AuctionUserDaoImpl();
		AuctionUser auctionUser = auctionUserDao.auctionUserLogin(userName,MD5.MD5(PassWord));
		//如果DAO层查不到 对应 DAO层返回一个NULL
		if (auctionUser == null) {
			return auctionLoginEnums.AUCTION_LOGIN_FAIL.getValue();
		}
		
		session.setAttribute("user", auctionUser);
		return auctionLoginEnums.AUCTION_LOGIN_SUCCESS.getValue();
	}

	

}
