package VO;

public class StudentVO {
	//멤버필드
	private String name, mobile;
	
	//기본생성자
	public StudentVO() {}
	
	//생성자
	public StudentVO(String name, String mobile) {
		super();
		this.name = name;
		this.mobile = mobile;
	}

	//메소드
	//getter,setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return  name + ":;" + mobile ;
	}
}
