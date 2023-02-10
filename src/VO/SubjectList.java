package VO;

import java.util.ArrayList;

import DAO.*;
import VO.*;

public class SubjectList {
	private String subject[];	//과목 배열
	ArrayList<SubjectVO> list;

	
	public String[] Select(){
		list = new ArrayList<>();
		list = new SubjectDAO().SelectName();
		int m;
		
		int number = 10;
		subject = new String[list.size()];
		String newSubJect;
		
		m = 0;
		//page = m;	// page : 페이징 처리를 위한 변수
		for(SubjectVO vo : list) {			
			newSubJect = new String();
			subject[m] = newSubJect;
			subject[m] = vo.toString() + " ( "+ number +" 문항 ) ";
			m++;
		}
		
		return subject;
	}

}
