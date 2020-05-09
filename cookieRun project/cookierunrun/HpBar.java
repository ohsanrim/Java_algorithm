package cookierunrun;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class HpBar extends JPanel implements Runnable{
	//체력 설정 필드
	public Image HpBar;
	public Image HbBar;
	public int second=0;
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(700, 460);
		setOpaque(false);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit t = Toolkit.getDefaultToolkit();
		// 체력 게이지 만들기 
		
		HpBar = t.getImage("src/IMAGE/HpBar_wh.png");
		HbBar = t.getImage("src/IMAGE/HbBark.gif");
		g.drawImage(HbBar, 70, 48, (MovingHurdle.health*4),20, this);
		g.drawImage(HpBar, 30, 30, 450,50, this);
	} 
	public void threadStart() {
		Thread t = new Thread(this);
		t.start();
	}
	public void run() {
		while(true) {
			MovingHurdle.health--;  // 1초에 1퍼센트 씩 체력 소모
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			repaint();
		}
		
	}
}
