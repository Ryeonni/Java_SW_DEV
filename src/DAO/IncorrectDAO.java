package DAO;

import java.sql.*;
import java.util.ArrayList;

import DBCon.DBConnect;
import VO.*;


public class IncorrectDAO extends DBConnect{

	private static IncorrectDAO dao = null;
	public IncorrectDAO() {}
	public static IncorrectDAO getInstance() {
		if(dao==null)
			dao = new IncorrectDAO();
		return dao;
	}

	//모의고사 - rvo로 수정 2022.10.13
	//오답수량, 키워드 select
	public ArrayList<IncorrectVO> Select(ResultVO rvo, int subCode){
		ArrayList<IncorrectVO> list = null;
		Connection con = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT distinct(keyword),score FROM result_tbl \r\n"
				+ "WHERE mobile = ? and subject_code = ? and score = 0 AND test_type = ? \r\n"
				+ "AND TO_CHAR(exam_date,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD')";
//		System.out.println(sql + rvo.getMobile()+ rvo.getTit().toString() + subCode);
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rvo.getMobile());
			pstmt.setInt(2, subCode);
			pstmt.setString(3, rvo.getTit().toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(new IncorrectVO(rs.getInt(2),rs.getString(1)));
				}while(rs.next());
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(con,pstmt,rs);
		}
		return list;
	}
	
	//일일테스트
	//오답 과목명 select
	public ArrayList<IncorrectVO> SelectSub(String mobile){
		ArrayList<IncorrectVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select exam_code, subject_code, stu_answer, exam_questions, keyword "
				+ "from result_tbl where mobile =? and score=0 and test_type ='일일 테스트' "
				+ "and TO_CHAR(exam_date,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD')";
		
		//System.out.println(sql+" // mobile = "+mobile);
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mobile);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(new IncorrectVO(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con,pstmt,rs);
		}
		return list;
	}		
	
	//학생별 일일 테스트 시험을 봤는지 확인! 2022.10.13 추가
	public int SelectJoin(String mobile){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		String testType ="일일 테스트";
		
		String sql ="select count(mobile) as cnt from result_tbl where mobile = ? and test_type = ? \r\n"
				+ "and TO_CHAR(exam_date,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD')";

		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mobile);
			pstmt.setString(2, testType);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result= rs.getInt(1);
				//System.out.println(result);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			close(con,pstmt,rs);
		}
		return result;
	}
	
}