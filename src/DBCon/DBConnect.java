package DBCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnect {

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public Connection getConnection() {
		Connection con=null;
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		try {
			con=DriverManager.getConnection(url, "PROJECT", "1234");
		} catch (SQLException e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
			System.exit(0);
		}
		return con;
	}

	public void close(Connection con,PreparedStatement pstmt,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			System.out.println("close 실패");
			e.printStackTrace();
		}
	}
	
	public void close(Connection con,PreparedStatement pstmt) {
		try {
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			System.out.println("close 실패");
			e.printStackTrace();
		}
	}
}
