
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


		//메뉴바 만들기
		JMenuBar menuBar = new JMenuBar();

		// 파일 메뉴 만들기
		JMenu fileMenu = new JMenu("파일");

		// 파일 메뉴 안에서 동작할 것들
		JMenuItem newItem = new JMenuItem("새 일기 만들기");
		JMenuItem openItem = new JMenuItem("기존 일기 열기");
		JMenuItem saveItem = new JMenuItem("일기 저장하기");
		JMenuItem saveasItem = new JMenuItem("다른 일기로 저장하기");
		JMenuItem closeItem = new JMenuItem("닫기");

		frame.setJMenuBar(menuBar);

		menuBar.add(fileMenu); //파일 메뉴

		fileMenu.add(newItem);
		fileMenu.addSeparator();
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(saveasItem);
		fileMenu.addSeparator();
		fileMenu.add(closeItem);

		// 메뉴마다 구분선도 추가함.
		
		//
		//
		// 파트 별 구분선
		FileDialog mSave = new FileDialog(frame, "일기 저장하기",FileDialog.SAVE);
		FileDialog mOpen = new FileDialog(frame,"기존 일기 열기", FileDialog.LOAD);

		newItem.addActionListener(new ActionListener() { // 새 일기 만들기
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.setText(content);
				frame.setTitle("소박한 다이어리");
				flag =0;
				frame.setSize(600,810);
			}
		});

		openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mOpen.setVisible(true);
				String data = mOpen.getDirectory() + mOpen.getFile();
			}
		});










		frame.pack(); // 화면에 맞게 프레임 조절
		frame.setVisible(true); //frame 보이게 설정
	}
	static String content = ""; //처음에는 아무것도 없도록 페이지 초기화
	static int flag =0; // 일기장 저장이랑 다른 일기로 저장에 이용
}
