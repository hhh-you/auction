package com.peng.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.peng.model.Auction;
import com.peng.util.JDBCUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionAddServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionAddServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SmartUpload smartUpload = new SmartUpload();
		// 初始化smartupload
		// 下面这句话执行完后 smartupload 就知道用户上传的
		// 文件和用户提交的参数
		try {
			smartUpload.initialize(getServletConfig(), request, response);

			smartUpload.setTotalMaxFileSize(1024 * 1024 * 10);

			smartUpload.upload();

			File userFile = smartUpload.getFiles().getFile(0);

			String fileEXT = userFile.getFileExt();

			String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
					.format(new Date()) + "." + fileEXT;
			// 如果存储文件 分别有两种操作
			// 1.存储在数据库中
			String serverpath = request.getServletContext().getRealPath(
					"upload");
			// 存储的操作
			userFile.saveAs(serverpath + java.io.File.separator + fileName);

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

	public static void main(String[] args) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = JDBCUtil.getConnection();

			preparedStatement = connection
					.prepareStatement("insert into auction(auctionname,auctionstartprice,"
							+ "auctionupset,auctionstarttime,auctionendtime,"
							+ "auctiondesc,auctionpicpath,createtime,updatetime)"
							+ " values(?,?,?,?,?,?,?,?,?)");
			Auction auction = new Auction();
			auction.setAuctionName("houxinjunjj");
			auction.setAuctionDESC("dabu");
			auction.setAuctionStartPrice(10d);
			auction.setAutionUpSet(10d);
			auction.setAuctionStartTime(new Timestamp(System
					.currentTimeMillis()));
			auction.setAuctionEndTime(new Timestamp(System
					.currentTimeMillis()));
			auction.setCreateTime(new Timestamp(System
					.currentTimeMillis()));
			auction.setUpdateTime(new Timestamp(System
					.currentTimeMillis()));
			preparedStatement.setString(1, auction.getAuctionName());
			preparedStatement.setDouble(2, auction.getAuctionStartPrice());
			preparedStatement.setDouble(3, auction.getAutionUpSet());
			preparedStatement.setTimestamp(4, auction.getAuctionStartTime());
			preparedStatement.setTimestamp(5, auction.getAuctionEndTime());
			preparedStatement.setString(6, auction.getAuctionDESC());
			preparedStatement.setString(7, auction.getAuctionPicpath());
			preparedStatement.setTimestamp(8, auction.getCreateTime());
			preparedStatement.setTimestamp(9, auction.getUpdateTime());
			
			count = preparedStatement.executeUpdate();
			System.out.println(count);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(preparedStatement, connection);
		}
	}
}
