package com.peng.model;

import java.io.Serializable;
import java.sql.Timestamp;

//这个类
public class AuctionUser implements Serializable {
	private int userID;
	private String userName;
	private String userPassword;
	private String userCardNo;
	private String userTel;
	private String userAddress;
	private String userPostNumber;
	private boolean userIsAdmin;
	private String userQuestion;
	private String userAnswer;
	private Timestamp createTime;
	private Timestamp updateTime;

	public AuctionUser() {
	}
	public AuctionUser(int userID) {
		this.userID = userID;
	}


	public AuctionUser(int userID, String userName, String userPassword,
			String userCardNo, String userTel, String userAddress,
			String userPostNumber, boolean userIsadmin, String userQuestion,
			String userAnswer, Timestamp createTime, Timestamp updateTime) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userCardNo = userCardNo;
		this.userTel = userTel;
		this.userAddress = userAddress;
		this.userPostNumber = userPostNumber;
		this.userQuestion = userQuestion;
		this.userAnswer = userAnswer;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserCardNo() {
		return userCardNo;
	}

	public void setUserCardNo(String userCardNo) {
		this.userCardNo = userCardNo;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPostNumber() {
		return userPostNumber;
	}

	public void setUserPostNumber(String userPostNumber) {
		this.userPostNumber = userPostNumber;
	}

	public boolean isUserIsAdmin() {
		return userIsAdmin;
	}

	public void setUserIsAdmin(boolean userIsAdmin) {
		this.userIsAdmin = userIsAdmin;
	}

	public String getUserQuestion() {
		return userQuestion;
	}

	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
