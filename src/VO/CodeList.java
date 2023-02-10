package VO;

import VO.*;

public class CodeList extends SubjectVO {	// SubjectVO 에서 subjectCode,subjectName 상속받음
	//멤버필드
//	private int subjectCode;	//과목코드
	private int xSubjectCode;	//과목코드2
	private int rowNum;	//랜덤 데이타 갯수
	private int examCode;	//시험문제코드
	private int chkCode;	//체그한 번호
	
	//기본생성자
	public CodeList() {}
	
	//생성자
	public CodeList(int examCode) {
		super();
		this.examCode = examCode;
	}
	public CodeList(int xsubjectCode, int rowNum) {
		super();
//		this.subjectCode = subjectCode;
		setSubjectCode(xsubjectCode+1);
		this.rowNum = rowNum;
	}
	
	public CodeList(int subjectCode, int rowNum, int xSubjectCode) {
		super();
		//subjectCode = getSubjectCode();
		setSubjectCode(xSubjectCode+1);
		this.rowNum = rowNum;
		this.xSubjectCode = xSubjectCode;
	}
	
	//메소드
	//getter,setter
/*	
	public int getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
*/
	public int getxSubjectCode() {
		return xSubjectCode;
	}
	public void setxSubjectCode(int xSubjectCode) {
		this.xSubjectCode = xSubjectCode;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getExamCode() {
		return examCode;
	}
	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}
	public int getChkCode() {
		return chkCode;
	}
	public void setChkCode(int chkCode) {
		this.chkCode = chkCode;
	}
	
	@Override
	public String toString() {
		return getSubjectCode()+"";
	}
}
