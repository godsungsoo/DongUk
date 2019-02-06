package burger.view;

import java.util.ArrayList;
import java.util.Scanner;

import burger.mvc.vo.Burger;

public class BurgerMenu {
	private Scanner sc = new Scanner(System.in);
	public BurgerMenu() {}
	
	public void displayMenu() {
		do {
			System.out.print("1. 주문 입력 -> 주문하시겠습니까?[Y/N]    -> 재고 자동 UPDATE \r\n" + 
					"2. 주문서 확인 -> 폰번호 입력 받아서 확인\r\n" + 
					"3. 재고 확인\n"
					+ "4. 프로그램 종료\n"
					+ "메뉴 입력 : ");
			switch(sc.nextInt()) {
			case 1: inputMenu(); break;
			case 2: printMenu(); break;
			case 3: displayStock(); break;
			case 4: return;
			default : System.out.println("잘못 입력하셨습니다. 다시 입력하십시오.");
			}
			
		}while(true);
	}
	public ArrayList<Burger> inputMenu() {
		Burger burger = new Burger();
		System.out.print("메뉴이름 선택 : ");
		ArrayList<Burger> burgerList = new ArrayList<>();
		while(true) {
			System.out.println("버거 선택");
			System.out.println("1.치즈버거");
			System.out.println("2.불고기버거");
			System.out.println("3.치킨버거");
			System.out.println("4. 콜라");
			System.out.println("5. 사이다");
			System.out.println("6. 후렌치후라이");
			System.out.println("0. 결제진행/나가기");
			System.out.println("번호 입력 : ");
			int menuNo=sc.nextInt();
			int orderQty=0;
			switch(menuNo) {
			case 1: menuNo = 1; 
					System.out.print("수량입력 : ");
					orderQty = sc.nextInt();
					if(orderQty != 0)
						burger.setOrderQty(orderQty);
					burgerList.add(burger);
					break;
			case 2:  menuNo = 1; 
					 System.out.print("수량입력 : ");
					 orderQty = sc.nextInt();
					 if(orderQty != 0)
						 burger.setOrderQty(orderQty);
					 burgerList.add(burger);
					 break;
			case 3:  menuNo = 1; 
					 System.out.print("수량입력 : ");
					 orderQty = sc.nextInt();
					 if(orderQty != 0)
						 burger.setOrderQty(orderQty);
					 burgerList.add(burger);
					 break;
			case 4:  menuNo = 1; 
					 System.out.print("수량입력 : ");
					 orderQty = sc.nextInt();
					 if(orderQty != 0)
						 burger.setOrderQty(orderQty);
					 burgerList.add(burger);
					 break;
			case 5:  menuNo = 1; 
					 System.out.print("수량입력 : ");
					 orderQty = sc.nextInt();
					 if(orderQty != 0)
						 burger.setOrderQty(orderQty);
					 burgerList.add(burger);
					 break;
			case 6:  menuNo = 1; 
					 System.out.print("수량입력 : ");
					 orderQty = sc.nextInt();
					 if(orderQty != 0)
						 burger.setOrderQty(orderQty);
					 burgerList.add(burger);
					 break;
			case 0: System.out.print("현재 주문 리스트입니다. 이대로 주문하시겠습니까?[y/n]");
					if(sc.next().toUpperCase().charAt(0)=='Y')
						return burgerList;
					else break;
			default : System.out.println("없는 메뉴입니다. 다시 입력해주세요.");
			}
		}
	}
	public void printMenu(ArrayList<Burger> burgerList) {
		for(Burger b: burgerList) {
			System.out.println(b);
		}
			
	}
	public void displayStock(ArrayList<Burger> burgerList){
		for(Burger b: burgerList) {
			System.out.println(b);
		}
	}
	public void printError(String message) {
		System.out.println("\n오류발생!!");
		System.out.println("관리자에게 문의하십시오.");
		System.out.println("오류내용 : "+message);
	}
}
