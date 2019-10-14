package com.peng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peng.model.Auction;
import com.peng.service.AuctionService;
import com.peng.serviceimpl.AuctionServiceImpl;
import com.peng.vo.AuctionPageVo;

public class AuctionSearchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionSearchServlet() {
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
		try {
			request.setCharacterEncoding("utf-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String auctionName = request.getParameter("auctionName");
		String auctionStartTime = request.getParameter("auctionStartTime");
		String auctionEndTime = request.getParameter("auctionEndTime");
		String auctionStartPrice = request.getParameter("auctionStartPrice");
		AuctionService auctionService = new AuctionServiceImpl();
		try {
			List<Auction> auctionList = auctionService.auctionSearch(auctionName, auctionStartTime,
					auctionEndTime, auctionStartPrice);
			AuctionPageVo<Auction> auctionPageVo = new AuctionPageVo<Auction>();
			auctionPageVo.setLists(auctionList);
			auctionPageVo.setPageIndex(new BigDecimal(1));
			auctionPageVo.setPageNum(new BigDecimal(666));
			auctionPageVo.setTotal(new BigDecimal(666));
			auctionPageVo.setEndPage(new BigDecimal(1));
			request.setAttribute("auctionPageInfo", auctionPageVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			request.getRequestDispatcher("auctionList.jsp").forward(request, response);
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
