package cookierunrun;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PauseEx extends JPanel implements ActionListener{
	//public PauseBack pauseBack;
	public JButton pauseBtn;
	public JButton exit;
	public Image pause;
	//일시정지 프레임 만들기
	public PauseEx() {
		//버튼 생성
		pauseBtn = new JButton(new ImageIcon("src/continue.png"));
		pauseBtn.setBounds(180,350,135,50);
		exit = new JButton(new ImageIcon("src/exit.png"));
		exit.setBounds(400,350,135,50);
		pauseBtn.addActionListener(this);
		exit.addActionListener(this);
		
		//윈도우 설정
		setBounds(200,300,700,500);
		setVisible(true);
		setOpaque(false);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pauseBtn) {
			//게임 다시 시작
		} else if(e.getSource()==exit) {
			//게임 나가기
			System.out.println("exit");
			int answer = JOptionPane.showConfirmDialog(this, "정말로 게임을 나가시겠습니까?(종료시 로비화면으로 이동합니다.)", "confirm", JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION) {
				//new TempWindow();  //로비 화면으로 이동
				MyFrame2.gameDie=true;
				this.setVisible(false);
			}
		}
	}
	public void paintComponent(Graphics g) {
		// 이미지 생성
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit t = Toolkit.getDefaultToolkit();
		pause = t.getImage("src/pause.png");
		g.drawImage(pause,0,0,this.getWidth(), this.getHeight()-20,this);
	}
	
	public static void main(String[] args) {
		new Pause();
	}
}
/*
class PauseBack extends JPanel {
	public Image pause;
	public void paintComponent(Graphics g) {
		// 이미지 생성
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit t = Toolkit.getDefaultToolkit();
		pause = t.getImage("src/pause.png");
		g.drawImage(pause,0,0,this.getWidth(), this.getHeight()-20,this);
	}
}
*/