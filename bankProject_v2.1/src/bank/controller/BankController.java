package bank.controller;

import java.util.ArrayList;

import bank.exception.BankException;
import bank.model.service.BankService;
import bank.model.vo.Bank;
import bank.view.BankMenu;

public class BankController {
	BankService bservice;
	public BankController() {
		try {
			bservice = new BankService();
		} catch (BankException e) {
			printError(e.getMessage());
		}
	}

	private void printError(String message) {
		System.out.println("\n오류발생!!");
		System.out.println("관리자에게 문의하십시오.");
		System.out.println("오류내용 : "+message);
		
	}

	public void bankNewInsert(Bank bank) {		
		try {
			if(bservice.bankNewInsert(bank) > 0)
				System.out.println("\n계좌 정보 등록 성공!");
		} catch (BankException e) {
			printError(e.getMessage());
		}	
		
	}
	
	public void bankInsert(Bank bank) {	
		try {
			if(bservice.bankInsert(bank) > 0)
				System.out.println("\n계좌 정보 등록 성공!");
		} catch (BankException e) {
			printError(e.getMessage());
		}		
	}

	public ArrayList<Bank> selectAll() {
		ArrayList<Bank> bankList = new ArrayList<>();
		try {
			bankList = bservice.selectAll();
		} catch (BankException e) {
			printError(e.getMessage());
		}
		
		return bankList;
	}

	public ArrayList<Bank> selectAccount(String inputAccountNo) {
		ArrayList<Bank> bankList = new ArrayList<>();;
		try {
			bankList = bservice.selectAccount(inputAccountNo);
		} catch (BankException e) {
			printError(e.getMessage());
		}		
		return bankList;
	}

	public ArrayList<Bank> selectName(String inputUserName) {
		ArrayList<Bank> bankList = = new ArrayList<>();;
		try {
			bankList = bservice.selectName(inputUserName);
		} catch (BankException e) {
			printError(e.getMessage());
		}		
		return bankList;
	}
	
	public void updatePhone(Bank bank) {
		try {
			if(bservice.updatePhone(bank) > 0)
				System.out.println("\n고객 전화번호 변경 성공");				
		} catch (BankException e) {
			printError(e.getMessage());
		}		
	}

	public void deleteAccount(Bank bank) {
		try {
			if(bservice.deleteAccount(bank) > 0)
				System.out.println("\n고객 계좌 삭제");
		} catch (BankException e) {
			printError(e.getMessage());
		}		
	}

	public void insertDeposit(Bank bank) {
		try {
			if(bservice.insertDeposit(bank) > 0)
				System.out.println("\n 입금 성공!");
		} catch (BankException e) {
			printError(e.getMessage());
		}
	}

	public void insertWithdraw(Bank bank) {
		try {
			if(bservice.insertWithdraw(bank) > 0)
				System.out.println("\n 출금 성공!");
		} catch (BankException e) {
			printError(e.getMessage());
		}
	}

	public void insertTransaction(Bank bank) {	
		try {
			if(bservice.insertTransaction(bank) > 0)
				System.out.println("\n 이체 성공!");
		} catch (BankException e) {
			printError(e.getMessage());
		}
	}
}
