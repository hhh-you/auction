package com.peng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

import com.peng.enums.auctionLoginEnums;
import com.peng.service.AuctionUserService;
import com.peng.serviceimpl.AuctionUserServiceImpl;

public class AuctionUserLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionUserLoginServlet() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String urName = request.getParameter("username");
		// String pwd = request.getParameter("password");

		// response.getWriter().print("username" + urName + "password" + pwd);

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
		String userName = request.getParameter("username01");
		String userPassword = request.getParameter("userpassword01");
		String inputCode = request.getParameter("inputCode");
	
		String sysCode = (String) request.getSession().getAttribute("numrand");
		AuctionUserService auctionUserService = new AuctionUserServiceImpl();
		String result = auctionUserService.auctionUserLogin(userName,
				userPassword, inputCode, sysCode, request.getSession());
		try {
			if (result.equals(auctionLoginEnums.AUCTION_LOGIN_SUCCESS
					.getValue())) {
				request.getRequestDispatcher(
						"AuctionListServlet?msg=" + result + "").forward(
						request, response);
			} else {
				request.getRequestDispatcher("login.jsp?msg=" + result + "")
						.forward(request, response);
			}

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
