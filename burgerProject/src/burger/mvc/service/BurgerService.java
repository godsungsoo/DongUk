package burger.mvc.service;

import burger.exception.BurgerException;
import burger.mvc.dao.BurgerDao;
import burger.mvc.vo.Burger;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

public class BurgerService {
	private BurgerDao bdao;
	public BurgerService() throws BurgerException {
		 bdao = new BurgerDao();
	}
	
	public int burgerInsert(ArrayList<Burger> blist) throws BurgerException {
		Connection conn = getConnection();
		int result = bdao.insertBurger(conn, blist);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}
	
	public ArrayList<Burger> selectMenu() throws BurgerException{
		Connection conn = getConnection();
		ArrayList<Burger> blist = new ArrayList<>();
		close(conn);
		return blist;
	}
	
	public ArrayList<Burger> selectStock() throws BurgerException{
		Connection conn = getConnection();
		ArrayList<Burger> blist = new ArrayList<>();
		close(conn);
		return blist;
	}
	
	
}
