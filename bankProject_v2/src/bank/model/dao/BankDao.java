package bank.model.dao;

import java.sql.*;
import java.util.ArrayList;

import bank.model.vo.Bank;
import static common.JDBCTemplate.*;

public class BankDao {

	public int insert(Connection conn, Bank bank) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into bankmanager values (?,?,?,?);"
				+ "insert into account values (?,?);"
				+ "insert into transaction (?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Bank> selectList(Connection conn) {
		ArrayList<Bank> bankList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		//Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select user_no, user_name, account_no, balance, open_date, trans_date, phone from bankmanager join transaction using (user_no) join account using (account_no)";
		//String query  = "select * from bankmanager";
		try {
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, "USER_NO");
			//pstmt.setString(2, "USER_NAME");
			rset = pstmt.executeQuery();
			//stmt = conn.createStatement();
			//rset = stmt.executeQuery(query);
			System.out.println(rset.next());
			while(rset.next()) {
				Bank bank = new Bank();
				//고객번호 이름 계좌번호  잔액 통장개설일 최근거래일 핸드폰 번호
				bank.setUserNo(rset.getInt("USER_NO"));
				bank.setUserName(rset.getString("USER_NAME"));
				/*bank.setAccountNo(rset.getString("account_no"));
				bank.setBalance(rset.getInt("BALANCE"));
				bank.setOpenDate(rset.getDate("OPEN_DATE"));
				bank.setTransDate(rset.getDate("TRANS_DATE"));
				bank.setPhone(rset.getString("PHONE"));*/
				bankList.add(bank);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
			//close(stmt);
		}
		
		
		return bankList;
	}

	public ArrayList<Bank> selectAccountNo(Connection conn, String inputAccountNo) {
		ArrayList<Bank> bankList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//String query = "select user_no, user_name, account_no, balance, open_date, trans_date, phone from bankmanager join transaction using (user_no) join account using (account_no) where account_no = ?";
		String query = "select a.user_no from bankmanager a, transaction b where a.user_no = b.user_no";
		try {
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, inputAccountNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Bank bank = new Bank();
				bank.setUserNo(rset.getInt("USER_NO"));
				bankList.add(bank);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bankList;
	}

	public ArrayList<Bank> selectName(Connection conn, String inputUserName) {
		ArrayList<Bank> bankList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Bank bank = null;
		//String query = "select user_no, user_name, account_no, balance, open_date, trans_date, phone from bankmanager join transaction using (user_no) join account using (account_no) where user_name = ?";
		String query = "SELECT USER_NO, USER_NAME, ACCOUNT_NO, BALANCE, OPEN_DATE, TRANS_DATE, PHONE FROM BANKMANAGER JOIN TRANSACTION USING (USER_NO) JOIN ACCOUNT USING (ACCOUNT_NO) WHERE USER_NAME = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputUserName);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				bank = new Bank();
				bank.setUserNo(rset.getInt("user_no"));
				bankList.add(bank);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bankList;
	}

	public int updatePhone(Connection conn, Bank bank) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteAccount(Connection conn, Bank bank) {
		// TODO Auto-generated method stub
		return 0;
	}

}
