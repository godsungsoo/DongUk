package common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import burger.exception.BurgerException;
public class JDBCTemplate {
	public static Connection getConnection() throws BurgerException{
		Connection conn = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("properties/dirver.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pwd"));
			conn.setAutoCommit(false);
		} catch (Exception e) {
			throw new BurgerException(e.getMessage());
		}
		return conn;
	}
	public static void close(Connection conn) throws BurgerException{
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			throw new BurgerException(e.getMessage());
		}
	}
	public static void close(Statement stmt) throws BurgerException{
		try {
			if(stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (Exception e) {
			throw new BurgerException(e.getMessage());
		}
	}
	public static void close(ResultSet rset) throws BurgerException{
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (Exception e) {
			throw new BurgerException(e.getMessage());
		}
	}
	public static void commit(Connection conn) throws BurgerException{
		try {
			if(conn != null && !conn.isClosed())
				commit(conn);
		} catch (Exception e) {
			throw new BurgerException(e.getMessage());
		}
	}
	public static void rollback(Connection conn) throws BurgerException{
		try {
			if(conn != null && !conn.isClosed())
				rollback(conn);
		} catch (Exception e) {
			throw new BurgerException(e.getMessage());
		}
	}
	
}
