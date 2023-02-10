package Project;
//공모전 Project
//2022.09.27~ 2022.10.14
//안세련

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import DataList.CurrentTime;

//전체 판넬 크기 가로 : 900 ,세로 : 800
//layered 크기 가로 : 860 ,세로 : 700

@SuppressWarnings("serial")
public class GuiIntro extends JFrame{
	JTextPane textP;
	public GuiIntro() {
		setTitle("CBT TEST");	// 타이틀
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		//setLocation(500, 100);
		setLocationRelativeTo(null);	//화면창 중앙
		JPanel contentPane = new JPanel();		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// 현재 시스템 시간 하단에 고정
		JLabel leb_2 = new JLabel(new CurrentTime().run());
		leb_2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		leb_2.setBounds(715, 711, 146, 26);
		contentPane.add(leb_2);			

		//-------- GUI 클래스 페이지 ------------//		
		//GuiTest(true);		
		//GuiDayTest1(true,page);
		//GuiResult1(true);
		//new GuiIncorrectAnswer(rvo);
	}
//-------디지털 시간 실시간 구현 못함---------------------------------------
	public void Clock(boolean sw) {
			String time = new CurrentTime().time();
			textP = new JTextPane();
			textP.setBackground(Color.WHITE);
			textP.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			textP.setForeground(Color.GRAY);
			textP.setFocusable(false);
			textP.setBounds(600, 56, 200, 50);
			getContentPane().add(textP);
			textP.setText("Time : "+time);			
				try { 					
					Thread.sleep(100);
					textP.repaint();
			    } catch(Exception e) {}
			
			textP.setVisible(sw);			
	}	
}

class GuiMain  {

	public static void main(String[] args) {	
		new GuiTest().run(true);
	}
}