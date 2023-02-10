package DataList;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class CurrentTime {
	long systemTime = System.currentTimeMillis();
	
	public String run() {
		// 현재 시스템 시간 구하기
		//long systemTime = System.currentTimeMillis();
		 
		// 출력 형태를 위한 formmater 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
		 
		// format에 맞게 출력하기 위한 문자열 변환
		String dateToStr = formatter.format(systemTime);
			
		return dateToStr;
	}
	
	public String time() {
		String clock;
//		while(true) {
			SimpleDateFormat t = new SimpleDateFormat("HH시 mm분 ss초", Locale.KOREA);
			clock = t.format(systemTime);
/*
			try { 
				Thread.sleep(100);
		    } catch(Exception e) {}		
*/		
		return clock;
//		}
	}
}
