package com.peng.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.peng.dao.AuctionUserDao;
import com.peng.model.Auction;
import com.peng.model.AuctionUser;
import com.peng.util.JDBCUtil;

public class AuctionUserDaoImpl implements AuctionUserDao {

	@Override
	public AuctionUser auctionUserLogin(String userName, String PassWord) {
		AuctionUser auctionUser = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// 接下来的代码具有不稳定性
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auctionuser "
							+ " where username=? and userpassword=?");
			// 设置占位符
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, PassWord);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				auctionUser = new AuctionUser();
				// 将auction进行赋值
				auctionUser.setUserID(resultSet.getInt("USERID"));
				auctionUser.setUserName(resultSet.getString("USERNAME"));
				auctionUser
						.setUserPassword(resultSet.getString("USERPASSWORD"));
				auctionUser.setUserCardNo(resultSet.getString("USERCARDNO"));
				auctionUser.setUserTel(resultSet.getString("USERTEL"));
				auctionUser.setUserAddress(resultSet.getString("USERADDRESS"));
				auctionUser.setUserPostNumber(resultSet
						.getString("USERPOSTNUMBER"));
				auctionUser.setUserIsAdmin(resultSet.getBoolean("USERISADMIN"));
				auctionUser
						.setUserQuestion(resultSet.getString("USERQUESTION"));
				auctionUser.setUserAnswer(resultSet.getString("USERISADMIN"));
				auctionUser.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auctionUser.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}

		return auctionUser;
	}

	

}
