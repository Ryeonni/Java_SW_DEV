package DAO;

import java.sql.*;

import DBCon.DBConnect;
import VO.ResultVO;

public class ResultDAO extends DBConnect{
	
	private static ResultDAO dao = null;
	public ResultDAO() {}
	public static ResultDAO getInstance() {
		if(dao==null)
			dao=new ResultDAO();
		return dao;
	}

	public int InsertResult(ResultVO vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql="INSERT into RESULT_TBL values(RESULT_SEQ.nextval,?,?,?,?,?,sysdate,?,?,?,?)";
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getMobile()); 
			pstmt.setString(2, vo.getExamQuestions());
			pstmt.setInt(3, vo.getAnswer()); 
			pstmt.setInt(4, vo.getStuAnswer());
			pstmt.setInt(4, vo.getChkCode());
			pstmt.setInt(5, vo.getExamCode()); 
			pstmt.setObject(6, vo.getTit().toString());
			pstmt.setInt(7, vo.getScore()); 
			pstmt.setString(8, vo.getKeyword());
			pstmt.setInt(9, vo.getSubjectCode()); 
/*			
			System.out.println("전화번호:"+vo.getMobile()+", 문제:"+vo.getExamQuestions()+", 답:"+vo.getAnswer()+", 체크값(학생답):"+vo.getChkCode()+", 문제코드:"+vo.getExamCode()
			+", 시험종류:"+vo.getTit().toString()+", 정답 개수:"+vo.getScore()+", 키워드:"+vo.getKeyword()+", 과목코드:"+vo.getSubjectCode()); 
		
			System.out.println(sql);
*/			
			result=pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con,pstmt);
		}
		return result;
	}
}
