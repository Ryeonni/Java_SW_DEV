package VO;

public class SubjectVO {
	//멤버필드
	private int subjectCode;
	private String subjectName;
	
	//기본생성자
	public SubjectVO() {}
	
	//생성자
	public SubjectVO(int subjectCode, String subjectName) {
		super();
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
	}
	
	//메소드
	//getter,setter
	public int getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	@Override
	public String toString() {
		return subjectCode+"과목 : " + subjectName;
	}

}
