package bank.model.dao;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import bank.exception.BankException;
import bank.model.vo.Bank;
import static common.JDBCTemplate.*;

public class BankDao {
	private Properties prop = new Properties();
	public BankDao() throws BankException {
		try {
			prop.load(new FileReader("properties/query.properties"));
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}
	}
	
	public int bankNewInsert(Connection conn, Bank bank) throws BankException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("newInsert");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getUserName());
			pstmt.setString(2, bank.getUserSsn());
			pstmt.setString(3, bank.getPhone());
			pstmt.setInt(4, bank.getDeposit());
			pstmt.setInt(5, bank.getDeposit());
			
			result = pstmt.executeUpdate();
			if(result <= 0) {
				rollback(conn);
				throw new BankException("\n통장 개설 실패!");
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		} finally {
			close(pstmt);
		}		
		return result;
	}
	
	public int bankInsert(Connection conn, Bank bank) throws BankException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("insert");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getUserName());
			pstmt.setString(2, bank.getUserSsn());
			pstmt.setInt(3, bank.getDeposit());
			pstmt.setInt(4, bank.getDeposit());
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new BankException("통장개설 실패!");
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Bank> selectAll(Connection conn) throws BankException {
		ArrayList<Bank> bankList = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAll");
		
		try {			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
				while(rset.next()) {				
				Bank bank = new Bank();
				//고객번호 이름 계좌번호  잔액 통장개설일 최근거래일 핸드폰 번호
				bank.setUserNo(rset.getInt("USER_NO"));
				bank.setUserName(rset.getString("USER_NAME"));
				bank.setAccountNo(rset.getString("account_no"));
				bank.setBalance(rset.getInt("BALANCE"));
				bank.setOpenDate(rset.getDate("OPEN_DATE"));
				bank.setTransDate(rset.getDate("TRANS_DATE"));
				bank.setPhone(rset.getString("PHONE"));
				bankList.add(bank);
			}
			if(bankList.size() == 0) {
				throw new BankException("조회된 정보가 없습니다.");
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}finally {
			close(rset);			
			close(stmt);
		}		
		return bankList;
	}

	public ArrayList<Bank> selectAccount(Connection conn, String inputAccountNo) throws BankException {
		ArrayList<Bank> bankList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAccount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputAccountNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Bank bank = new Bank();
				bank.setUserNo(rset.getInt("USER_NO"));				
				bank.setAccountNo(rset.getString("ACCOUNT_NO"));
				bank.setTransDate(rset.getDate("TRANS_DATE"));
				bank.setTypeNo(rset.getInt("type_no"));
				bank.setTransContent(rset.getString("trans_content"));
				bank.setDeposit(rset.getInt("deposit"));
				bank.setWithdraw(rset.getInt("withdraw"));
				bank.setBalance(rset.getInt("balance"));
				bankList.add(bank);
			}
			if(bankList.size() == 0)
				throw new BankException(inputAccountNo + " 계좌의 정보가 없습니다.");
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bankList;
	}

	public ArrayList<Bank> selectName(Connection conn, String userName) throws BankException {
		ArrayList<Bank> bankList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Bank bank = null;
		String query = prop.getProperty("selectName");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+userName+"%");			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				bank = new Bank();
				bank.setUserNo(rset.getInt("USER_NO"));
				bank.setUserName(rset.getString("USER_NAME"));
				bank.setAccountNo(rset.getString("account_no"));
				bank.setBalance(rset.getInt("BALANCE"));
				bank.setOpenDate(rset.getDate("OPEN_DATE"));
				bank.setTransDate(rset.getDate("TRANS_DATE"));
				bank.setPhone(rset.getString("PHONE"));
				bankList.add(bank);				
			}
			if(bankList.size() == 0)
				throw new BankException(userName + " 의 정보가 없습니다.");
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return bankList;
	}

	public int updatePhone(Connection conn, Bank bank) throws BankException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("update");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getPhone());
			pstmt.setString(2, bank.getUserName());
			pstmt.setString(3, bank.getUserSsn());
			result = pstmt.executeUpdate();
			if(result <= 0) {
				rollback(conn);
				throw new BankException("핸드폰 번호 변경 실패!");
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteAccount(Connection conn, Bank bank) throws BankException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("delete");
		//그냥 join해서 delete를 하면 account테이블에 남아있음. account테이블에서 해당 계좌를 지우면 on delete cascade이기 때문에
		//transaction에도 지워짐
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getUserName());
			pstmt.setString(2, bank.getUserSsn());
			pstmt.setString(3, bank.getAccountNo());
			
			result = pstmt.executeUpdate();		
			if(result <= 0) {
				rollback(conn);
				throw new BankException("삭제 실패!");
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertDeposit(Connection conn, Bank bank) throws BankException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertDeposit");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getAccountNo());
			pstmt.setString(2, bank.getAccountNo());
			pstmt.setInt(3, bank.getDeposit());
			pstmt.setInt(4, bank.getDeposit());
			pstmt.setString(5, bank.getAccountNo());
			result = pstmt.executeUpdate();
			if(result <= 0) {
				rollback(conn);
				throw new BankException("입금 실패!");
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertWithdraw(Connection conn, Bank bank) throws BankException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertWithdraw");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getAccountNo());
			pstmt.setString(2, bank.getAccountNo());
			pstmt.setInt(3, bank.getWithdraw());
			pstmt.setString(4, bank.getAccountNo());
			pstmt.setInt(5, bank.getWithdraw());
			result = pstmt.executeUpdate();
			if(result <= 0) {
				rollback(conn);
				throw new BankException("출금 실패!");
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertTransaction(Connection conn, Bank bank) throws BankException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertTransaction");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getAccountNo());
			pstmt.setString(2, bank.getAccountNo());
			pstmt.setString(3, bank.getTransAccountNo());
			pstmt.setInt(4, bank.getWithdraw());
			pstmt.setString(5, bank.getAccountNo());
			pstmt.setInt(6, bank.getWithdraw());
			pstmt.setString(7, bank.getTransAccountNo());
			pstmt.setString(8, bank.getTransAccountNo());
			pstmt.setString(9, bank.getAccountNo());
			pstmt.setInt(10, bank.getWithdraw());
			pstmt.setInt(11, bank.getWithdraw());
			pstmt.setString(12, bank.getTransAccountNo());
			result = pstmt.executeUpdate();
			if(result <= 0) {
				rollback(conn);
				throw new BankException("계좌이체 실패!");
			}
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	

}
