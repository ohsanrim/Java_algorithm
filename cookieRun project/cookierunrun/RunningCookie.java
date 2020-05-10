package cookierunrun;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

public class RunningCookie extends JPanel implements Runnable{
	public static final int COOKIE_X=80;  //쿠키의 x좌표는 항상 고정
	public Image cookieRun1, cookieRun2,cookieRun3,cookieRun4,cookieRun5,cookieRun6,cookieRun7,cookieRun8,cookieRun9,cookieRun10;
	public int choice=0;
	public boolean slide=false;
	public boolean jump=false;
	public static boolean coll=false;
	public int jumpY=250;
	public boolean jumpUp=true;  //true 값일 댄 jump진행, false일 떈 하강
	public MovingHurdle movingHurdle;
	public HurdleDTO hurdleDTO;
	public static int cookieY;
	public ArrayList <HurdleDTO> list;
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(700,460);
		setOpaque(false);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit t = Toolkit.getDefaultToolkit();
		// 이미지 불러오기
		cookieRun1 = t.getImage("src/char/general_1.gif");
		cookieRun2 = t.getImage("src/char/general_2.gif");
		cookieRun3 = t.getImage("src/char/general_3.gif");
		cookieRun4 = t.getImage("src/char/general_4.gif");
		cookieRun5 = t.getImage("src/char/slide0.png");
		cookieRun6 = t.getImage("src/char/slide1.png");
		cookieRun7 = t.getImage("src/char/jump0.png");
		
		cookieRun8 = t.getImage("src/char/CHar_Hit.png");
		// 사진 전환하기
		if(choice==0) g.drawImage(cookieRun1,80,250, this);
		else if(choice==1) { g.drawImage(cookieRun2,80,250, this); cookieY=250;}
		else if(choice==2) {g.drawImage(cookieRun3,80,250, this);cookieY=250;}  
		else if(choice==3) { g.drawImage(cookieRun4,80,250, this);cookieY=250;}
		else if(choice==4) {g.drawImage(cookieRun5, 80,310,120,70,this);cookieY=310;}
		else if(choice==5) {g.drawImage(cookieRun6, 80,310,120,70,this); cookieY=310;}
		else if(choice==6) {g.drawImage(cookieRun7, 80,jumpY,100,110, this);cookieY=jumpY ;} 
		else if(choice==7) {g.drawImage(cookieRun8, 80,cookieY,this);}
		
		//this.setFocusable(true);
		
	}  
	public void threadStart() {
		Thread t = new Thread(this);
		t.start();
		//startTime = System.currentTimeMillis();
	}
	//runnabble 오버라이드
	@Override
	public void run() {
		
		while(true) {
			//list=movingHurdle.getHurdleList();
			//collision();
				if(coll==true) {
					choice=7;
			 	}else if(slide==false&&jump==false&coll==false){ 
				choice++;
				if (choice>=4) {
					choice=0;
				}
				try{
					Thread.sleep(35);
				} catch(InterruptedException e){
				} 	
			}else if(slide==true&&jump==false&&coll==false) {
				if(choice==4) choice=5;
				else if(choice==5) choice=4;
				else choice=4;
				try{
					Thread.sleep(35);
				} catch(InterruptedException e){
				} 	
			} else if(jump==true&&slide==false&&coll==false) { //점프가 클릭되었을 때
				//그림 값 변경
				if(choice<=5) {  //초기 점프사진으로 돌아가기
					choice=6;
				} 
				//좌표값을 변경
				if(jumpUp) {   //쿠키가 점프할 떄
					jumpY-=5;   
					if(jumpY<150) jumpUp=false;
				} else if(!jumpUp) {   //쿠키가 하강할 때
					jumpY+=5;
					if(jumpY>250&&jumpUp==false) {  //지면에 쿠키가 닿을 때
						jumpUp=true;
						jump=false;
					}
				}
				try{
					Thread.sleep(20);
				} catch(InterruptedException e){
				} 	
			}
			repaint();
			if(MyFrame2.gameDie==true) break;
		}
	}
	public void setSlide(boolean slide) {
		this.slide=slide;
	}
	public void setJump(boolean jump) {
		this.jump=jump;
	}
	public void setHurddle() {
		
	}
	public int getCookieY() {
		return cookieY;
	}
	public void setColl(boolean coll) {
		this.coll=coll;
	}
		
		
		
	
}
