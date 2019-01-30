package bank.model.vo;

import java.sql.*;

public class Bank implements java.io.Serializable{

	private static final long serialVersionUID = 7257863091568812260L;
	private int userNo;
	private String userName;
	private String userSsn;
	private String phone;
	private String accountNo;
	private Date openDate;
	private String transAccountNo;//상대방 계좌
	private int typeNo;
	private String typeName;
	private Date transDate;
	private String transContent;
	private int deposit;
	private int withdraw;
	private int balance;
	
	public Bank() {}

	public Bank(int userNo, String userName, String userSsn, String phone, String accountNo, Date openDate,
			String transAccountNo, int typeNo, String typeName, Date transDate, String transContent, int deposit,
			int withdraw, int balance) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.userSsn = userSsn;
		this.phone = phone;
		this.accountNo = accountNo;
		this.openDate = openDate;
		this.transAccountNo = transAccountNo;
		this.typeNo = typeNo;
		this.typeName = typeName;
		this.transDate = transDate;
		this.transContent = transContent;
		this.deposit = deposit;
		this.withdraw = withdraw;
		this.balance = balance;
	}	

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSsn() {
		return userSsn;
	}

	public void setUserSsn(String userSsn) {
		this.userSsn = userSsn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getTransAccountNo() {
		return transAccountNo;
	}

	public void setTransAccountNo(String transAccountNo) {
		this.transAccountNo = transAccountNo;
	}

	public int getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getTransContent() {
		return transContent;
	}

	public void setTransContent(String transContent) {
		this.transContent = transContent;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(int withdraw) {
		this.withdraw = withdraw;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return userNo + ", "   + accountNo + ", " +  transDate + ", " + typeNo + ", "
				 + transContent + ", " + deposit + ", " + withdraw + ", " + balance;
	}

}
