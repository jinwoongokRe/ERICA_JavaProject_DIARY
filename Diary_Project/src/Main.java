
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
		JFrame frame = new JFrame("다이어리");
		
		//창 닫으면 프로그램 종료!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//패널 설정 및 추가
		//패널 객체 생성 (텍스트)
		JPanel textPanel = new JPanel();
		JPanel alarmPanel = new JPanel();

		alarmPanel.setToolTipText("기타 사용을 위해 이용할 부분");


		
	}
}
