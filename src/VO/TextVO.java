package VO;

public class TextVO {
	//멤버필드
	private int no, examCode, textNum;
	private String textEx;
	
	//기본생성자
	public TextVO() {}
	
	//생성자
	public TextVO(int examCode, int textNum, String textEx) {
		super();
		this.examCode = examCode;
		this.textNum = textNum;
		this.textEx = textEx;
	}
	
	public TextVO(int no, int examCode, int textNum, String textEx) {
		super();
		this.no = no;
		this.examCode = examCode;
		this.textNum = textNum;
		this.textEx = textEx;
	}
	
	//메소드
	//getter,setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getExamCode() {
		return examCode;
	}
	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}
	public int getTextNum() {
		return textNum;
	}
	public void setTextNum(int textNum) {
		this.textNum = textNum;
	}
	public String getTextEx() {
		return textEx;
	}
	public void setTextEx(String textEx) {
		this.textEx = textEx;
	}
	
	@Override
	public String toString() {
		return  textEx ;
	}

}
