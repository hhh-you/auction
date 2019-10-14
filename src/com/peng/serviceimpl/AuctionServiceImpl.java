package com.peng.serviceimpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.peng.dao.AuctionDao;
import com.peng.daoimpl.AuctionDaoImpl;
import com.peng.enums.AuctionCRUDEnum;
import com.peng.model.Auction;
import com.peng.service.AuctionService;
import com.peng.util.StringUtil;

public class AuctionServiceImpl implements AuctionService {
	AuctionDao auctionDao = new AuctionDaoImpl();

	@Override
	public List<Auction> auctionListByPage(BigDecimal pageIndex,
			BigDecimal pageNum, String msg) {
		// mysql分页
		// limit 1(代表第几条开始)，10(代表页数)
		// limit 5，10
		// 只要是分页PageIndex(页码一定减1)
		return auctionDao.auctionListByPage(
				pageIndex.subtract(new BigDecimal("1")).multiply(pageNum),
				pageNum);
	}

	@Override
	public BigDecimal getTotalCount() {
		return auctionDao.getTotalCount();
	}

	@Override
	public String auctionSave(HttpServletRequest request,
			HttpServletResponse response, ServletConfig config) {

		String result = AuctionCRUDEnum.AUCTION_ADD_FAIL.getVaule();
		// 实力化 smartupload相关的操作
		SmartUpload smartUpload = new SmartUpload();
		try {
			smartUpload.initialize(config, request, response);
			smartUpload.setTotalMaxFileSize(1024 * 1024 * 10);
			smartUpload.upload();
			File file = smartUpload.getFiles().getFile(0);

			// 实例化 拍卖品
			Auction auction = new Auction();
			// 调用dao层 执行添加的操作
			String auctionName = smartUpload.getRequest().getParameter(
					"auctionName");
			String startPrice = smartUpload.getRequest().getParameter(
					"startPrice");
			String upset = smartUpload.getRequest().getParameter("upset");
			String startTime = smartUpload.getRequest().getParameter(
					"startTime");
			String endTime = smartUpload.getRequest().getParameter("endTime");
			String desc = smartUpload.getRequest().getParameter("desc");

			if (StringUtil.notEmpty(auctionName)) {
				auction.setAuctionName(auctionName);
			}
			if (StringUtil.notEmpty(startPrice)) {
				auction.setAuctionStartPrice(Double.valueOf(startPrice));
			}
			if (StringUtil.notEmpty(upset)) {
				auction.setAutionUpSet(Double.valueOf(upset));
			}
			if (StringUtil.notEmpty(startTime)) {
				auction.setAuctionStartTime(Timestamp.valueOf(startTime));
			}
			if (StringUtil.notEmpty(endTime)) {
				auction.setAuctionEndTime(Timestamp.valueOf(endTime));
			}
			if (StringUtil.notEmpty(desc)) {
				auction.setAuctionDESC(desc);
			}

			// 判断一下 有没有添加成功 并将添加状态 返回
			if (file.getSize() > 0) {
				// 如果进入到这里说明 用户有上传
				String fileEXT = file.getFileExt();
				String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
						.format(new Date()) + "." + fileEXT;
				String serverpath = request.getServletContext().getRealPath(
						"upload");
				auction.setAuctionPicpath(fileName);
				int executeCount = auctionDao.auctionSave(auction);
				if (executeCount > 0) {
					file.saveAs(serverpath + java.io.File.separator + fileName);
					result = AuctionCRUDEnum.AUCTION_ADD_SUCCESS.getVaule();
				}
			} else {
				// 用户没有上传
				int executeCount = auctionDao.auctionSave(auction);
				if (executeCount > 0) {
					result = AuctionCRUDEnum.AUCTION_ADD_SUCCESS.getVaule();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Auction findAuctionByID(int auctionID) {
		return auctionDao.findAuctionByID(auctionID);
	}

	@Override
	public String auctionUpdate(HttpServletRequest request,
			HttpServletResponse response, ServletConfig config) {
		String result = AuctionCRUDEnum.AUCTION_UPDATE_FAIL.getVaule();
		SmartUpload smartUpload = new SmartUpload();
		Auction auction = new Auction();
		try {
			smartUpload.initialize(config, request, response);
			smartUpload.setTotalMaxFileSize(1024 * 1024 * 10);
			smartUpload.upload();
			File file = smartUpload.getFiles().getFile(0);
			String auctionID = smartUpload.getRequest().getParameter(
					"auctionId");
			String auctionName = smartUpload.getRequest().getParameter(
					"auctionName");
			String startPrice = smartUpload.getRequest().getParameter(
					"startPrice");
			String upset = smartUpload.getRequest().getParameter("upset");
			String startTime = smartUpload.getRequest().getParameter(
					"startTime");
			String endTime = smartUpload.getRequest().getParameter("endTime");
			String desc = smartUpload.getRequest().getParameter("desc");

			if (StringUtil.notEmpty(auctionName)) {
				auction.setAuctionName(auctionName);
			}
			if (StringUtil.notEmpty(startPrice)) {
				auction.setAuctionStartPrice(Double.valueOf(startPrice));
			}
			if (StringUtil.notEmpty(upset)) {
				auction.setAutionUpSet(Double.valueOf(upset));
			}
			if (StringUtil.notEmpty(startTime)) {
				auction.setAuctionStartTime(Timestamp.valueOf(startTime));
			}
			if (StringUtil.notEmpty(endTime)) {
				auction.setAuctionEndTime(Timestamp.valueOf(endTime));
			}
			if (StringUtil.notEmpty(desc)) {
				auction.setAuctionDESC(desc);
			}
			// 判断是否添加成功
			if (file.getSize() > 0) {
				// 如果进入到这里说明 用户有上传
				String fileEXT = file.getFileExt();
				String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
						.format(new Date()) + "." + fileEXT;
				String serverpath = request.getServletContext().getRealPath(
						"upload");
				String userPic = smartUpload.getRequest().getParameter("pic02");
				auction.setAuctionPicpath(fileName);
				int executeCount = auctionDao.auctionUpdate(auction);
				if (executeCount > 0) {
					java.io.File file2 = new java.io.File(serverpath
							+ java.io.File.separator + userPic);
					file2.delete();
					file.saveAs(serverpath + java.io.File.separator + fileName);
					result = AuctionCRUDEnum.AUCTION_ADD_SUCCESS.getVaule();
				}
			} else {
				// 用户没有上传
				String userPic = smartUpload.getRequest().getParameter("pic02");
				auction.setAuctionPicpath(userPic);
				int executeCount = auctionDao.auctionUpdate(auction);
				if (executeCount > 0) {
					result = AuctionCRUDEnum.AUCTION_ADD_SUCCESS.getVaule();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String auctionDelByID(int auctionID, String serverPath) {
		Auction auction = auctionDao.findAuctionByID(auctionID);
		int executeCount = auctionDao.auctionDelByID(auctionID);
		if (executeCount > 0) {
			// 删除一般有两种 真删 假删
			// 假删 方便数据的找回
			// 真删 数据找回麻烦
			java.io.File file = new java.io.File(serverPath
					+ java.io.File.separator + auction.getAuctionPicpath());
			file.delete();
			return AuctionCRUDEnum.AUCTION_DELETE_SUCCESS.getVaule();
		}
		return AuctionCRUDEnum.AUCTION_DELETE_FAIL.getVaule();
	}

	@Override
	public List<Auction> auctionSearch(String auctionName, String startTime,
			String endTime, String startPrice) {
		// 动态查询
		// 初始化一个sql 条件恒等成立的sql语句
		// 在线程安全的情况下使用StringBuilder
		StringBuilder sql = new StringBuilder(
				"select * from auction where 1=1   ");
		// 根据用户的传参情况来拼接sql语句
		if (StringUtil.notEmpty(auctionName)) {
			// like 指的是模糊查询
			// 1.不能忘记写单引号
			// 后面的百分号的是后模糊
			// 前模糊会引起全表查询
			sql.append(" and auctionname like'" + auctionName + "%'");
		}
		if (StringUtil.notEmpty(startTime)) {
			sql.append(" and auctionstarttime >= '"
					+ Timestamp.valueOf(startTime) + "' ");
		}
		if (StringUtil.notEmpty(endTime)) {
			sql.append(" and auctionendtime <=  '" + Timestamp.valueOf(endTime)
					+ "' ");
		}
		if (StringUtil.notEmpty(startPrice)) {
			sql.append(" and auctionstartprice >=  '" + startPrice + "' ");
		}
		// 最后再将sql语句传入dao层 返回拍卖品结果集
		return auctionDao.auctionSearch(sql.toString());
	}

	@Override
	public List<Auction> searchNotEndAuction() {
		
		return auctionDao.searchNotEndAuction();
	}

	@Override
	public List<Auction> searchEndAuction() {
		return auctionDao.searchEndAuction();
	}
}
