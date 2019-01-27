package bank.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import bank.model.dao.BankDao;
import bank.model.vo.Bank;
import static common.JDBCTemplate.*;

public class BankService {
	private BankDao bdao = new BankDao();
	
	public BankService(){};
	
	public int insert(Bank bank) {
		Connection conn = getConnection();
		int result = bdao.insert(conn, bank);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Bank> selectList() {
		Connection conn = getConnection();
		ArrayList<Bank> bankList = bdao.selectList(conn);
		close(conn);
		return bankList;
	}

	public ArrayList<Bank> selectAccountNo(String inputAccountNo) {
		Connection conn = getConnection();
		ArrayList<Bank> bankList = bdao.selectAccountNo(conn, inputAccountNo);
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

}
