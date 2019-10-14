package com.peng.serviceimpl;

import java.util.List;

import com.peng.dao.AuctionRecordDao;
import com.peng.daoimpl.AuctionRecordDaoImpl;
import com.peng.enums.AuctionRecordEnum;
import com.peng.model.AuctionRecord;
import com.peng.service.AuctionRecordService;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionRecordServiceImpl implements AuctionRecordService {
	AuctionRecordDao auctionRecordDao = new AuctionRecordDaoImpl();
	@Override
	public List<AuctionRecord> findAuctionRecordListAuctionUserID(int userID) {
		// TODO Auto-generated method stub
		return auctionRecordDao.findAuctionRecordListAuctionUserID(userID);
	}
	@Override
	public String AuctionRecordAddServlet(AuctionRecord auctionRecord) {
		int executeCount = auctionRecordDao.AuctionRecordAddServlet(auctionRecord);
		if (executeCount > 0) {
			return AuctionRecordEnum.AUCTION_SUCCESS.getValue();
		}else {
			return AuctionRecordEnum.AUCTION_FAIL.getValue();
		}
		
	}

}
