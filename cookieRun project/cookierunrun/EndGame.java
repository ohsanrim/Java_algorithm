package cookierunrun;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
//게임이 종료되면 나올 패널
public class EndGame extends JPanel {
	//이미지 생성 필드
	public Image scoreBoard;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(700, 460);
		setOpaque(false);
		Graphics2D g2d = (Graphics2D) g;
		this.setLayout(null);;
		// 이미지 생성
		Toolkit t = Toolkit.getDefaultToolkit();
		scoreBoard = t.getImage("src/IMAGE/Gameresult.jpg");
		g.drawImage(scoreBoard,0,0,this.getWidth(), this.getHeight()-20,this);
		
	}
}
