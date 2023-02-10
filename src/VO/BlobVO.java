package VO;

public class BlobVO {
	//멤버필드
	private int no, examCode;
	private String fileName;

	//기본생성자
	public BlobVO() {}
	
	//생성자
	public BlobVO(int no, int examCode, String fileName) {
		super();
		this.no = no;
		this.examCode = examCode;
		this.fileName = fileName;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return  no + ", " + examCode + ", " + fileName ;
	}


}
