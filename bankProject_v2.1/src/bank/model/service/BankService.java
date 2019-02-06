package bank.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import bank.exception.BankException;
import bank.model.dao.BankDao;
import bank.model.vo.Bank;
import static common.JDBCTemplate.*;

public class BankService {
	private BankDao bdao;
	
	public BankService() throws BankException{
		bdao = new BankDao();
	};
	
	public int bankNewInsert(Bank bank) throws BankException {
		Connection conn = getConnection();
		int result = bdao.bankNewInsert(conn, bank);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}
		
	public int bankInsert(Bank bank) throws BankException {
		Connection conn = getConnection();
		int result = bdao.bankInsert(conn, bank);
		if(result > 0)
			commit(conn);		
		close(conn);
		return result;
	}

	public ArrayList<Bank> selectAll() throws BankException {
		Connection conn = getConnection();
		ArrayList<Bank> bankList = bdao.selectAll(conn);
		close(conn);
		return bankList;
	}

	public ArrayList<Bank> selectAccount(String inputAccountNo) throws BankException {
		Connection conn = getConnection();
		ArrayList<Bank> bankList = bdao.selectAccount(conn, inputAccountNo);
		close(conn);
		return bankList;
	}

	public ArrayList<Bank> selectName(Bank bank) throws BankException {
		Connection conn = getConnection();
		ArrayList<Bank> bankList = bdao.selectName(conn, bank);
		return bankList;
	}

	public int updatePhone(Bank bank) throws BankException {
		Connection conn = getConnection();
		int result = bdao.updatePhone(conn, bank);
		if(result > 0)
			commit(conn);		
		return result;
	}

	public int deleteAccount(Bank bank) throws BankException {
		Connection conn = getConnection();
		int result = bdao.deleteAccount(conn, bank);
		if(result > 0)
			commit(conn);		
		return result;
	}

	public int insertDeposit(Bank bank) throws BankException {
		Connection conn = getConnection();
		int result = bdao.insertDeposit(conn, bank);
		if(result > 0)
			commit(conn);		
		return result;
	}

	public int insertWithdraw(Bank bank) throws BankException {
		Connection conn = getConnection();
		int result = bdao.insertWithdraw(conn, bank);
		if(result > 0)
			commit(conn);		
		return result;
	}

	public int insertTransaction(Bank bank) throws BankException {
		Connection conn = getConnection();
		int result = bdao.insertTransaction(conn, bank);
		if(result > 0)
			commit(conn);		
		return result;
	}
}
