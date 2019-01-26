package bank.controller;

import java.util.ArrayList;

import bank.model.service.BankService;
import bank.model.vo.Bank;
import bank.view.BankMenu;

public class BankController {
	BankService bservice = new BankService();
	public BankController() {}

	public void bankInsert(Bank bankInsert) {
		int result = bservice.insert(bankInsert);
		
		if(result > 0)
			System.out.println("\n계좌 정보 등록 성공!");
		else
			System.out.println("\n새 계좌 등록 실패!");
		
	}

	public ArrayList<Bank> selectAll() {
		ArrayList<Bank> bankList = bservice.selectList();
		if(bankList.size() == 0 || bankList == null) {
			System.out.println("\n계좌정보가 존재하지 않습니다.");
			new BankMenu().displayMenu();
		}
		return bankList;
	}

	public ArrayList<Bank> selectAccountNo(String inputAccountNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Bank> selectName(String inputUserName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAccount(Bank inputDeleteAccount) {
		// TODO Auto-generated method stub
		
	}

	public void insertDeposit(Bank bank) {
		// TODO Auto-generated method stub
		
	}

	public void insertWithdraw(Bank bank) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Bank> selectTransaction(String inputAccountNo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
