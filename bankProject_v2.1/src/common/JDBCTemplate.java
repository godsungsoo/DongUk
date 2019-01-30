package common;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

import bank.exception.BankException;

public class JDBCTemplate {
	public static Connection getConnection() throws BankException {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("properties/driver.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pwd"));
			conn.setAutoCommit(false);
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}
		return conn;
	}
	public static void close(Connection conn) throws BankException {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}
	}
	public static void close(Statement stmt) throws BankException {
		try {
			if(stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}
	}
	public static void close(ResultSet rset) throws BankException {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}
	}
	public static void commit(Connection conn) throws BankException {
		try {
			if(conn != null && !conn.isClosed())
				conn.commit();
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}
	}
	public static void rollback(Connection conn) throws BankException {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		} catch (Exception e) {
			throw new BankException(e.getMessage());
		}
	}
}
