package Project;
//공모전 Project
//2022.09.27~ 2022.10.14
//안세련

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import DAO.*;
import DataList.*;
import VO.*;

@SuppressWarnings("serial")
class GuiIncorrectAnswer extends GuiIntro{

	private JLayeredPane[] layP;	//첫 화면 배열 판
	private JLabel leb1;
	private int page;
	private final int LayeredY = 740, LayeredH = 650;
	private int butY, butH;
	private int spaceY;
	private ResultVO rvo;
	private CodeList code;
	private int examCode;		// 문제번호
	private String examText;	// 문제
	private String mobile;
	private int stuAnswer;
	private int i;
	private int listSize;
	private String[] examT;
	private JCheckBox chckbxVal;	// 테스트용
	private JCheckBox[] chckbx;	// 지문 체크박스 배열
	private ArrayList<IncorrectVO> list;
	
	public GuiIncorrectAnswer() {}
	
	public GuiIncorrectAnswer(ResultVO rvo) {
		this.rvo = rvo;
		this.mobile = rvo.getMobile();
		page = 0;	
		butY = 275; butH = 580;
		list = new IncorrectDAO().SelectSub(mobile);
		
		if(list != null) {
			listSize = list.size();
			examT = new String[listSize];	// 일일:2개, 모의고사 10개 배열 생성
			int c =0;
			for(IncorrectVO vo : list) {
				examT[c] = vo.toString2();c++;
			}
			run(page);	
		}
	}
	
	public void run(int page) {	// 오답보기 페이지
	
		code = new CodeList();		
		spaceY = 0;

		setTitle("CBT TEST - 오답 화면");	// 타이틀
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(getContentPane());
		getContentPane().setLayout(null);		
		setBounds(200, 100, 750, 680);
		//setLocation(600, 150);
		
		layP = new JLayeredPane[listSize];	// 오답 레이어 배열 준비
		JLayeredPane newLayP = new JLayeredPane();
		layP[page] = newLayP;
		layP[page].setBackground(Color.WHITE);
		layP[page].setBounds(5, 5, LayeredY, LayeredH);
		layP[page].setAutoscrolls(true);

		getContentPane().add(layP[page]);
		layP[page].setLayout(null);
		layP[page].resetKeyboardActions();
		
//--------테스트 타이틀-----------------------------------------
		
		leb1 = new JLabel(rvo.getTit().toString());
		leb1.setHorizontalAlignment(SwingConstants.CENTER);
		leb1.setVerticalAlignment(SwingConstants.CENTER);
		leb1.setBackground(new Color(70, 130, 180));
		leb1.setFont(new Font("맑은 고딕", Font.BOLD, 49));
		leb1.setBounds(20, 10, 650, 74);
		layP[page].add(leb1);
		
		JLabel leb_2 = new JLabel("오답 문항 ( "+ listSize +"개 중 " + (page+1) +"번째 ) ");
		leb_2.setHorizontalAlignment(SwingConstants.CENTER);
		leb_2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		leb_2.setBackground(new Color(245, 222, 179));
		leb_2.setBounds(20, 95, 650, 45);
		layP[page].add(leb_2);		

//-----------db로 부터 가져올 오답 data -----------------------------------	

		int leb_1Y = 160;	// JLabel setBounds 상단기준 Y위치
		spaceY += leb_1Y;
		String [] examText2 = new String[3];
		//examText = arrVo[n].toString().split(":;");
		examText2 = examT[page].toString().split(":;");
		examCode = Integer.parseInt(examText2[0]);
		stuAnswer = Integer.parseInt(examText2[1]);
		examText = examText2[2];
//--------------------------------------------------------------------
// 한줄에 표시되는 문자열 길이 구하기 클래스
		int maxTextLenth = 51;
		int lenText = new TextLength().run(examText, maxTextLenth);	
//--------------------------------------------------------------------	
//           오답 문제 시작
//--------------------------------------------------------------------
		JLabel leb_1 = new JLabel("<html><body>"+examCode+". "+examText+"</body></html>");	// html태그를 사용하면 길 글자수는 자동으로 다음칸으로 이동
		leb_1.setVerticalAlignment(SwingConstants.TOP);
		leb_1.setEnabled(true);		
		leb_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		int textH = lenText * 28;		//문제항목 텍스트 길이에 따른 조정 1줄당
		leb_1.setBounds(60, spaceY, 580, textH);spaceY+=textH;	// 기존 Y값에 높이를 더한다.

		layP[page].add(leb_1);
//---------------------------------------------------------------------------------------
		String lebleText = "   [ 선택한 오답 번호 : "+stuAnswer+" ]  정답은 ?? 공부해요 ^^";		
		JLabel leb_3 = new JLabel(lebleText, SwingConstants.LEADING);
		leb_3.setVerticalAlignment(SwingConstants.TOP);
		leb_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		leb_3.setBackground(new Color(245, 222, 179));
		leb_3.setForeground(Color.RED);
		leb_3.setBounds(70, spaceY, 650, 22);spaceY+=30;
		layP[page].add(leb_3);
		
//----------지문 Start------------------------------------------------------------------

		ArrayList<TextVO> textList; 	
		code.setExamCode(examCode);						// 문제코드 code에 저장		
		textList = new TextDAO().Select(code);
		chckbx = new JCheckBox[textList.size()];		// 지문 체크박스 배열 크기
		
		int chckbxH = 28;	// 지문 체크박스 1개당 높이 설정		
		int len = 48;		// 지문 글자 수 자르기
		int lenLine = 1;		
		i = 0;
		ButtonGroup buttonG = new ButtonGroup();		// 체크된 내용이 1개만 선택되도록 그룹화
		
		for(TextVO vo : textList) {
			
			lenLine = new TextLength().run(vo.toString(), len);	//글자수에 따른 간격 조정

			chckbxVal = new JCheckBox();
			chckbx[i] = chckbxVal;
			chckbx[i].setBounds(60, spaceY, 580, (chckbxH*lenLine));spaceY+=(lenLine*25);			
			chckbx[i].setText("<html><body>"+(i+1)+". "+vo.toString()+"</body></html>");
			chckbx[i].setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			chckbx[i].setFocusable(false);
			
			//--- 학생이 체크한 번호로 선택부분 체크해주기
			if(stuAnswer==(i+1)) chckbx[i].setSelected(true);	// 오답 부분에 체크해주기 ?? 또는 레이블로 표기
			//------------------------------------------------------
			chckbx[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String chkNumText = e.getActionCommand();
					int chkCode = Integer.parseInt(chkNumText.substring(12, 13));
					
					JOptionPane.showMessageDialog(null, chkCode+" 번이 정답일까요?? ");
					
				}
			});			
			
			buttonG.add(chckbx[i]);	
			
		layP[page].add(chckbx[i]);	
		i++;
		}	//end for			

//----------하단버튼 Start-------------------------------------------------------------------

		if(page < (listSize - 1)) {	// 오답문항에 대한 페이지
			JButton btn_1 = new JButton("다 음");
			btn_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layP[page].setVisible(false);
					int page2 = page;	// 현재 페이지 재저장
					page2++;					
					
					run(page2);
				}				
			});			
			btn_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			btn_1.setHorizontalAlignment(SwingConstants.CENTER);
			btn_1.setVerticalAlignment(SwingConstants.CENTER);
			btn_1.setFocusable(false);
			btn_1.setBounds(butY, butH, 95, 35);
			layP[page].add(btn_1);
		}else butY -= 100;
			//end if
		
		JButton btn_2 = new JButton("종 료");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(page);
				setVisible(false);
			}			
		});		
		btn_2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btn_2.setHorizontalAlignment(SwingConstants.CENTER);
		btn_2.setVerticalAlignment(SwingConstants.CENTER);
		btn_2.setFocusable(false);
		btn_2.setBounds(butY+100, butH, 95, 35);
		layP[page].add(btn_2);
		
//----------하단버튼 End-------------------------------------------------------------------			
		
		setVisible(true);
		
	}	//end 오답페이지
}
