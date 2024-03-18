package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	/*
	 * Template : 틀
	 * JDBC 작업위한 코드 제공 클라스
	 * JDBC 관련 공통 코드 작성
	 * 
	 * Connection
	 * commit /rollback
	 * JDBC 객체 자원 반환 구문 close();
	 * 
	 * 어디서든 사용할수 있게 모든 메서드를 public static으로
	 */
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			
			if(conn == null || conn.isClosed()) {
				
				Properties prop = new Properties(); // k:v 모두 String
				prop.loadFromXML(new FileInputStream("driver.xml"));
				//prop에 저장된값 얻어오기
				String driver = prop.getProperty("driver"); // oracle.jdbc.driver.OracleDriver
				String url = prop.getProperty("url"); // jdbc:oracle:thin:@localhost:1521:xe
				String user = prop.getProperty("user");
				String pw = prop.getProperty("pw");
				
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url,user,pw);
				
				conn.setAutoCommit(false);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 트랜잭션 제어 코드
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())  conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())  conn.rollback();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/* JDBC 객체 자원 반환 (close) + 오버로딩 */
	public static void close(Connection conn) {
		try {
			if(conn !=null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stmt) {
		// Statement, PreparedStatement 두 객체 close 처리하는 메서드
		
		try {
			if(stmt != null && !stmt.isClosed())  stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void close(ResultSet rs) {
		
		try {
			if(rs != null && !rs.isClosed())  rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
