package DAO;

import java.sql.*;
import java.util.ArrayList;

import DBCon.DBConnect;
import VO.*;

public class TextDAO extends DBConnect{
	
	private static TextDAO dao = null;
	public TextDAO() {}
	public static TextDAO getInstance() {
		if(dao==null)
			dao=new TextDAO();
		return dao;
	}

	//------항목번호,항목 추출 사용
	//문제번호받아서 항목번호,항목 추출
	//TextVO(int examCode, int textNum, String textEx)
	public ArrayList<TextVO> Select(CodeList code){
		ArrayList<TextVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT exam_code,text_num,text_ex FROM text_tbl WHERE exam_code = ? ORDER BY text_num";
		//System.out.println(sql+code.getExamCode());
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, code.getExamCode());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(new TextVO(rs.getInt(1),rs.getInt(2),rs.getString(3)));
				}while(rs.next());
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con,pstmt,rs);
		}
		return list;
	}
}

