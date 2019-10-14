package com.peng.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.peng.dao.AuctionRecordDao;
import com.peng.model.Auction;
import com.peng.model.AuctionRecord;
import com.peng.model.AuctionUser;
import com.peng.util.JDBCUtil;

public class AuctionRecordDaoImpl implements AuctionRecordDao {

	@Override
	public List<AuctionRecord> findAuctionRecordListAuctionUserID(int auctionID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<AuctionRecord> auctionRecordList = new ArrayList<AuctionRecord>();

		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auctionuser as at"
							+ " left join auctionrecord as atr "
							+ " on at.userid = atr.userid "
							+ " where atr.auctionid = ? order by atr.auctionprice desc  ");
			preparedStatement.setInt(1, auctionID);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AuctionRecord auctionRecord = new AuctionRecord();
				auctionRecord.setAuctionPrice(resultSet
						.getDouble("AUCTIONPRICE"));
				auctionRecord.setAuctionTime(resultSet
						.getTimestamp("AUCTIONTIME"));
				AuctionUser auctionUser = new AuctionUser();
				auctionUser.setUserName(resultSet.getString("USERNAME"));
				auctionRecord.setAuctionUser(auctionUser);
				auctionRecordList.add(auctionRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}

		return auctionRecordList;
	}

	@Override
	public int AuctionRecordAddServlet(AuctionRecord auctionRecord) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into auctionrecord "
							+ " (auctionid,userid,auctionprice,auctiontime) "
							+ " values(?,?,?,?)");
			preparedStatement.setInt(1, auctionRecord.getAuction()
					.getAuctionID());
			preparedStatement.setInt(2, auctionRecord.getAuctionUser()
					.getUserID());
			preparedStatement.setDouble(3, auctionRecord.getAuctionPrice());
			preparedStatement.setTimestamp(4,
					new Timestamp(System.currentTimeMillis()));
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}
}
