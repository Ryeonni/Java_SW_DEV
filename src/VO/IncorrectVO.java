package VO;

public class IncorrectVO {
	//멤버필드
	private int score, examCode, subjectCode ,stuAnswer;
	private String  keyword, examQuestions;

	//기본생성자
	public IncorrectVO() {}
	
	//생성자
	public IncorrectVO(int score) {
		super();
		this.score = score;
	}
	
	// 모의 고사용 VO!
	public IncorrectVO(int score, String keyword) {
		super();
		this.score = score;
		this.keyword = keyword;
	}
	
	// 일일 테스트용 VO!
	public IncorrectVO(int examCode, int subjectCode, int stuAnswer, String examQuestions, String keyword) {
		super();
		this.examCode = examCode;
		this.subjectCode = subjectCode;
		this.stuAnswer = stuAnswer;
		this.examQuestions = examQuestions;
		this.keyword = keyword;
	}
	
	//메소드
	//getter,setter
	public int getExamCode() {
		return examCode;
	}
	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}
	public int getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	public int getStuAnswer() {
		return stuAnswer;
	}
	public void setStuAnswer(int stuAnswer) {
		this.stuAnswer = stuAnswer;
	}
	public String getExamQuestions() {
		return examQuestions;
	}
	public void setExamQuestions(String examQuestions) {
		this.examQuestions = examQuestions;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override	
	public String toString() {
		return keyword;
	}
	
	//일일테스트용 toString
	public String toString2() {
		return examCode + ":;"+stuAnswer+":;"+examQuestions;
	}

}
