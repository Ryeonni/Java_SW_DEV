package Project;
//공모전 Project
//2022.09.27~ 2022.10.14
//안세련

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.*;

import DataList.*;
import VO.*;
import DAO.*;

// 전체 판넬 크기 가로 : 900 ,세로 : 800
// layered 크기 가로 : 860 ,세로 : 700
@SuppressWarnings("serial")
public class GuiTest extends GuiIntro {

	private JLayeredPane layP, layP3;	
	private JLayeredPane[] layP2;				// 문제 풀이 화면 배열	
	private TestTitle<Integer,String> tit;			// 일일테스트(0), 모의고사(1), 오답문제(2) 선택 클래스
	private JCheckBox[] chckbx;				// 지문 체크박스 배열
	private final int LayeredY= 860, LayeredH = 700;	// 레이어드 크기 값
	private int butY, butH;					// 버튼 위치 값
	private CodeList code;					// examCode, subjectCode, xSubjectCode
	private SubjectVO vo;					// 과목명 멤버필드 vo
	private SubjectDAO dao;					// 과목명 select dao
	private ResultVO rvo;					// 결과  멤버필드 vo
	private String sdao;					// 학생정보
	private String[] studentInfo;				// 학생정보 split
	private int number;	 				// 응시형태에 따른 문항수(일일테스트는 2, 모의고사는 10
	private int sumNumber;					// 응시 총 문항수 sumNumber
	private int page;					// 페이징 Number
	private int xSubjectCode;				// subjectCode 재 정의
	private int examCode, answer;				// 문제번호
	private int chkCode;					// 체크한 번호코드
	private String keyword;
	private String examText1;				// 문제텍스트
	private String examImg;					// 예문이미지파일명
	private int TotScore;					// 총 맞춘 개수
	private String chkNumText;				// 체크한 지문 코드와 내용
	private int i, n;
	private int maxLength;
	private String[] examVo;				// 문제 배열
	private String[] examText;				// 문제String() 배열
	private JLabel leb1;					// 상단 응시조건 레이블
	private int imgGetW , imgGetH;
	
	public GuiTest() {}	//default 생성자
	public void run(boolean sw1) {
//---------------------------------------------------------------------------------	
// 크기 및 위치 초기값 설정	

		butY= 280; butH = 650;				// 하단 버튼 위치 값		
		page = 0;					// 페이지 초기값
		n = 0;						// 시험 문제 클래스 배열에 담아 보여주기 초기값
		maxLength = 0;					// 시험 문제 클래스 배열에 담아 보여주기 max값 
//---------------------------------------------------------------------------------
		
		sdao = new StudentDAO().SelectVo();		// 학생 정보 (이름, 전화번호)
		studentInfo = sdao.split(":;");			// 학생 정보 0:이름, 1:전화번호)
		layP = new JLayeredPane();
		//layP.setBackground(new Color(255, 255, 255));
		layP.setBackground(Color.WHITE);
		layP.setBounds(12, 10, LayeredY, LayeredH);
		getContentPane().add(layP);
		
		JLabel leb = new JLabel("CBT 시험 화면");
		leb.setHorizontalAlignment(SwingConstants.CENTER);
		leb.setVerticalAlignment(SwingConstants.CENTER);
		leb.setFont(new Font("맑은 고딕", Font.BOLD, 49));
		leb.setBackground(getBackground());
		leb.setBounds(80, 63, 700, 94);
		layP.add(leb);
		//-------------------------------------------------------------
		String introComment = "님 시험 준비 되셨나요^^";
		int resultCnt = new IncorrectDAO().SelectJoin(studentInfo[1]);
		if(resultCnt>0) introComment ="님 "+new CurrentTime().run()+" 일일테스트는 응시 완료하였습니다^^";
		//-------------------------------------------------------------
		JLabel leb_4 = new JLabel(studentInfo[0]+introComment);
		leb_4.setHorizontalAlignment(SwingConstants.CENTER);
		leb_4.setVerticalAlignment(SwingConstants.CENTER);
		leb_4.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		leb_4.setForeground(Color.BLUE);
		leb_4.setBackground(SystemColor.white);
		leb_4.setBounds(80, 167, 700, 25);
		layP.add(leb_4);
		//------------------------------------------------------------
		JButton[] test = new JButton[] {new JButton("일일 테스트"),new JButton("모의 고사")};

		int buttonY = 240;	//버튼 위치값

		for(i=0;i<test.length;i++) {

			test[i].setForeground(Color.BLACK);
			test[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			test[i].setBackground(new Color(173, 216, 230));
			test[i].setBackground(getBackground());
			test[i].setBounds(300, buttonY, 251, 51); buttonY+=90;
			test[i].setFocusable(false);
			
			layP.add(test[i]);
			
			test[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tit = new TestTitle<>();	
					String title = e.getActionCommand();
					tit.setT(0);
					if(title.equals("모의 고사")) tit.setT(1);	// 모의고사인 경우 1로 저장
					tit.setM(title);
					layP.setVisible(false);
					GuiDayTest(true,page);
				}			
			});
			
		}	//end for

		JButton btn_2 = new JButton("종 료");
		btn_2.setBackground(SystemColor.activeCaption);
		btn_2.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		btn_2.setBounds(386, 600, 97, 51);
		layP.add(btn_2);	

		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		setVisible(sw1);		
	}

//--------------------------------------------------------------------	
//----------- 일일테스트 / 모의고사 선택 화면 --------------------------------
//--------------------------------------------------------------------	
	public void GuiDayTest(boolean sw,int page) {	// 모의고사 일일테스트 선택
		Clock(true);
		int spaceY = 0;				// setBound 간격Y
		int subjectCode=0;			// db select에 의해 바뀌는 과목코드
		number = 10;		
		if(tit.getT()==0)number = 2;		// 일일테스트인경우 2로 설정(과목별 2문항씩)
		sumNumber = number * 5;			// 전체 시험문항 수
		xSubjectCode = page/number;		// 과목코드에 따른 화면설정 ex)일일테스트의 경우 1과목당 2문제

		code = new CodeList(subjectCode, number, xSubjectCode);
		
		layP2 = new JLayeredPane[sumNumber];	// 페이지용 레이어 배열 준비
		JLayeredPane newLayP;
		newLayP = new JLayeredPane();
		layP2[page] = newLayP;
		layP2[page].setBackground(Color.WHITE);
		layP2[page].setBounds(12, 10, LayeredY, LayeredH);
		getContentPane().add(layP2[page]);
		layP2[page].setLayout(null);
		layP2[page].resetKeyboardActions();		

//--------시험 응시 타입 타이틀-----------------------------------------------
		leb1 = new JLabel(tit.toString());
		leb1.setHorizontalAlignment(SwingConstants.CENTER);
		leb1.setVerticalAlignment(SwingConstants.CENTER);
		leb1.setBackground(new Color(70, 130, 180));
		leb1.setFont(new Font("맑은 고딕", Font.BOLD, 49));
		leb1.setBounds(80, 10, 700, 74);
		layP2[page].add(leb1);

		vo = new SubjectVO();
		dao = new SubjectDAO();
		
		String sub = dao.SelectNameOne(code).toString();
		
		subjectCode = vo.getSubjectCode();
		String lebleText = "<html><head><style>ul {color: blue; font-size: 11px}</style></head><body>"+sub+" ( "+ number +"문항 ) ";
				lebleText += "<ul>( 총" + (sumNumber) +"문항 중 " + (page+1) +"번째 )</ul></body></html>";
	
		JLabel leb_3 = new JLabel(lebleText);
		leb_3.setHorizontalAlignment(SwingConstants.CENTER);
		leb_3.setVerticalAlignment(SwingConstants.CENTER);
		leb_3.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		leb_3.setBackground(new Color(245, 222, 179));
		leb_3.setBounds(80, 95, 700, 60);
		layP2[page].add(leb_3);

//--------db로 부터 가져올 data  [class 파일로 분리하기 Start]---------------------------

		examText = new String[7];
		int c = 0;
		if(n < maxLength-1 ){	// 배열에 저장된 문제가 있는지 확인
			if(n == maxLength-1) {	n = 1;maxLength = number;	}
			n++;
		}else {		//배열에 저장된 자료가 없거나 새로 DB에서 가져올 경우
			c = 0;
			n = 0;			
			maxLength = number;
			examVo = new String[number];	// 일일:2개, 모의고사 10개 배열 생성
			ArrayList<ExamVO> list = new ExamDAO().SelectOne(code);	// !!과목 코드 별 시험문제

			for(ExamVO vo : list) {	
				examVo[c] = vo.toString();c++;
			}
		}	//end if

//-----------db로 부터 가져올 data End-----------------------------------

		int leb_1Y = 160;	//JLabel setBounds 상단기준 Y위치
		spaceY += leb_1Y;
		examText = examVo[n].toString().split(":;");			// 구분자 ";:" 
		examCode = Integer.parseInt(examText[0]);			// examCode	
		answer = Integer.parseInt(examText[5]);				// 답 
		keyword = examText[6];						//keyword
		examText1 = examText[1];
		examImg = examText[3];
		
//---------Result rvo에 자료 저장 Start-----------------------------------	
		
		code.setExamCode(examCode);
		code.setxSubjectCode(xSubjectCode);
		rvo = new ResultVO(tit, code, answer, examCode, 
				code.getSubjectCode(), examText1, keyword, studentInfo[0], studentInfo[1]);
//		rvo = new ResultVO(tit, code, answer, examText1, keyword, studentInfo[0], studentInfo[1]);

//---------자료 저장 End---------------------------------------------------		

		int maxTextLenth = 48;	// 한줄에 표시되는 문자열 길이 구하기		
		int lenText = new TextLength().run(examText1, maxTextLenth);
		
		int textH = lenText * 28;		// 문제항목 텍스트 길이에 따른 조정 1칸당 높이
		
		JLabel leb_1 = new JLabel("<html><body>"+examCode+". "+examText1+"</body></html>");	//html태그를 사용하면 길 글자수는 자동으로 다음칸으로 이동
		leb_1.setVerticalAlignment(SwingConstants.TOP);
		leb_1.setEnabled(true);		
		leb_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		leb_1.setBounds(140, spaceY, 580, textH);spaceY+=textH;	// 기존 Y값에 높이를 더한다.
		layP2[page].add(leb_1);
			
//---------예문 이미지----------------------------------------------------------

		imgGetW=500; imgGetH=0;
		
		JLabel lbl;
		lbl = new JLabel();
//		examImg = "63.png";		// 이미지 사이즈 테스트용
		lbl = new JLabel("");
		if(!(examImg == null)) {	// img가 null이 아닌경우			
			try {
			
				lbl = new JLabel(new ImageView(examImg).run());
				imgGetW = new ImageView(examImg).imgSizeW();
				imgGetH = new ImageView(examImg).imgSizeH();
				lbl.setBounds(150, spaceY, imgGetW, imgGetH);spaceY+=imgGetH+3;

			} catch (IOException e1) { lbl = new JLabel(""); }
	
			//--------------------------------------------------------------------------		
			//	이미지 클릭했을때 or 마우스 오버했을때 원본 이미지 보여주기	
			//--------------------------------------------------------------------------
			lbl.addMouseListener(new MouseListener() { 

				@Override
				 public void mouseReleased(MouseEvent e) {}				 
				 @Override
				 public void mousePressed(MouseEvent e) {}				 
				 @Override
				 public void mouseExited(MouseEvent e) {}				 
				 @Override
				 public void mouseEntered(MouseEvent e) {
				/*	 
					 try {
						JOptionPane.showMessageDialog(null, new ImageView(examImg).img());
					} catch (HeadlessException | IOException e1) {
						
					}
				*/	
				 }				 
				 @Override
				 public void mouseClicked(MouseEvent e) {
					 try {
						JOptionPane.showMessageDialog(null, new ImageView(examImg).img());
					} catch (HeadlessException | IOException e1) {
						
					}
				 }			 
			});		
			//----------------------------------------------------------------	

		}	// end if
		lbl.setVerticalAlignment(SwingConstants.CENTER);		
		layP2[page].add(lbl);

		ArrayList<TextVO> tlist = new ArrayList<>();		

		tlist = new TextDAO().Select(code);

		ButtonGroup buttonG = new ButtonGroup();	// 체크된 내용이 1개만 선택되도록 그룹화		
		chckbx = new JCheckBox[tlist.size()];		

		
		int len = 48;	//지문 글자 수 자르기
		int lenLine = 1;

		i = 0;
		JCheckBox chckbxVal;
		for(TextVO vo : tlist) {

			lenLine = new TextLength().run(vo.toString(), len);	// 글자수에 따른 간격 조정		
			
			int chckbxH = 24 * lenLine;	// 지문 체크박스 1개당 높이 설정
			chckbxVal = new JCheckBox();
			chckbx[i] = chckbxVal;
			chckbx[i].setBounds(135, spaceY, 590, chckbxH);			
			chckbx[i].setText("<html><body>"+(i+1)+". "+vo.toString()+"</body></html>");	// html 태그 포함
			chckbx[i].setFont(new Font("맑은 고딕", Font.PLAIN, 13));

			chckbx[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					chkNumText = e.getActionCommand();	// 체크한 내용
					chkCode = Integer.parseInt(chkNumText.substring(12, 13));	//chckbx[i].setText 태그포함 자리수 기준이라
					
					code.setChkCode(chkCode); 	// CodeList code클래스에 저장
					rvo.setChkCode(chkCode);
					if(rvo.getChkCode()==answer) TotScore+=1;	// 체크값과 답 비교하여 총 맞춘 개수 산출

				}
			});
			buttonG.add(chckbx[i]);			
			
			layP2[page].add(chckbx[i]);
			spaceY+=chckbxH;
			i++;
		}	//end for	
		
//-----------하단 버튼에 레이어 별도 추가해서 가운데 정렬하기.----------------------------------------------
		
		int ButtonSize = 0;	// 버튼 개수에 따른 위치값 조정을 위한 변수
		
		JLayeredPane layP4 = new JLayeredPane();		// 버튼 정렬을 위한 레이어
		layP4.setLayer(layP2[page], 2);
		layP4.setBackground(SystemColor.activeCaptionBorder);
		layP4.setBounds(10, butH, 700, 50);
		layP2[page].add(layP4);
		
		JButton btn = new JButton("메인 화면");
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btn.setVerticalTextPosition(SwingConstants.CENTER);
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layP2[page].setVisible(false);				
				textP.setText("");
				run(true);
			}
		});
		btn.setBounds(butY+ButtonSize, 0, 97, 41);
		layP4.add(btn);
		
		ButtonSize += 100;
		// 문제풀이 페이지가 10페이지(일일), 또는 50페이지(모의)가 넘어가지 않을때까지
		if(page < (sumNumber-1)) {	
			JButton btn_1 = new JButton("다 음");
			btn_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layP.setVisible(false);
					layP2[page].setVisible(false);

					int page2 = page;	// 현재 페이지 재저장
					page2++;	

					new ResultDAO().InsertResult(rvo);		// 결과 자료 DB에 insert
					
					GuiDayTest(true, page2);
				}				
			});
			
			btn_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			btn_1.setVerticalTextPosition(SwingConstants.CENTER);
			btn_1.setHorizontalAlignment(SwingConstants.CENTER);
			btn_1.setBounds(butY+ButtonSize, 0, 97, 41);
			layP4.add(btn_1);
			ButtonSize += 100;
		}
		
		JButton btn_2 = new JButton("결과 보기");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
				new ResultDAO().InsertResult(rvo);			// 결과 자료 DB에 insert
		
				//결과 화면 ( 일일 테스트는 GUI화면으로 구현, 모의 고사는 CLI로 구현 )				
				if(tit.getT()==0) {
					layP.setVisible(false);
					layP2[page].setVisible(false);
					GuiResult(true,0);	// 일일테스트인경우 gui로 결과 보여주기
				}
				else {
					new ResultCli(rvo);
					System.exit(0);	//결과 값 CLI로 보여줄때 GUI종료
				}
			}			
		});		
		
		btn_2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btn_2.setVerticalTextPosition(SwingConstants.CENTER);
		btn_2.setHorizontalAlignment(SwingConstants.CENTER);
		btn_2.setBounds(butY+ButtonSize, 0, 97, 41);
		//layP2[page].add(btn_2);	
		layP4.add(btn_2);
		
		layP2[page].setVisible(sw);
	}
//----------------------------------------------------------------	
//-----------GUI 일일 테스트 결과 메소드-------------------------------
//----------------------------------------------------------------
	public void GuiResult(boolean sw, int ResultPage)  {	

		layP3 = new JLayeredPane();
		layP3.setBounds(12, 10, LayeredY, LayeredH);
		getContentPane().add(layP3);
		layP3.setLayout(null);
		
//------------응시 타입 ------------------------------------------		
		JLabel leb1 = new JLabel(tit.toString());
		leb1.setHorizontalAlignment(SwingConstants.CENTER);
		leb1.setVerticalTextPosition(SwingConstants.CENTER);
		leb1.setBackground(new Color(70, 130, 180));
		leb1.setFont(new Font("맑은 고딕", Font.BOLD, 49));
		leb1.setBounds(80, 10, 700, 74);
		layP3.add(leb1);
//----------------------------------------------------------------		
		JLayeredPane layP5 = new JLayeredPane();
		layP3.setLayer(layP5, 1);
		layP5.setBackground(SystemColor.activeCaptionBorder);
		layP5.setBounds(10, 208, 700, 304);
		layP3.add(layP5);

		JLabel lblResult = new JLabel(new CurrentTime().run()+" ["+rvo.getStudent()+"님] 응시 결과");
		lblResult.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setVerticalTextPosition(SwingConstants.CENTER);
		lblResult.setBounds(65, 10, 700, 50);
		layP5.add(lblResult);

		String lableText;
		int incorrectAnswer = sumNumber - TotScore;	// 오답 : 전체 문항수 - score합계
		lableText = "<html><body>&nbsp;&nbsp;&nbsp;[ 총  " + sumNumber + " 문항 ]<br><br>";
		lableText += "정답 문항 >> " + TotScore + " 개<br><br>";
		lableText += "오답 문항 >> " + incorrectAnswer + " 개";
		lableText +="</body></html>";		
		JLabel lblIncorrect = new JLabel(lableText);
		lblIncorrect.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblIncorrect.setVerticalAlignment(SwingConstants.CENTER);
		lblIncorrect.setBounds(330, 95, 400, 150);
		layP5.add(lblIncorrect);
		
		if(incorrectAnswer > 0) {	// 오답이 있는 경우만 오답 버튼 보여주기!!
			JButton btnView = new JButton("오답 보기");
			btnView.setBounds(335, 236, 122, 30);
			btnView.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			btnView.setFocusable(false);
			layP5.add(btnView);
			btnView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new GuiIncorrectAnswer(rvo);		
				}
			});
		}	//end if

//-------하단 버튼---------------------------------------------------------	
		JLayeredPane layP6 = new JLayeredPane();	// 하단 버튼용 레이어
		layP6.setLayer(layP3, 2);
		layP6.setBackground(SystemColor.activeCaptionBorder);
		layP6.setBounds(10, butH, 700, 50);
		layP3.add(layP6);
		
		JButton btn = new JButton("메인 화면");
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layP3.setVisible(false);
				textP.setText("");
				run(true);				
			}
		});
		btn.setBounds(300, 0, 97, 41);
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setVerticalTextPosition(SwingConstants.CENTER);
		btn.setFocusable(false);
		layP6.add(btn);		
	
		JButton btn_1 = new JButton("시험 종료");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});		
		
		btn_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btn_1.setBounds(410, 0, 97, 41);
		btn_1.setHorizontalAlignment(SwingConstants.CENTER);
		btn_1.setVerticalTextPosition(SwingConstants.CENTER);
		btn_1.setFocusable(false);
		layP6.add(btn_1);
	
		layP3.setVisible(sw);
		setVisible(true);
	}
	
}	// end class
