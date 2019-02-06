package burger.mvc.controller;

import java.util.ArrayList;

import burger.mvc.service.BurgerService;
import burger.mvc.vo.Burger;
import burger.view.BurgerMenu;

public class BurgerController {
	BurgerService bservice;
	
	public BurgerController() {
		try {
			bservice = new BurgerService();
		} catch (Exception e) {
			new BurgerMenu().printError(e.getMessage());
		}
	}
	
	public void burgerInsert(ArrayList<Burger> blist) {
		try {
			if(bservice.burgerInsert(blist) > 0)
				System.out.println("주문 성공!");
		} catch (Exception e) {
			new BurgerMenu().printError(e.getMessage());
		}
	}
}
