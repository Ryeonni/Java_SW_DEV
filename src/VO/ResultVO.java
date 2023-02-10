package VO;

public class ResultVO extends CodeList {
	//멤버필드
//	private int  answer, stuAnswer, examCode, score, ;
	private int subjectCode, answer, examCode;
	private String mobile, examQuestions, keyword;
	private TestTitle<Integer,String> tit;
	private CodeList code;
	private String student;
	int stuAnswer;
//	private String testType;
//	private int score;
	
	//기본생성자
	public ResultVO() {}	
	
	//생성자
	public ResultVO(TestTitle<Integer,String> tit) {
		this.tit = tit;
	}
	
	public ResultVO(CodeList code) {
		this.code = code;
	}
	
	public ResultVO(TestTitle<Integer,String> tit, CodeList code, int answer, int examCode, int subjectCode, String examText1, String keyword, String student, String mobile){
		this.tit = tit;
		this.code = code;
		this.answer = answer;
		this.examCode = examCode;
		this.subjectCode = subjectCode;
		this.examQuestions = examText1;
		this.keyword = keyword;
		this.student = student;
		this.mobile = mobile;
	}

	//tit, code, answer, examText1, keyword, studentInfo[0], studentInfo[1]
/*	
	public ResultVO(TestTitle<Integer,String> tit, CodeList code, int answer, String examText1, String keyword, String student, String mobile){
		this.tit = tit;
		this.code = code;
		this.answer = answer;
		this.examQuestions = examText1;
		this.keyword = keyword;
		this.student = student;
		this.mobile = mobile;
	}
*/	
	public int getScore() {
		// 정답과 학생이 체크한 답 비교해서 맞으면 score = 1
		int score =0;
		if(answer == getChkCode()) score = 1;
		return score;
	}
/*
	public void setScore(int score) {
		this.score = score;
	}
*/	
	//메소드
	//getter,setter
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}	
	public TestTitle<Integer,String> getTit() {
		return tit;
	}
	public void setTit(TestTitle<Integer,String> tit) {
		this.tit = tit;
	}		
	public int getStuAnswer() {
		return getChkCode();
	}	
	public void setStuAnswer(int stuAnswer) {
		this.stuAnswer = getChkCode();;
	}	
	public int getExamCode() {
		return examCode;
	}
	public int getSubjectCode() {
		return subjectCode;
	}	
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;		
	}	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getExamQuestions() {
		return examQuestions;
	}
	public void setExamQuestions(String examQuestions) {
		this.examQuestions = examQuestions;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public CodeList getCode() {
		return code;
	}
	public void setCode(CodeList code) {
		this.code = code;
	}	

}
