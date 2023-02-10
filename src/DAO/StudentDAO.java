package DAO;

import java.sql.*;

import DBCon.DBConnect;
import VO.*;

public class StudentDAO extends DBConnect{
	
	private static StudentDAO dao = null;
	public StudentDAO() {}
	public static StudentDAO getInstance() {
		if(dao==null)
			dao=new StudentDAO();
		return dao;
	}
	
	public String SelectVo(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO vo = null;

		//랜덤으로 학생정보 가져오기
		String sql ="select * from (SELECT name, mobile FROM student order by DBMS_RANDOM.RANDOM) where rownum=1";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new StudentVO(rs.getString(1),rs.getString(2));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con,pstmt,rs);
		}
		return vo.toString();
	}
	
}
