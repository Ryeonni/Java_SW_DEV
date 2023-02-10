package DataList;

import java.util.Arrays;

public class TestCheckNo {
	int examCode;
	int checkNo;
	int[] chk;
	
	public TestCheckNo() {	
		chk = new int[10];
		for(int i=0;i<chk.length;i++) {
		chk[i] = examCode;
		}
	}
	
	public int getExamCode() {
		return examCode;
	}
	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}
	public int getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}
	public int[] getChk() {
		return chk;
	}
	public void setChk(int[] chk) {
		this.chk = chk;
	}
	public void run() {
		chk[examCode]=examCode;
	}
	@Override
	public String toString() {
		return Arrays.toString(chk);
	}
	
}
