package com.peng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peng.model.Auction;
import com.peng.model.AuctionRecord;
import com.peng.model.AuctionUser;
import com.peng.service.AuctionRecordService;
import com.peng.serviceimpl.AuctionRecordServiceImpl;

public class AuctionRecordAddServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionRecordAddServlet() {
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
		String auctionId = request.getParameter("auctionId");
		String auctionprice = request.getParameter("auctionPrice");
		String result = null;
		try {
			int userId = ((AuctionUser) request.getSession().getAttribute("user"))
					.getUserID();
			AuctionRecord auctionRecord = new AuctionRecord();
			auctionRecord.setAuctionPrice(Double.parseDouble(auctionprice));
			auctionRecord.setAuctionUser(new AuctionUser(userId));
			auctionRecord.setAuction(new Auction(Integer.parseInt(auctionId)));
			AuctionRecordService auctionRecordService = new AuctionRecordServiceImpl();
			result = auctionRecordService
					.AuctionRecordAddServlet(auctionRecord);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			request.getRequestDispatcher(
					"AuctionDetailServlet?auctionId=" + auctionId + "&msg="
							+ result + "").forward(request, response);
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
