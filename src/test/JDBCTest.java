package test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.peng.model.AuctionUser;
import com.peng.util.JDBCUtil;

public class JDBCTest {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//
//			connection = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/qf", "root", "peng");
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auctionuser");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AuctionUser AuctionUser = new AuctionUser();
				AuctionUser.setUserID(resultSet.getInt("userid"));
				AuctionUser.setUserName(resultSet.getString("userName"));
				AuctionUser
						.setUserPassword(resultSet.getString("userpassword"));
				AuctionUser.setUserCardNo(resultSet.getString("userCardNo"));
				AuctionUser.setUserTel(resultSet.getString("userTel"));
				AuctionUser.setUserAddress(resultSet.getString("userAddress"));
				AuctionUser.setUserPostNumber(resultSet
						.getString("userPostNumber"));
//				AuctionUser.setUserIsadmin(resultSet.getBoolean("userIsadmin"));
				AuctionUser.setUserQuestion(resultSet
						.getString("userQuestion"));
				AuctionUser.setUserAnswer(resultSet.getString("userIsadmin"));
				AuctionUser.setCreateTime(resultSet.getTimestamp("createTime"));
				AuctionUser.setUpdateTime(resultSet.getTimestamp("updateTime"));

				System.out.println(AuctionUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}

	}

}
