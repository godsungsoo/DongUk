package bank.controller;

import java.util.ArrayList;

import bank.model.service.BankService;
import bank.model.vo.Bank;
import bank.view.BankMenu;

public class BankController {
	BankService bservice = new BankService();
	public BankController() {}

	public void bankNewInsert(Bank bank) {
		int result = bservice.bankNewInsert(bank);
		if(result > 0)
			System.out.println("\n계좌 정보 등록 성공!");
		else
			System.out.println("\n계좌 등록 실패!");
		
	}
	
	public void bankInsert(Bank bank) {
		int result = bservice.bankInsert(bank);
		
		if(result > 0)
			System.out.println("\n계좌 정보 등록 성공!");
		else
			System.out.println("\n새 계좌 등록 실패!");
		
	}

	public ArrayList<Bank> selectAll() {
		ArrayList<Bank> bankList = bservice.selectAll();
		if(bankList.size() == 0 || bankList == null) {
			System.out.println("\n계좌정보가 존재하지 않습니다.");
			new BankMenu().displayMenu();
		}
		return bankList;
	}

	public ArrayList<Bank> selectAccount(String inputAccountNo) {
		ArrayList<Bank> bankList = bservice.selectAccount(inputAccountNo);
		if(bankList.size() == 0 || bankList == null) {
			System.out.println("\n해당 계좌번호가 존재하지 않습니다.");
			new BankMenu().displayMenu();
		}
		return bankList;
	}

	public ArrayList<Bank> selectName(String inputUserName) {
		ArrayList<Bank> bankList = bservice.selectName(inputUserName);
		if(bankList.size() == 0 || bankList == null) {
			System.out.println("\n해당 고객의 정보는 존재하지 않습니다.");
			new BankMenu().displayMenu();
		}
		return bankList;
	}
	
	public void updatePhone(Bank bank) {
		int result = bservice.updatePhone(bank);
		if(result > 0) {
			System.out.println("\n고객 전화번호 변경 성공");
		}else {
			System.out.println("\n전화번호 변경 실패!");
		}
		return;
		
	}

	public void deleteAccount(Bank bank) {
		int result = bservice.deleteAccount(bank);
		if(result > 0)
			System.out.println("\n고객 계좌 삭제");
		else
			System.out.println("\n계좌 삭제 실패!");
		
	}

	public void insertDeposit(Bank bank) {
		int result = bservice.insertDeposit(bank);
		if(result > 0 )
			System.out.println("\n 입금 성공!");
		else
			System.out.println("\n 입금 실패");
		
	}

	public void insertWithdraw(Bank bank) {
		int result = bservice.insertWithdraw(bank);
		if(result > 0)
			System.out.println("\n 출금 성공!");
		else
			System.out.println("\n 출금 실패!");
		
	}

	public void insertTransaction(Bank bank) {
		
		int result = bservice.insertTransaction(bank);
		if(result > 0)
			System.out.println("\n 이체 성공!");
		else
			System.out.println("\n 이체 실패!");
	}
	
}
