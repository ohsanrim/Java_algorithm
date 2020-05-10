package cookierunrun;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

/*
 쿠키가 지나가는 길에 JellyDrop으로 젤리들을 올려주면 그 올린 젤리들을 스레드로 연속적으로 옯겨 줌 , 젤리를 먹으면 score가 올라감
 */
public class Jelly extends JPanel implements Runnable{
	//필드 생성
	public Image jelly;
	public Image coin;
	public static ArrayList <JellyDTO> list;
	public int basicX=720;
	public RunningCookie runningCookie;
	//score 필드 생성
	public static int gameScore=0;
	public static int coinEat=0;
	// 기본값 세팅
	public void setting() {
		runningCookie = new RunningCookie();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(700, 460);
		setOpaque(false);
		Graphics2D g2d = (Graphics2D) g;
		//이미지 생성
		Toolkit t = Toolkit.getDefaultToolkit();
		jelly = t.getImage("src/Jelly/jelly.png");
		coin = t.getImage("src/Jelly/coin.png");
		//list안의 젤리를 패널에 그려주기
		try {
		if(list.size()>0) {
			for(JellyDTO data : list) {
				g.drawImage(data.imageIndex==1?jelly:coin,data.getX(),data.getY(),20,20,this);
			}
		}
		} catch(Exception e) {}
		//젤리 위를 지나가면 젤리가 사라지게 만들기 
		int cookieX = runningCookie.COOKIE_X;
		int cookieY = runningCookie.getCookieY();
		if(list.size()>0) {
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getX()>=80&&list.get(i).getX()<=180) {
					if(list.get(i).getY()>=cookieY &&list.get(i).getY()<=cookieY+100) {
						list.get(i).eat=true;
						if(list.get(i).imageIndex==2) coinEat++;  //코인 획득
						gameScore+=95;
					}
				}
			}
		}
		//먹은 젤리를 리스트에서 없애버리기
		try {
			for(JellyDTO data : list) {
				if(data.eat) {
					list.remove(data);
				}
			}
		} catch(Exception e) {
			
		}
		//System.out.println(gameScore);
	}
	public void threadStart() {
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		while(true) {
			try {
				if(list.size()>0) {
					for(JellyDTO data : list) {
						data.x--;
					}
				}
			}catch(Exception e) {}
			
			repaint();   //1초마다 repaint진행함
			try {
				Thread.sleep(3);  //초단위로 진행됨
			} catch (InterruptedException e) {
			}
			if(MyFrame2.gameDie==true) break;
		}
	}
}
class JellyDTO {  //각각의 젤리에 대한 좌표값을 저장
	public int x,y;
	public boolean eat=false;
	public int imageIndex;
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}

