package cookierunrun;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
/*
배경만 스레드로 움직여 주는 클래스. 60초가 지나면 2라운드로 넘어간다. 
*/
class BackgroundT extends JPanel implements Runnable{

	public int x1=0;
	public int x2=700;
	//1라운드 배경 이미지
	public Image backgroundImg_Round1;
	public Image backgroundImg2_Round1;
	//2라운드 배경 이미지
	public Image background1;
	
	public static boolean round2=false;
	//스레드 시작 시간 측정
	public Long startTime; // 스레드 시작 시간
	public Long endTime; // 스레드 현재 시간
	public int gamingTime; // 스레드 실행 시간
	//곰젤리 먹은 개수 카운팅
	//public Image gomCountBackground;  //배경에 먹은 곰 젤리의 갯수를 세어줄 이미지 넣기
	//public JLabel label;
	
	public BackgroundT() {
		/*
		this.setLayout(null);
		label = new JLabel("0");
		label.setFont(new Font("나눔고딕",Font.BOLD,25));
		label.setForeground(Color.WHITE);
		label.setBounds(610,18,100,50);
		add(label);
		*/
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(700,460);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit t = Toolkit.getDefaultToolkit();
		
		//1라운드 배경이미지 설정
		backgroundImg_Round1 = t.getImage("src/IMAGE/firstRound_1.JPG");
		backgroundImg2_Round1 = t.getImage("src/IMAGE/firstRound_1.JPG");
		//2라운드 배경 이미지
		background1 = t.getImage("src/IMAGE/blackghostback.png");
		//gomCountBackground = t.getImage("src/Jelly/Gom_jelly4.png");
		
		//게임이 시작된 지 60초가 지났을 때 배경 체인지
		if(gamingTime==60&&round2==false) round2=true;
		g.drawImage(round2?background1:backgroundImg_Round1,x1,0,this.getWidth()+10, this.getHeight(), this);
		g.drawImage(round2?background1:backgroundImg_Round1,x2,0,this.getWidth()+10, this.getHeight(), this);
		//g.drawImage(gomCountBackground,570,30,35,30,this);
		//label.setText(Integer.toString(GomJellyDummy.countGom));
	}	
	public void threadStart() {
		Thread t = new Thread(this);
		startTime = System.currentTimeMillis();
		t.start();
	}
	@Override
	public void run() {
		while(true) {
			// 시간을 측정해서 25초 이후엔 자동으로 2라운드 진입
			endTime = System.currentTimeMillis();
			gamingTime = (int) (endTime - startTime) / 1000;
			//배경이미지 좌표 이동
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
				Thread.sleep(3);
			} catch(InterruptedException e){
			}
			if(MyFrame2.gameDie==true) break;
		}
	}
}
