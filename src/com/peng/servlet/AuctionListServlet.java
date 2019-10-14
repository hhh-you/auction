package com.peng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peng.enums.AuctionCRUDEnum;
import com.peng.enums.auctionLoginEnums;
import com.peng.model.Auction;
import com.peng.service.AuctionService;
import com.peng.serviceimpl.AuctionServiceImpl;
import com.peng.vo.AuctionPageVo;

public class AuctionListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionListServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// 获取页码
		String pageIndex = request.getParameter("pageIndex");
		// 获取每页展示的条数
		String pageNum = request.getParameter("pageNum") == null ? "5"
				: request.getParameter("pageNum");
		// 获取操作场景 比如 是登录进来 还是分页进来
		String msg = request.getParameter("msg");

		if (msg != null
				&& msg.equals(auctionLoginEnums.AUCTION_LOGIN_SUCCESS
						.getValue())) {
			pageIndex = "1";
		}

		AuctionService auctionService = new AuctionServiceImpl();
		BigDecimal totalCount = auctionService.getTotalCount();
		if (msg != null
				&& msg.equals(AuctionCRUDEnum.AUCTION_ADD_SUCCESS.getVaule())) {
			pageIndex = totalCount.divide(new BigDecimal(pageNum), 0,
					RoundingMode.UP).toString();
		}
		List<Auction> auctions = auctionService.auctionListByPage(
				new BigDecimal(pageIndex), new BigDecimal(pageNum), msg);

		AuctionPageVo<Auction> auctionPageVo = new AuctionPageVo<Auction>();
		auctionPageVo.setLists(auctions);
		auctionPageVo.setPageIndex(new BigDecimal(pageIndex));
		auctionPageVo.setPageNum(new BigDecimal(pageNum));
		auctionPageVo.setTotal(totalCount);
		auctionPageVo.setEndPage(totalCount.divide(new BigDecimal(pageNum), 0,
				RoundingMode.UP));

		request.setAttribute("auctionPageInfo", auctionPageVo);

		try {
			request.getRequestDispatcher(
					"auctionList.jsp?msg=" + msg + "&pageNum=" + pageNum
							+ "&pageIndex=" + auctionPageVo.getPageIndex() + "")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * // arraylist 底层是一个动态数组 它适用与迭代 List<Auction> auctionList = new
		 * ArrayList<Auction>(); Auction auction1 = new Auction();
		 * auction1.setAuctionName("破军"); auction1.setAuctionDESC("残血加攻击40%");
		 * auction1.setAuctionStartPrice(2300d); auction1.setAutionUpSet(2300d);
		 * auction1.setAuctionStartTime(new
		 * Timestamp(System.currentTimeMillis()));
		 * auction1.setAuctionEndTime(new
		 * Timestamp(System.currentTimeMillis()));
		 * 
		 * Auction auction2 = new Auction(); auction2.setAuctionName("饮血剑");
		 * auction2.setAuctionDESC("吸血40%");
		 * auction2.setAuctionStartPrice(2450d); auction2.setAutionUpSet(2450d);
		 * auction2.setAuctionStartTime(new
		 * Timestamp(System.currentTimeMillis()));
		 * auction2.setAuctionEndTime(new
		 * Timestamp(System.currentTimeMillis()));
		 * 
		 * auctionList.add(auction1); auctionList.add(auction2);
		 * 
		 * AuctionPageVo<Auction> auctionPageVo = new AuctionPageVo<Auction>();
		 * // 设置要渲染的数据集合 auctionPageVo.setLists(auctionList);
		 * auctionPageVo.setPageIndex(new BigDecimal(1));
		 * auctionPageVo.setPageNum(new BigDecimal(5));
		 * auctionPageVo.setTotal(new BigDecimal(5));
		 * auctionPageVo.setEndPage(new BigDecimal(1)); //
		 * 将auctionPageVo存放在请求报文中 为了在 request.setAttribute("auctionPageInfo",
		 * auctionPageVo);
		 * 
		 * try {
		 * request.getRequestDispatcher("auctionList.jsp").forward(request,
		 * response); } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
