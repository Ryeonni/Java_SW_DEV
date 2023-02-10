package DAO;

import java.sql.*;
import java.util.ArrayList;

import DBCon.DBConnect;
import VO.*;

public class SubjectDAO extends DBConnect {
	
	private static SubjectDAO dao = null;
	public SubjectDAO() {}
	public static SubjectDAO getInstance() {
		if(dao==null)
			dao=new SubjectDAO();
		return dao;
	}

	public ArrayList<SubjectVO> SelectName(){
		ArrayList<SubjectVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT subject_code, subject_name FROM subject_tbl order by subject_code";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new SubjectVO(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(con,pstmt,rs);
		}
		return list;
	}
	
	public SubjectVO SelectNameOne(CodeList code){
		Connection con = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		SubjectVO vo = null;	// = new SubjectVO();
		String sql = "SELECT subject_code, subject_name FROM subject_tbl where subject_code=?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code.getSubjectCode());
			rs = pstmt.executeQuery();
			if(rs.next())
				vo=new SubjectVO(rs.getInt(1),rs.getString(2));			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(con,pstmt,rs);
		}
		return vo;
	}
}//class
