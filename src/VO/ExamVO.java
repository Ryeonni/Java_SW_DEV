package VO;

import java.sql.Blob;

public class ExamVO {
	//멤버필드
	private int examCode, answer;
	private String examQuestions, blobY, fileName, keyword;
	private Blob blob;
	
	//기본생성자
	public ExamVO(){ }
	
	//생성자
	public ExamVO(int examCode, String examQuestions, String blobY, String fileName) {
		super();
		this.examCode = examCode;
		this.examQuestions = examQuestions;
		this.blobY = blobY;
		this.fileName = fileName;
	}
	
	public ExamVO(int examCode, String examQuestions, String blobY, String fileName, Blob fileType, int answer, String keyword) {
		super();
		this.examCode = examCode;
		this.examQuestions = examQuestions;
		this.blobY = blobY;
		this.fileName = fileName;
		this.blob = fileType;
		this.answer = answer;
		this.keyword = keyword;
	}	
	//테스트용
	public ExamVO(int examCode, String examQuestions) {
		super();
		this.examCode = examCode;
		this.examQuestions = examQuestions;
	}

	//메소드
	//getter,setter
	public int getExamCode() {
		return examCode;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public Blob getBlob() {
		return blob;
	}
	public void setBlob(Blob fileType) {
		this.blob = fileType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}
	public String getExamQuestions() {
		return examQuestions;
	}
	public void setExamQuestions(String examQuestions) {
		this.examQuestions = examQuestions;
	}
	public String getBlobY() {
		return blobY;
	}
	public void setBlobY(String blobY) {
		this.blobY = blobY;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//20221010 수정
	@Override
	public String toString() {
		return examCode + ":;" + examQuestions + ":;" + blobY + ":;" + fileName + ":;" + blob + ":;" + answer + ":;" + keyword;
	}
}
