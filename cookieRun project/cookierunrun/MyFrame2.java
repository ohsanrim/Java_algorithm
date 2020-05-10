package cookierunrun;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
/*

*/
class MyFrame2 extends JFrame implements KeyListener,Runnable{
	//시작 화면 
	
	public JButton startBtn;
	public Image round1;
	public JLayeredPane lp;
	public Image startImg;
	public JPanel p;
	public BackgroundT backgroundT;
	public ImageIcon backgroundImg;
	//클래스 호출 필드
	public RunningCookie runningCookie;
	public MovingHurdle movingHurdle;
	public HpBar hpBar;
	public GomJellyDummy gomJellyDummy;
	public Jelly jelly;
	public JellyDrop jellyDrop;
	public EndGame endGame;
	public BigGom bigGom;
	//게임 시작 필드
	public boolean startGame=false;
	
	// 게임 종료 필드
	public static boolean gameDie=false;
	//게임 스코어 필드
	public static int gameScore=0;
	
	
	public MyFrame2() {
		
		//jellyDrop클래스 호출
		jellyDrop = new JellyDrop();
		// 곰젤리 클래스 호출
		gomJellyDummy = new GomJellyDummy();
		gomJellyDummy.setLayout(null);
		gomJellyDummy.setBounds(0,0,700,500); 
		gomJellyDummy.setting();
		// 일반 젤리 클래스 호출
		jelly= new Jelly();
		jelly.setLayout(null);
		jelly.setBounds(0,0,700,500); 
		jelly.setting();
		//장애물 클래스 호출
		movingHurdle = new MovingHurdle();
		movingHurdle.setting();
		movingHurdle.setBounds(0,0,700,500);
		movingHurdle.setLayout(null);
		//배경 클래스 호출
		backgroundT = new BackgroundT();
		backgroundT.setLayout(null);
		//쿠키가 달리는 클래스 호출
		runningCookie = new RunningCookie();
		runningCookie.setLayout(null);
		runningCookie.setBounds(0,0,700,500); 
		//체력 게이지 관리 클래스 호출
		hpBar = new HpBar();
		hpBar.setLayout(null);
		hpBar.setBounds(0,0,700,500); 
		
		//큰 곰젤리 호출
		bigGom = new BigGom();
		bigGom.setLayout(null);
		bigGom.setBounds(0,0,700,500); 
		bigGom.setting();
		
		//게임종료 클래스 호출
		/*
		endGame = new EndGame();
		endGame.setLayout(null);
		endGame.setBounds(0,0,700,500);
		*/
		
		// 프레임 위에 올리기 
		//endGame.add(p);
		runningCookie.add(bigGom);
		hpBar.add(runningCookie);
		movingHurdle.add(hpBar);
		jelly.add(movingHurdle);
		gomJellyDummy.add(jelly);
		backgroundT.add(gomJellyDummy);
		add(backgroundT);
		//장애물 리스트 받기
		//endGame.setVisible(false);
		//
		
		setBounds(200,300,700,500);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//startBtn.setFocusable(false);
		this.setResizable(true);
		this.addKeyListener(this);
	}
	public static void main(String[] args) {
		new MyFrame2();
	}
	//게임을 시작하는 메소드
	public void gameStart() {
		startGame=true;
		this.requestFocus();
		runningCookie.setFocusable(true);
		Thread t = new Thread(this);
		t.start();
	}
	//KeyListener 오버라이드
		@Override
		public void keyPressed(KeyEvent e) {
			//눌렀을 떄
			if(e.getKeyCode()==e.VK_DOWN) {
				if(!runningCookie.jump)  //슬라이드 중이 아닐 떄
				runningCookie.setSlide(true);
			} else if(e.getKeyCode()==e.VK_UP) {
				if(!runningCookie.slide)  //슬라이드 중이 아닐 떄
				runningCookie.setJump(true);
			} else if(e.getKeyCode()==e.VK_ENTER) {
				if(startGame==false) {
					//allThreadStart();
					gameStart();
				}
			}
			/*
			if(gameDie) {
				//종료시 나올 패널 넣기
				backgroundT.setVisible(false);
				hpBar.setVisible(false);
				runningCookie.setVisible(false);
				movingHurdle.setVisible(false);
				gomJellyDummy.setVisible(false);
				jelly.setVisible(false);
				endGame.setVisible(true);
			}
			*/
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			runningCookie.setSlide(false);
		}
		@Override
		public void keyTyped(KeyEvent e) {} 
		@Override
		public void run() {
			allThreadStart();
			while(!gameDie) {
				if(gameDie) break;
			}
			//this.remove();
		}
		public void allThreadStart() {
			backgroundT.threadStart();
			hpBar.threadStart();
			runningCookie.threadStart();
			movingHurdle.threadStart();
			gomJellyDummy.threadStart();
			jelly.threadStart();
			jellyDrop.ThreadStart();
			bigGom.threadStart();
		}
	

}