package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {
	
	public static void main (String[] args) {
		
		/* jdbc 객체 참조변수 선언 */
		Connection conn = null; //-연결
		// 특정 DB와의 연결 정보를 저장한 객체
		// DB랑 연결하는거
		// SQL문장 실행전에 꼭 있어야함
		
		Statement stmt = null; //-전달
		// SQL 을 String형태로 DB에 전달 후에 결과 받아오는 객체
		// SQL 전달하고 실행 결과 가져옴 -왔다 갔다
		
		ResultSet rs = null; //-저장,접근
		// SELECT 결과 저장 객체	
		// 조회결과 0행 이상
		// 1행씩만 접근한다...
		
		
		
		
		// try 구문 먼저 작성하기
		try {
			/* 2. connection 생성*/
			//2-1
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 이러면 객체가 된대, JDBC 혼자쓰는 객체래
			// 뭔소리야.....
			// --얘있어야 java랑 orcle db랑 연결가능
			//2-2
			String type			= "jdbc:oracle:thin:@"; // 드라이버 종류
			String host 		= "localhost"; // DB 서버 컴퓨터의 IP 주소
			String port 		= ":1521"; // DB 서버 컴퓨터에 DB프로그램 연결 번호
			String dbName 	= ":xe"; // DB 이름
			String userName = "KH_LEJ"; // 사용자 계정
			String pw				= "KH1234"; // 계정 비밀번호
			
			// DriverManager로 connection 얻어옴
			conn = DriverManager.getConnection(type+host+port+dbName, userName, pw);
			
			/* 3.SQL 작성 */
			
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY";
			// 안에다 ;쓰지 마라

			/* 4. Statement 객체 생성 */
			stmt = conn.createStatement();
			// conn에 연결 db로 sql 전달(smtm이니까) 하고 결과 받는Statement만듬(createStatement();)

			/* 5. Statement 객체를 이용해서 SQL 수행 후 결과 반환 받기 */
			stmt.executeQuery(sql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// 클래스가 존재하지 않으면 발생하는 예외 처리
			// -> 추가한 라이브러리가 없거나, 클래스 경로를 잘못 작성
			e.printStackTrace();

		}
		
		
	}

}
