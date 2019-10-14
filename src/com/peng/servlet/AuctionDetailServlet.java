package com.peng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peng.model.Auction;
import com.peng.model.AuctionRecord;
import com.peng.model.AuctionUser;
import com.peng.service.AuctionRecordService;
import com.peng.service.AuctionService;
import com.peng.serviceimpl.AuctionRecordServiceImpl;
import com.peng.serviceimpl.AuctionServiceImpl;

public class AuctionDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionDetailServlet() {
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
		// 先获取到 拍卖品ID
		String auctionID = request.getParameter("auctionId");
		// 根据拍卖品id 查找相应的拍卖品
		AuctionService auctionService = new AuctionServiceImpl();
		Auction auction = auctionService.findAuctionByID(Integer
				.parseInt(auctionID));
		
		AuctionRecordService auctionRecordService = new AuctionRecordServiceImpl();
		List<AuctionRecord> auctionRecordList = auctionRecordService
				.findAuctionRecordListAuctionUserID(auction.getAuctionID());
		// 分别将 拍卖品和拍卖品LIST（集合） 存入请求报文中
		request.setAttribute("AuctionObj", auction);
		request.setAttribute("record_list", auctionRecordList);
		// 最后 转发到 auctionDetail.jsp
		try {
			request.getRequestDispatcher("auctionDetail.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
