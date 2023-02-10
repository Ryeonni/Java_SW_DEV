package VO;

//일일테스트와 모의고사 멤버 클래스
public class TestTitle<T, M> {
	private T t;	// 일일:0/모의고사:1 코드
	private M m;	// 명칭
	
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public M getM() {
		return m;
	}
	public void setM(M m) {
		this.m = m;
	}
	@Override
	public String toString() {
		return ""+m+"";
	}
}
