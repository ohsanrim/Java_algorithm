package cookierunrun;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*

*/
class MyFrame2 extends JFrame implements KeyListener,Runnable{
	
	//일시정지 필드
	public static boolean gameStop=false;
	//시작 화면 
	public JButton startBtn;
	public Image round1;
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
	public Pause pause;
	//게임 시작 필드
	public boolean startGame=false;
	
	// 게임 종료 필드
	public static boolean gameDie=false;
	//게임 스코어 필드
	public static int gameScore=0;
	public JLayeredPane lp;
	
	
	public MyFrame2() {
		lp = new JLayeredPane();
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
		
		endGame = new EndGame();
		endGame.setLayout(null);
		endGame.setBounds(0,0,700,500);
		
		
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
				if(!runningCookie.jump) {  //점프 중이 아닐 떄
				runningCookie.setSlide(true);
				//play("src/ch42slide.wav");
				}
			} else if(e.getKeyCode()==e.VK_UP) {
				if(!runningCookie.slide) {  //슬라이드 중이 아닐 떄
				runningCookie.setJump(true);
				play("src/jump2.wav");
				}
			} else if(e.getKeyCode()==e.VK_ENTER) {
				if(startGame==false) {
					//allThreadStart();
					gameStart();
				}
			} else if(e.getKeyCode()==e.VK_ESCAPE) {
				System.out.println("일시정지 클릭");
				gameStop=true;
				addPause();
				//add()pause
			}
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
			while(true) {
				//System.out.println(gameDie);
				if(gameDie) {
					System.out.println("게임 종료");
					break;
				}
			}
			gameEnd();
		}
		public void allThreadStart(){
			backgroundT.threadStart();
			hpBar.threadStart();
			runningCookie.threadStart();
			movingHurdle.threadStart();
			gomJellyDummy.threadStart();
			jelly.threadStart();
			jellyDrop.ThreadStart();
			bigGom.threadStart();
		}
		public void play(String fileName) {
			try {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
	            Clip clip = AudioSystem.getClip();
	            clip.stop();
	            clip.open(ais);
	            clip.start();
	        } catch (Exception e){
	        }
		}
		//게임이 종료되면 나오는  frame창
		public void gameEnd() {
			this.setVisible(false);
			endGame = new EndGame();
			endGame.setLayout(null);
			endGame.setBounds(200,300,700,500);
			endGame.setVisible(true);
			
		}
		public void addPause() {
			pause = new Pause();
			pause.setLayout(null);
			pause.setBounds(0,0,700,500);
			pause.setVisible(true);
			bigGom.add(pause);
		}
}

//일시정지를 눌렀을 떄 나올 페널
class Pause extends JPanel implements ActionListener{
	public Image pause;
	public JButton pauseBtn;
	public JButton exit;
	public Pause() {
		setOpaque(false);
		pauseBtn = new JButton(new ImageIcon("src/continue.png"));
		pauseBtn.setBounds(180,350,135,50);
		add(pauseBtn);
		exit = new JButton(new ImageIcon("src/exit.png"));
		exit.setBounds(400,350,135,50);
		add(exit);
		pauseBtn.addActionListener(this);
		exit.addActionListener(this);
	}
	public void paintComponent(Graphics g) {
		// 이미지 생성
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit t = Toolkit.getDefaultToolkit();
		pause = t.getImage("src/pause.png");
		g.drawImage(pause,0,0,this.getWidth(), this.getHeight()-20,this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pauseBtn) {
			//게임 다시 시작
			System.out.println("continue 클릭");
			MyFrame2.gameStop=false;
			System.out.println(MyFrame2.gameStop);
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
}