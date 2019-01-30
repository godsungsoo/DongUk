package bank.model.dao;

import java.sql.*;
import java.util.ArrayList;

import bank.model.vo.Bank;
import static common.JDBCTemplate.*;

public class BankDao {

	public int bankNewInsert(Connection conn, Bank bank) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		/*String query = "insert into bankmanager values (SEQ_NO.NEXTVAL,?,?,?);  "
				+ "insert into account values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT); "
				+ "insert into transaction values (SEQ_NO.CURRVAL,'02-'||SEQ_ACC.CURRVAL,DEFAULT,1,' ',?,DEFAULT,?)";*/
/*		String query = "insert into bankmanager values (SEQ_NO.NEXTVAL,?,?,?)";
		query = "insert into account values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT)";
*/		
		String query = "insert all into bankmanager values (SEQ_NO.NEXTVAL, ?, ?, ?) into account  values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT) into transaction values (SEQ_NO.CURRVAL,'02-'||SEQ_ACC.CURRVAL,DEFAULT,1,' ',?,DEFAULT,?) select * from dual";
		//String query = "insert into bankmanager values (SEQ_NO.NEXTVAL,?,?,?)";
		//String query2 = "insert into account values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT)";
		//String query3 = "insert into transaction values (SEQ_NO.CURRVAL,'02-'||SEQ_ACC.CURRVAL,DEFAULT,1,' ',?,DEFAULT,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getUserName());
			pstmt.setString(2, bank.getUserSsn());
			pstmt.setString(3, bank.getPhone());
			/*pstmt.addBatch();
			pstmt = conn.prepareStatement(query2);
			pstmt.addBatch();
			pstmt = conn.prepareCall(query3);	*/		
			pstmt.setInt(4, bank.getDeposit());
			pstmt.setInt(5, bank.getDeposit());
			//pstmt.addBatch();
			//pstmt.executeUpdate();
			//pstmt.executeBatch();
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int bankInsert(Connection conn, Bank bank) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert all into account  values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT) into transaction values ((select user_no from bankmanager where user_name = ? and user_ssn = ?), '02-'||SEQ_ACC.CURRVAL,DEFAULT,1,' ',?,DEFAULT,?) select * from dual";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getUserName());
			pstmt.setString(2, bank.getUserSsn());
			pstmt.setInt(3, bank.getDeposit());
			pstmt.setInt(4, bank.getDeposit());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Bank> selectAll(Connection conn) {
		ArrayList<Bank> bankList = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select user_no, user_name, account_no, balance, open_date, trans_date, phone "
				+ "		from bankmanager "
				+ "		join transaction using (user_no)"
				+ "		join account using (account_no)";
		
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
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);			
			close(stmt);
		}		
		return bankList;
	}

	public ArrayList<Bank> selectAccount(Connection conn, String inputAccountNo) {
		ArrayList<Bank> bankList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select user_no, account_no, trans_date, type_no, trans_content, deposit, withdraw, balance from transaction where account_no = ?";
		// userNo + ", "   + accountNo + ", " +  transDate + ", " + typeNo + ", "
		 //+ transContent + ", " + deposit + ", " + withdraw + ", " + balance;
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
		String query = "SELECT USER_NO, USER_NAME, ACCOUNT_NO, BALANCE, OPEN_DATE, TRANS_DATE, PHONE FROM BANKMANAGER JOIN TRANSACTION USING (USER_NO) JOIN ACCOUNT USING (ACCOUNT_NO) WHERE USER_NAME like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+inputUserName+"%");			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bankList;
	}

	public int updatePhone(Connection conn, Bank bank) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update bankmanager set phone = ? where user_name = ? and user_ssn = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getPhone());
			pstmt.setString(2, bank.getUserName());
			pstmt.setString(3, bank.getUserSsn());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteAccount(Connection conn, Bank bank) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete (select * from account "
								+ "where account_no = (select distinct account_no "
									+ "from transaction join bankmanager using(user_no) "
									+ "where user_name = ? and user_ssn = ? and account_no = ?))";
		//그냥 join해서 delete를 하면 account테이블에 남아있음. account테이블에서 해당 계좌를 지우면 on delete cascade이기 때문에
		//transaction에도 지워짐
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getUserName());
			pstmt.setString(2, bank.getUserSsn());
			pstmt.setString(3, bank.getAccountNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int insertDaposit(Connection conn, Bank bank) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into transaction values((select distinct user_no " + 
				"                                from transaction " + 
				"                                where account_no = ?),?,default,1,' ',?,0,?+(select balance " + 
				" from (select balance, trans_date, row_number() over(order by trans_date desc) as rank " + 
				"      from transaction " + 
				"      where account_no = ?) " + 
				"where rank = 1))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getAccountNo());
			pstmt.setString(2, bank.getAccountNo());
			pstmt.setInt(3, bank.getDeposit());
			pstmt.setInt(4, bank.getDeposit());
			pstmt.setString(5, bank.getAccountNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertWithdraw(Connection conn, Bank bank) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into transaction values((select distinct user_no " + 
				"                                from transaction " + 
				"                                where account_no = ?),?,default,2,' ',0,?,(select balance " + 
				" from (select balance, trans_date, row_number() over(order by trans_date desc) as rank " + 
				"      from transaction " + 
				"      where account_no = ?) " + 
				"where rank = 1) -? )";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bank.getAccountNo());
			pstmt.setString(2, bank.getAccountNo());
			pstmt.setInt(3, bank.getWithdraw());
			pstmt.setString(4, bank.getAccountNo());
			pstmt.setInt(5, bank.getWithdraw());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertTransaction(Connection conn, String accountNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		try {
			pstmt = conn.prepareStatement(query);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	

}
