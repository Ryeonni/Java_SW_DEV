package Project;
//공모전 Project
//2022.09.27~ 2022.10.14
//안세련

import java.util.ArrayList;
import DAO.*;
import VO.*;

class ResultCli {	

	// mobile, 모의 고사
	// 일일테스트 - 점수, 오답문제 / 모의고사 - CLI, 키워드

	public ResultCli(ResultVO rvo) {	//String mobile, String testType
		int number = 10;
		System.out.println("================== [ 모의고사 결과 ] ====================");
		System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");

		ArrayList<SubjectVO> list;
		ArrayList<IncorrectVO> ilist;
		list = new SubjectDAO().SelectName();
		SubjectVO[] vo = new SubjectVO[list.size()];

		for (int i = 0; i < list.size(); i++) {
			vo[i] = list.get(i);
			int subCode = vo[i].getSubjectCode();
			System.out.println(vo[i].toString());

			ilist = new IncorrectDAO().Select(rvo, subCode);
			if(ilist != null) {	//데이터가 있는 경우 출력
				System.out.println("\n"+number +"문항 중 "+ (number-ilist.size()) +"문항 정답!\t오답 "+ilist.size() +"문항");

				int k = 0;
				System.out.print("[키워드] ");
				for (IncorrectVO Ivo : ilist ) {
					Ivo = ilist.get(k);
					System.out.print( Ivo.toString()+ " | ");
					if(k==(5))System.out.println();
					k++;			

				}	// end for2
			}else {
				System.out.println(" 출력 결과가 없습니다. ");
			}
			System.out.println("\n―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			//System.out.println(vo[i].getSubjectName());
		}	// end for1
		System.out.println("=========================================================");

	}

}//class

