
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.border.Border;



public class Main {
	public static void main(String[] args) {
		
		//Frame 설정 및 추가
		//Frame 생성 제목표시줄에 다이어리 표시하기 
		JFrame frame = new JFrame("소박한 다이어리");
		
		//창 닫으면 프로그램 종료!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//패널 설정 및 추가
		//패널 객체 생성 (텍스트)
		JPanel textPanel = new JPanel();
		JPanel alarmPanel = new JPanel();

		alarmPanel.setToolTipText("기타 사용을 위해 이용할 부분");

		//위 아래 패널의 영역 확인 위해 패널에 배경색 줌.
		textPanel.setBackground(Color.gray);
		alarmPanel.setBackground(Color.pink);

		frame.getContentPane().add(textPanel, "North");
		frame.getContentPane().add(alarmPanel, "South");

		//TextArea에 컨텐츠가 가리키는 문자열 표시 및 행열 설정
		JTextArea ta = new JTextArea(content, 40,45);

		//행 넘기기 true
		ta.setLineWrap(true);
		//행 넘길 때 행의 마지막 단어가 두행에 걸쳐 나뉘지 않도록 하기
		ta.setWrapStyleWord(true);

		//툴팁제작하기
		ta.setToolTipText("이곳에 텍스트를 입력해요..!");

		//textArea 테두리 색 검정, 두께 3
		Border lineBorder = BorderFactory.createLineBorder(Color.pink, 10);

		//텍스트와 textArea 경계 사이에 여백을 두기 위해서 emptyBorder 생성
		Border emptyBorder = BorderFactory.createEmptyBorder(2,2,7,7);

		//textArea에 검정테두리와 여백으로 구성된 경계선 설정
		ta.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

		//textArea에 스크롤 펑션 추가 및 Panel에 삽입
		textPanel.add(new JScrollPane(ta));



		frame.setVisible(true); //frame 보이게 설정
	}
	static String content = "유리야 사랑해 고마"; //처음에는 아무것도 없도록 페이지 초기화  
}
