package burger.mvc.dao;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import burger.exception.BurgerException;
import burger.mvc.vo.Burger;

import static common.JDBCTemplate.*;

public class BurgerDao {
	private Properties prop = new Properties();
	public BurgerDao() throws BurgerException {
		try {
			prop.load(new FileReader("properties/query.properties"));
		} catch (Exception e) {
			throw new BurgerException(e.getMessage());
		}
	}
	
	public int insertBurger(Connection conn, ArrayList<Burger> blist) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		return result;
	}
	
	public ArrayList<Burger> selectMenu(){
		return null;
	}
	
	public ArrayList<Burger> selectStock(){
		return null;
	}
}
