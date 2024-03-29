
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.Border;



public class Main {
	public static void main(String[] args) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월 dd일");

		Date time = new Date();

		String timetime = format1.format(time);
		
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
		JMenu dateMenu = new JMenu(timetime);

		// 파일 메뉴 안에서 동작할 것들
		JMenuItem newItem = new JMenuItem("새 일기 만들기");
		JMenuItem openItem = new JMenuItem("기존 일기 열기");
		JMenuItem saveItem = new JMenuItem("일기 저장하기");
		JMenuItem saveasItem = new JMenuItem("다른 일기로 저장하기");
		JMenuItem closeItem = new JMenuItem("닫기");
		JMenuItem dateItem = new JMenuItem(timetime);

		frame.setJMenuBar(menuBar);

		menuBar.add(fileMenu); //파일 메뉴
		menuBar.add(dateMenu); // 마지막 날짜 메뉴버튼 추가 

		fileMenu.add(newItem);
		fileMenu.addSeparator();
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(saveasItem);
		fileMenu.addSeparator();
		fileMenu.add(closeItem);
		fileMenu.add(dateItem);

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

		openItem.addActionListener(new ActionListener() { //기존 일기 열기
			@Override
			public void actionPerformed(ActionEvent e) {
				mOpen.setVisible(true);
				String data = mOpen.getDirectory() + mOpen.getFile();
				try {
					String str = "";
					FileReader fr = new FileReader(data);
					BufferedReader br = new BufferedReader(fr);

					ta.setText(""); //일단 초기화
					while ((str = br.readLine()) != null) {   //	str에 남아둔 내용이 null이 아닐시..

						ta.append(str); //str 내용 추가
						ta.append("\r\n");// readline의 줄바꿈을 도움 
					}
					br.close();
					String Filename = mOpen.getFile();
					frame.setTitle(Filename);
					flag = 1;
				} catch (Exception e1) {

				}
			}
		});

		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(flag ==0)
				{
					mSave.setVisible(true);
					String data = mSave.getDirectory() + mSave.getFile(); //파일의 디렉토리 정보 및 파일명 가져오기

					try{
						FileWriter fw = new FileWriter(data + ".txt"); //txt파일로 저장하게 하기.
						BufferedWriter bw = new BufferedWriter(fw);

						String str = ta.getText();
						for(int i=0; i< str.length(); i++)
						{
							if(str.charAt(i) =='\n')
							{
								System.out.println("find");
								bw.newLine();
							}
							else
								bw.write(str.charAt(i)); //ta에 bw객체에 있는 내용을 넣는다.
						}

						bw.close(); //bufferdwriter 사용했으므로 close해야함.
						String Filename = mSave.getFile() ;//저장할 파일의 이름을 넣음.
						frame.setTitle(Filename + ".txt"); //프레임 명을 파일명으로 바꾸기

					}catch (Exception e1){

					}
					flag =1;
				}
				else if(flag ==1)
				{
					String data = mSave.getDirectory() + mSave.getFile(); //파일 디렉토리 정보랑 파일명 얻기

					try{
						FileWriter fw = new FileWriter(data + ".txt"); //txt파일로 저장하기.
						BufferedWriter bw = new BufferedWriter(fw);

						String str = ta.getText();
						for(int i=0; i<str.length(); i++)
						{
							if(str.charAt(i) =='\n')
							{
								System.out.println("find");
								bw.newLine();
							}
							else
								bw.write(str.charAt(i));  //Textarea에 bw객체에 있는 내용을 넣는다.
						}
						bw.close();
					}catch (Exception e2){

					}
				}

			}
		});

		saveasItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mSave.setVisible(true);
				String data = mSave.getDirectory() + mSave.getFile(); //파일의 디렉토리 정보와 파일명을 얻는다.(일기장 정보)
				try{
					FileWriter fw = new FileWriter(data + ".txt"); //일기를 txt파일로 저장.
					BufferedWriter bw = new BufferedWriter(fw);

					String str = ta.getText();
					for(int i=0; i<str.length(); i++)
					{
						if(str.charAt(i) =='\n')
						{
							System.out.println("find");
							bw.newLine();
						}
						else
							bw.write(str.charAt(i));	//Textarea에 bw객체에 있는 내용을 넣는다.
					}
					bw.close();
					String Filename = mSave.getFile();
					frame.setTitle(Filename + ".txt");
				}catch (Exception e1) {

				}
			}
		});

		closeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});










		frame.pack(); // 화면에 맞게 프레임 조절
		frame.setVisible(true); //frame 보이게 설정
	}


	static String content = ""; //처음에는 아무것도 없도록 페이지 초기화

	static int flag =0; // 일기장 저장이랑 다른 일기로 저장에 이용
}
