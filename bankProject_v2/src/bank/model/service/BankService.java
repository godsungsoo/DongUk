package bank.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import bank.model.dao.BankDao;
import bank.model.vo.Bank;
import static common.JDBCTemplate.*;

public class BankService {
	private BankDao bdao = new BankDao();
	
	public BankService(){};
	
	public int bankNewInsert(Bank bank) {
		Connection conn = getConnection();
		int result = bdao.bankNewInsert(conn, bank);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
		
	public int bankInsert(Bank bank) {
		Connection conn = getConnection();
		int result = bdao.bankInsert(conn, bank);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Bank> selectAll() {
		Connection conn = getConnection();
		ArrayList<Bank> bankList = bdao.selectAll(conn);
		close(conn);
		return bankList;
	}

	public ArrayList<Bank> selectAccount(String inputAccountNo) {
		Connection conn = getConnection();
		ArrayList<Bank> bankList = bdao.selectAccount(conn, inputAccountNo);
		close(conn);
		return bankList;
	}

	public ArrayList<Bank> selectName(String inputUserName) {
		Connection conn = getConnection();
		ArrayList<Bank> bankList = bdao.selectName(conn, inputUserName);
		return bankList;
	}

	public int updatePhone(Bank bank) {
		Connection conn = getConnection();
		int result = bdao.updatePhone(conn, bank);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}

	public int deleteAccount(Bank bank) {
		Connection conn = getConnection();
		int result = bdao.deleteAccount(conn, bank);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}

	public int insertDeposit(Bank bank) {
		Connection conn = getConnection();
		int result = bdao.insertDaposit(conn, bank);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}

	public int insertWithdraw(Bank bank) {
		Connection conn = getConnection();
		int result = bdao.insertWithdraw(conn, bank);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}

	public int insertTransaction(String accountNo) {
		Connection conn = getConnection();
		int result = bdao.insertTransaction(conn, accountNo);
		close(conn);
		return result;
	}
}
