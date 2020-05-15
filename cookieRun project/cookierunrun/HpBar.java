package cookierunrun;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
/*
체력바를 화면에 올려주는 JPanel 클래스. 총 체력은 100으로, 2초에 1씩 체력이 소모가 되고, 
만약 장애물이 부딛혔을 떄는 체력이 15가 닳는다. 
+ 화면 상단 위 곰젤리 획득 개수와 코인획득개수, 스코어가 함께 뜬다. 
*/

public class HpBar extends JPanel implements Runnable {
	//---------------------필드-----------------------
	// 체력 설정 필드
	public Image HpBar;
	public Image HbBar;
	public int second = 0;
	// 곰젤리 먹은 개수 카운팅
	public Image gomCountBackground; // 배경에 먹은 곰 젤리의 갯수를 세어줄 이미지 넣기
	public JLabel gomCountL;
	//먹은 코인 개수 카운팅
	public Image coinCountBackground;
	public JLabel coinCountL;
	//전체 스코어 점수 카운팅
	public Image scoreBackground;
	public JLabel scoreL;
	public DecimalFormat df = new DecimalFormat("#,##0");
	
	// 랭킹 이미지
	public Image number1, number2, rankBar;
	public JLabel competitionL;
	
//게임종료 클래스 호출
	public EndGame endGame;
	//---------------------메소드-----------------------------------
	public HpBar() {
		this.setLayout(null);
		// 화면 상단 위 스코어 설정
		gomCountL = new JLabel("0");
		coinCountL = new JLabel("0");
		scoreL = new JLabel("0");
		competitionL= new JLabel("0");
		gomCountL.setFont(new Font("Segoe UI Black", Font.BOLD, 25)); // 글씨체 수정 요망
		coinCountL.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		scoreL.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		competitionL.setFont(new Font("Segoe UI Black", Font.BOLD,15));
		gomCountL.setForeground(Color.WHITE);
		coinCountL.setForeground(Color.WHITE);
		scoreL.setForeground(Color.WHITE);
		competitionL.setForeground(Color.white);
		gomCountL.setBounds(630, 18, 100, 50);
		coinCountL.setBounds(630, 63, 100, 50);
		scoreL.setBounds(330, 60, 200, 50);
		competitionL.setBounds(50,100,200,50);
		add(scoreL);
		add(gomCountL);
		add(coinCountL);
		add(competitionL);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(700, 460);
		setOpaque(false);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit t = Toolkit.getDefaultToolkit();
		
		// 랭킹 이미지
		number1 = t.getImage("C:\\cookierun\\png\\rank.png");
		number2=t.getImage("C:\\cookierun\\png\\2.png");
		rankBar=t.getImage("C:\\cookierun\\png\\rank1.png");
		
		// 화면 상단 위젯 이미지 불러오기
		gomCountBackground = t.getImage("C:\\cookierun\\png\\Gom_jelly4.png");
		coinCountBackground = t.getImage("C:\\cookierun\\png\\Coin.png");
		scoreBackground = t.getImage("C:\\cookierun\\png\\jelly.png");
		//number1 = t.getImage("C:\\cookierun\\png\\number1.png");
		
		// 체력 게이지 만들기
		HpBar = t.getImage("C:\\cookierun\\png\\HpBar_wh.png");
		HbBar = t.getImage("C:\\cookierun\\png\\HbBark.gif");
		
		//만약 죽지 않았을 떄(체력이 0 이상일 때)
		
		g.drawImage(HbBar, 130, 38, (MovingHurdle.health * 4), 20, this);
		g.drawImage(HpBar, 90, 20, 450, 50, this);
		g.drawImage(number1,5, 5, 100, 100, this);
		g.drawImage(rankBar, 10,30,10,100,this);
		
		//죽었을 때(체력이 0보다 작을 때)
		if (MovingHurdle.health < 0) {
			MyFrame.gameDie = true;
		}
		
		// 랭킹 이미지 넣기 - 노트북에서는 실행이 안되서 일단 주석 처리
		//g.drawImage(number1, 300, 300, 500, 500, this);
		//g.drawImage(number2, 570, 30, 35, 30, this);
		
		// 상단 위에 곰젤리&코인을 카운팅하는 위젯 넣기
		g.drawImage(gomCountBackground, 570, 30, 35, 30, this);
		gomCountL.setText(Integer.toString(GomJellyDummy.countGom));
		
		g.drawImage(coinCountBackground, 570, 75, 35, 30, this);
		coinCountL.setText(Integer.toString(Jelly.coinEat));
		
		g.drawImage(scoreBackground, 300, 75, 20, 20, this);
		scoreL.setText(df.format(Jelly.gameScore));
		
		competitionL.setText(MyFrame.competitionScore);
		
		
	}
	//스레드를 시작해주는 메소드
	public void threadStart() {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (!MyFrame.gameDie) {
			MovingHurdle.health -= 4; // 2초에 1퍼센트 씩 체력 소모
			try {
				Thread.sleep(2000); // 1초에 1씩 닳게 하고 싶으면 1000으로 교환해주기
			} catch (InterruptedException e) {
				return;
			}
			repaint();
			if (MyFrame.gameDie) break;
		}
	}
}
