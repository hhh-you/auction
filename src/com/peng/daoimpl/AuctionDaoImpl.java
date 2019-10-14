package com.peng.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.peng.dao.AuctionDao;
import com.peng.model.Auction;
import com.peng.util.JDBCUtil;

public class AuctionDaoImpl implements AuctionDao {

	@Override
	public List<Auction> auctionListByPage(BigDecimal pageIndex,
			BigDecimal pageNum) {
		List<Auction> auctionList = new ArrayList<Auction>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auction limit ?,?");
			preparedStatement.setBigDecimal(1, pageIndex);
			preparedStatement.setBigDecimal(2, pageNum);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AUCTIONNAME"));
				auction.setAuctionStartPrice(resultSet
						.getDouble("AUCTIONSTARTPRICE"));
				auction.setAutionUpSet(resultSet.getDouble("AUCTIONUPSET"));
				auction.setAuctionStartTime(resultSet
						.getTimestamp("AUCTIONSTARTTIME"));
				auction.setAuctionEndTime(resultSet
						.getTimestamp("AUCTIONENDTIME"));
				auction.setAuctionDESC(resultSet.getString("AUCTIONDESC"));
				auction.setAuctionPicpath(resultSet.getString("AUCTIONPICPATH"));
				auction.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				auctionList.add(auction);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return auctionList;
	}

	@Override
	public BigDecimal getTotalCount() {
		BigDecimal tocal = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select count(*) from auction ");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				tocal = resultSet.getBigDecimal(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		if (tocal != null) {
			return tocal;
		} else {
			return null;
		}

	}

	@Override
	public int auctionSave(Auction auction) {
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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return count;
	}

	@Override
	public Auction findAuctionByID(int auctionID) {
		Auction auction = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auction where auctionid=?");
			preparedStatement.setInt(1, auctionID);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AUCTIONNAME"));
				auction.setAuctionStartPrice(resultSet
						.getDouble("AUCTIONSTARTPRICE"));
				auction.setAutionUpSet(resultSet.getDouble("AUCTIONUPSET"));
				auction.setAuctionStartTime(resultSet
						.getTimestamp("AUCTIONSTARTTIME"));
				auction.setAuctionEndTime(resultSet
						.getTimestamp("AUCTIONENDTIME"));
				auction.setAuctionDESC(resultSet.getString("AUCTIONDESC"));
				auction.setAuctionPicpath(resultSet.getString("AUCTIONPICPATH"));
				auction.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}

		return auction;
	}

	@Override
	public int auctionUpdate(Auction auction) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {

			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement("update auction 	"
					+ "set auctionname=?, auctionstartprice=?,"
					+ "auctionupset=?,auctionstarttime=?,"
					+ "auctionendtime=?,auctiondesc=?,"
					+ "createtime=?,updatetime=? where auctionid=?");
			preparedStatement.setString(1, auction.getAuctionName());
			preparedStatement.setDouble(2, auction.getAuctionStartPrice());
			preparedStatement.setDouble(3, auction.getAutionUpSet());
			preparedStatement.setTimestamp(4, auction.getAuctionStartTime());
			preparedStatement.setTimestamp(5, auction.getAuctionEndTime());
			preparedStatement.setString(6, auction.getAuctionDESC());
			preparedStatement.setTimestamp(7, auction.getCreateTime());
			preparedStatement.setTimestamp(8, auction.getUpdateTime());
			preparedStatement.setInt(8, auction.getAuctionID());

			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return count;
	}

	@Override
	public int auctionDelByID(int auctionID) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("delete from auction where auctionid=?");
			preparedStatement.setInt(1, auctionID);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return count;
	}

	@Override
	public List<Auction> auctionSearch(String sql) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctionList = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AUCTIONNAME"));
				auction.setAuctionStartPrice(resultSet
						.getDouble("AUCTIONSTARTPRICE"));
				auction.setAutionUpSet(resultSet.getDouble("AUCTIONUPSET"));
				auction.setAuctionStartTime(resultSet
						.getTimestamp("AUCTIONSTARTTIME"));
				auction.setAuctionEndTime(resultSet
						.getTimestamp("AUCTIONENDTIME"));
				auction.setAuctionDESC(resultSet.getString("AUCTIONDESC"));
				auction.setAuctionPicpath(resultSet.getString("AUCTIONPICPATH"));
				auction.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				auctionList.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auctionList;
	}

	@Override
	public List<Auction> searchNotEndAuction() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctionList = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(" select * from auction where auctionendtime >= '"
							+ new Timestamp(System.currentTimeMillis()) + "' ");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AUCTIONNAME"));
				auction.setAuctionStartPrice(resultSet
						.getDouble("AUCTIONSTARTPRICE"));
				auction.setAutionUpSet(resultSet.getDouble("AUCTIONUPSET"));
				auction.setAuctionStartTime(resultSet
						.getTimestamp("AUCTIONSTARTTIME"));
				auction.setAuctionEndTime(resultSet
						.getTimestamp("AUCTIONENDTIME"));
				auction.setAuctionDESC(resultSet.getString("AUCTIONDESC"));
				auction.setAuctionPicpath(resultSet.getString("AUCTIONPICPATH"));
				auction.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				auctionList.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auctionList;
	}

	@Override
	public List<Auction> searchEndAuction() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctionList = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(" select * from auction where auctionendtime <= '"
							+ new Timestamp(System.currentTimeMillis()) + "' ");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AUCTIONNAME"));
				auction.setAuctionStartPrice(resultSet
						.getDouble("AUCTIONSTARTPRICE"));
				auction.setAutionUpSet(resultSet.getDouble("AUCTIONUPSET"));
				auction.setAuctionStartTime(resultSet
						.getTimestamp("AUCTIONSTARTTIME"));
				auction.setAuctionEndTime(resultSet
						.getTimestamp("AUCTIONENDTIME"));
				auction.setAuctionDESC(resultSet.getString("AUCTIONDESC"));
				auction.setAuctionPicpath(resultSet.getString("AUCTIONPICPATH"));
				auction.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				auctionList.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return auctionList;
	}

}
