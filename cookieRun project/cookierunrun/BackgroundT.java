package cookierunrun;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;
/*
배경만 스레드로 움직여 주는 클래스. 
*/
class BackgroundT extends JPanel implements Runnable{

	public int x1=0;
	public int x2=700;
	public Image backgroundImg;
	public Image backgroundImg2;
	
	public BackgroundT() {
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(700,460);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit t = Toolkit.getDefaultToolkit();
		//1라운드 배경이미지 설정
		backgroundImg = t.getImage("src/IMAGE/firstRound_1.JPG");
		backgroundImg2 = t.getImage("src/IMAGE/firstRound_1.JPG");
		
		
		g.drawImage(backgroundImg,x1,0,this.getWidth()+10, this.getHeight(), this);
		//g.drawImage(backgroundImg,x1,0,null);
		g.drawImage(backgroundImg2,x2,0,this.getWidth()+10, this.getHeight(), this);
		//g.drawImage(backgroundImg2,x2,0,null);
				
	}	
	public void threadStart() {
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		while(true) {
			x1--;
			x2--;
			if(x1<=-700) {
				x1=700;
			} 
			if(x2<=-700) {
				x2=700;
			}
			repaint();
			try{
				Thread.sleep(5);
			} catch(InterruptedException e){
			}
		}
	}
}
