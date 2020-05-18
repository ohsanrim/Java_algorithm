package cookierunrun;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class GameClient extends JFrame implements KeyListener, Runnable {

	// ------------------------필드-----------------------------
	
	public JButton startBtn;
	public Image round1;
	public Image startImg;
	public BackgroundT backgroundT;
	public ImageIcon backgroundImg;
	public RunningCookie runningCookie;
	public MovingHurdle movingHurdle;
	public static HpBar hpBar;
	public GomJellyDummy gomJellyDummy;
	public Jelly jelly;
	public JellyDrop jellyDrop;
	public BigGom bigGom;
	public GameClient myFrame;
	public boolean startGame = false;
	public static boolean rivalGameDie = false; 
	public static boolean gameDie = false;
	public JLayeredPane lp;
	public static String competitionScore = "0";
	public static int rivalScore=0;
	public boolean temp1 = false; 
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private String nickName;
	private LoginDTO loginDTO;
	private GameClient gameClient;
	private Thread t;

	// -------------------------메소드--------------------------------
	public GameClient(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
		nickName=loginDTO.getNickName();
		
		jellyDrop = new JellyDrop();
		// 곰젤리 클래스 호출
		gomJellyDummy = new GomJellyDummy();
		gomJellyDummy.setLayout(null);
		gomJellyDummy.setBounds(0, 0, 700, 500);
		gomJellyDummy.setting();
		// 일반 젤리 클래스 호출
		jelly = new Jelly();
		jelly.setLayout(null);
		jelly.setBounds(0, 0, 700, 500);
		jelly.setting();
		// 장애물 클래스 호출
		movingHurdle = new MovingHurdle();
		movingHurdle.setting();
		movingHurdle.setBounds(0, 0, 700, 500);
		movingHurdle.setLayout(null);
		// 배경 클래스 호출
		backgroundT = new BackgroundT();
		backgroundT.setLayout(null);
		// 쿠키가 달리는 클래스 호출
		runningCookie = new RunningCookie();
		runningCookie.setLayout(null);
		runningCookie.setBounds(0, 0, 700, 500);
		// 체력 게이지 관리 클래스 호출
		hpBar = new HpBar();
		hpBar.setLayout(null);
		hpBar.setBounds(0, 0, 700, 500);

		// 큰 곰젤리 호출
		bigGom = new BigGom();
		bigGom.setLayout(null);
		bigGom.setBounds(0, 0, 700, 500);
		bigGom.setting();

		// 프레임 위에 올리기
		runningCookie.add(bigGom);
		hpBar.add(runningCookie);
		movingHurdle.add(hpBar);
		jelly.add(movingHurdle);
		gomJellyDummy.add(jelly);
		backgroundT.add(gomJellyDummy);
		add(backgroundT);
		// 닉네임 보내기

		setBounds(200, 300, 700, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
		this.setResizable(true);
		this.addKeyListener(this);
		setResizable(false);
	}

	// 클라이언트 서비스
	public void service() {
		//String serverIP = "192.168.0.153";
		//String serverIP = "172.30.1.28";
		//String serverIP = "192.168.147.4";
		String serverIP = "192.168.0.153";
		if (serverIP == null || serverIP.length() == 0) {
			System.out.println("서버IP가 입력되지 않았습니다.");
			System.exit(0);
		}
		try {
			// 소켓생성
			socket = new Socket(serverIP, 1111); // 아이피는 위에있는거고 port 는 고정? 꼭 9500써야하나
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("서버와 연결이 안되었습니다.");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("게임 서버와 연결 완료!!");
		// 서버로 닉네임 보내기
		writer.println(nickName); 
		writer.flush(); 
		gameStart();
		receiveMsg();  
	}

	@Override
	public void run() {
		allThreadStart();
		while(!t.isInterrupted()) {  
			//게임 끝났을 때
			if(!gameDie) {
				writer.println(Jelly.gameScore);
				writer.flush();
			} else {
				if (gameDie && temp1 == false) {
					writer.println("gameDie:"+Jelly.gameScore);
					writer.flush();
					temp1 = true;
					gameEnd();
				}
				if (gameDie && rivalGameDie) {
					writer.println("exit");
					writer.flush();
				}
			}
			
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
		}
	}
	
	public void receiveMsg() {
		String line;
		while(true) {
			try {
				while (true) {
					line = reader.readLine();
					
					if (line == null || line.equals("exit")) {
						reader.close(); // 읽기 끊기
						writer.close(); // 쓰기 끊기
						socket.close(); // 소켓 끊기
						t.interrupt();
						break;
					} 
					if (line.contains("gameDie")) {
						String[] temp = line.split(":");
						if (!temp[0].equals(nickName)) {
							rivalGameDie = true; 
							rivalScore=Integer.parseInt(temp[2]);
						}
					} else {
						String[] temp = line.split(":");
						if (temp.length == 2) {
							if (!temp[0].equals(nickName)) {
								competitionScore = temp[1];
							}
						}
					}
				}
			} catch (IOException e) {
			}
		}
	}

	// 게임을 시작하는 메소드
	public void gameStart() {
		startGame = true;
		this.requestFocus();
		runningCookie.setFocusable(true);
		t = new Thread(this);
		t.start();
	}

	// KeyListener 오버라이드
	@Override
	public void keyPressed(KeyEvent e) {
		// 눌렀을 떄
		if (e.getKeyCode() == e.VK_DOWN) {
			if (!runningCookie.jump) { // 점프 중이 아닐 떄
				runningCookie.setSlide(true);
			}
		} else if (e.getKeyCode() == e.VK_UP) {
			if (!runningCookie.slide) { // 슬라이드 중이 아닐 떄
				runningCookie.setJump(true);
				play("src/jump2.wav");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		runningCookie.setSlide(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void play(String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
			Clip clip = AudioSystem.getClip();
			clip.stop();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
		}
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

	// 게임이 종료되면 나오는 frame창
	public void gameEnd() {
		new EndLoading(loginDTO, this);
	}
}

class EndLoading extends JFrame implements Runnable {
	private String nickName;
	private GameClient game;
	private EndGame endGame;
	private LoginDTO loginDTO;

	public EndLoading(LoginDTO loginDTO, GameClient game) {
		super("로딩 화면");
		//this.nickName = nickName;
		this.game = game;
		this.loginDTO = loginDTO;

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				Image image = Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\EndLoading.gif");
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		add("Center", panel);
		JPanel p = new JPanel();
		JLabel label = new JLabel("상대방의 경기종료를 기다리는 중입니다...");
		p.add(label);
		add("South", p);

		setBounds(400, 200, 300, 350);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );

		Thread th = new Thread(this);
		th.start();
	}

	public void run() {
		while (true) {
			System.out.println(GameClient.rivalGameDie);  //지우시면 게임 실행이 안됩니다ㅜㅜㅜ 지우지 마세요!!!
			if (GameClient.rivalGameDie) {
				game.dispose();
				break;
			}
		}
		gameEnd();
	}

	public void gameEnd() {
		endGame = new EndGame(loginDTO);
		endGame.setLayout(null);
		endGame.setBounds(200, 300, 700, 500);
		endGame.setVisible(true);
		dispose();
	}
}
