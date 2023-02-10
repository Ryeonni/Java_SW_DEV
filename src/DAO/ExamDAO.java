package DAO;

import java.sql.*;
import java.util.ArrayList;

import DBCon.DBConnect;
import VO.*;

public class ExamDAO extends DBConnect{
	
	private static ExamDAO dao = null;
	public ExamDAO() {}
	public static ExamDAO getInstance() {
		if(dao==null)
			dao=new ExamDAO();
		return dao;
	}	
	
	// 시험문제와 예문 랜덤생성하여 가져오기. (DBMS_RANDOM.RANDOM 사용)
	public ArrayList<ExamVO> SelectOne(CodeList code){
		ArrayList<ExamVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		String sql ="SELECT e.exam_code, e.exam_questions, e.blob_Y, b.file_name, b.file_type, e.answer, e.keyword \r\n"
				+ "FROM exam_tbl e left join blob_tbl b\r\n"
				+ "on e.exam_code = b.exam_code where e.exam_code IN\r\n"
				+ "    (SELECT exam_code FROM\r\n"
				+ "             (SELECT exam_code FROM exam_tbl \r\n"
				+ "                 WHERE subject_code = ?\r\n"
				+ "                      ORDER BY DBMS_RANDOM.RANDOM)\r\n"
				+ "     WHERE ROWNUM <= ?)\r\n";
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code.getxSubjectCode()+1);
			pstmt.setInt(2, code.getRowNum());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(new ExamVO(rs.getInt(1), rs.getString(2), rs.getString(3) ,rs.getString(4), rs.getBlob(5), rs.getInt(6), rs.getString(7)));
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
