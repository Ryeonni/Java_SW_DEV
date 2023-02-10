package DataList;

// 긴 텍스트 길이 구하는 클래스
public class TextLength {

	public TextLength() {}

	public int run(String txt, int len) {
		return ((txt.length()-1) /len ) + 1;
	}	

}
