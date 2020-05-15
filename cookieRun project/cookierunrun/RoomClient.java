package cookierunrun;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class RoomClient extends JFrame implements ActionListener, Runnable {
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;

	private Container c;
	private JButton rivalReadyBtn, readyBtn, sendBtn;
	private JPanel panel, panel2, panel2_1, panel3, panel4, panel4_1, panel5;
	private Panel3_1 panel3_1;
	private JLabel user1, user2;
	private JTextArea output;
	private JTextField input;
	private JButton outBtn;
	public static boolean enterClient = false;
	private int sw = 0;
	private Clip clip;
	private int enterCount = 0; // 현재 대기실에 입장한 사람
	private String nickName;
	private ArrayList<String> joinUser = new ArrayList<String>();
	//레디 여부
	public boolean meReady = false;
	public boolean rivalReady = false;

	public RoomClient(Clip clip) {
		super("대기화면");
		this.clip = clip;
		nickName = LobbyClient.nickName;

		Image img = new ImageIcon().getImage();
		panel = new JPanel() {
			ImageIcon icon = new ImageIcon("C:\\cookierun\\png\\fruit.png");

			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setLayout(null);
		getContentPane().add(panel);

		panel2 = new JPanel() {
			ImageIcon icon = new ImageIcon("C:\\cookierun\\png\\id_form.png");

			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel2.setLayout(null);
		panel2.setBounds(30, 50, 200, 400);
		panel.add(panel2);
		panel2_1 = new JPanel() {
			public void paintComponent(Graphics g) {
				Image image = Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\cookie_man.gif");
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel2_1.setLayout(null);
		user1 = new JLabel(nickName);
		user1.setBounds(85, 15, 65, 65);
		user1.setForeground(Color.WHITE);
		panel2.add(user1);

		rivalReadyBtn = new JButton(new ImageIcon("C:\\cookierun\\png\\ready_2.png"));
		rivalReadyBtn.setBounds(30, 280, 140, 60);
		rivalReadyBtn.setEnabled(false);
		//

		panel2_1.setBounds(20, 85, 170, 170);
		panel2.add(panel2_1);

		panel3 = new JPanel() {
			// private static final long serialVersionUID = 1L;

			ImageIcon icon = new ImageIcon("C:\\cookierun\\png\\id_form.png");

			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel3.setLayout(null);
		panel3.setBounds(470, 50, 200, 400);
		panel3_1 = new Panel3_1();

		/*
		 * panel3_1 = new JPanel() { public void paintComponent(Graphics g) {
		 * System.out.println(enterClient); if(enterClient) { Image image =
		 * Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\cookie_girl.gif");
		 * g.drawImage(image, 0, 0, getWidth(), getHeight(), this); } setOpaque(false);
		 * super.paintComponent(g); } }; panel3_1.setBounds(20, 85, 170, 170);
		 * panel3_1.setLayout(null);
		 */
		panel3.add(panel3_1);
		user2 = new JLabel("사용자2");
		user2.setBounds(85, 15, 65, 65);
		user2.setForeground(Color.WHITE);
		panel3.add(user2);
		readyBtn = new JButton(new ImageIcon("C:\\cookierun\\png\\ready_2.png"));
		readyBtn.setBounds(30, 280, 140, 60);
		panel3.add(rivalReadyBtn);
		panel.add(panel3);

		panel2.add(readyBtn);

		panel4 = new JPanel(new BorderLayout());
		output = new JTextArea();
		// output.setFont(new Font("새굴림", Font.BOLD, 20));
		output.setBackground(new Color(255, 188, 121));
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel4.add("Center", scroll);

		panel4_1 = new JPanel();
		panel4_1.setLayout(new BorderLayout());

		input = new JTextField();
		sendBtn = new JButton("보내기");

		panel4_1.add("Center", input);
		panel4_1.add("East", sendBtn);

		panel4.add("South", panel4_1);

		panel4.setBounds(265, 155, 180, 240);
		panel.add(panel4);

		panel5 = new JPanel() {
			ImageIcon icon = new ImageIcon("C:\\cookierun\\png\\cookieRun.png");

			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel5.setLayout(null);
		panel5.setBounds(255, 15, 200, 100);

		panel.add(panel5);

		outBtn = new JButton(new ImageIcon("C:\\cookierun\\png\\out.png"));
		outBtn.setBounds(550, 8, 130, 45);
		panel.add(outBtn);

		setBounds(200, 100, 700, 500);
		setResizable(false);
		setVisible(true);
		outBtn.addActionListener(this);
		rivalReadyBtn.addActionListener(this);
		readyBtn.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// System.out.println("엑스누르면 들어 오나?");
				writer.println("exit");
				writer.flush();
				System.out.println("대기방 엑스버튼");
			}
		});
	}

	public void service() {
		// enterClient = true;
		repaint();
		// String serverIP = "192.168.147.4";
		//String serverIP = "192.168.0.153"; 
		String serverIP = "172.30.1.28";
		if (serverIP == null || serverIP.length() == 0) {
			System.out.println("서버IP가 입력되지 않았습니다.");
			System.exit(0);
		}

		try {
			// 소켓생성
			socket = new Socket(serverIP, 8888); // 아이피는 위에있는거고 port 는 고정? 꼭 9500써야하나
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

		// 서버로 닉네임 보내기
		writer.println(nickName); // ln 이라는거 자체가 \n있는거니깐 이렇게 쓰는게 좋다.
		writer.flush(); // 버퍼안에 찌꺼기 없애기
		// 스레드 생성
		Thread t = new Thread(this); // 인터페이스로 햇을때는 꼭 this를 해야됨
		// 스레드 시작
		t.start();
		// 이벤트 처리
		sendBtn.addActionListener(this); // 이거는 샌드버튼 눌렀을때 한다
		input.addActionListener(this); // 타자를 치고 엔터를 쳤을때 나타낸다

	} // service생성자

	@Override
	public void run() {
		String line;
		// 서버로 부터 받기
		while (true) {
			try {
				// reader은 서버로부터 읽어오는 과정이다.
				line = reader.readLine(); // line의 기준은 엔터가 기준이다. 엔터 치기전까지의 모든 Line을 읽어 온다
				//입장했을 떄 몇명이 접속했는지 확인
				if (line.contains("입장")) {
					System.out.println(enterCount);
					String[] name = line.split("님");
					joinUser.add(name[0]);
					if (joinUser.size() == 2) { // 두명 다 입장
						System.out.println("2명 다 입장 완료!");
						enterClient = true;
						panel3_1.repaint();
						user2.setText(joinUser.get(1));
					}
				}
				//ready관련 버튼 클릭
				if(line.contains("ready")) {  
					String []temp = line.split(":");
					if(temp.length==2) {
						if(!temp[0].contains(nickName)) {
						if(temp[1].equals("ready")) {
							rivalReady=true;
							if(meReady) { gameStart(); }
						} else {
							rivalReady=false;
						}
					}
					}
				}

				if (line.contains("게임을 시작하시려면 READY를 눌러주세요!")) {
					System.out.println("2명 다 입장 완료!");
					enterClient = true;
					panel3_1.repaint();
				}

				//
				System.out.println("클라이어ㄴ트 2번" + line);
				if (line == null || line.equals("exit")) {
					System.out.println("제발 들어와라");
					// 통신 끊기

					reader.close(); // 읽기 끊기
					writer.close(); // 쓰기 끊기
					socket.close(); // 소켓 끊기
					System.out.println("대기방 소켓끊느거다");
					dispose(); // 창을끄고
					break;
					// setVisible(false);
					// System.exit(0); // 프로그램 종료
				}
				output.append(line + "\n");

				// 스크롤바를 항상 아래로 넣기
				int pos = output.getText().length();
				output.setCaretPosition(pos);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		new LobbyClient().service();
	}

	@Override // Override는 생성자 밖에 해야된다.
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rivalReadyBtn) {
			// setVisible(false);
			dispose();
			clip.close();
			// Loading loadFrame =new Loading();
			new Loading();
		} else if (e.getSource() == readyBtn) {
			if (sw == 0) {
				rivalReadyBtn.setEnabled(true);
				sw++;
				readyBtn.setIcon(new ImageIcon("C:\\cookierun\\png\\ready_1.png"));
				meReady = true;
				writer.println("ready");
				writer.flush();
				if(meReady==true&&rivalReady==true) {
					gameStart();
				}
			} else if (sw == 1) {
				rivalReadyBtn.setEnabled(false);
				sw--;
				readyBtn.setIcon(new ImageIcon("C:\\cookierun\\png\\ready_2.png"));
				meReady=false;
				writer.println("unReady");
				writer.flush();
			}
		} else if (e.getSource() == outBtn) {
			writer.println("exit");
			writer.flush();
		}

		String data = input.getText();
		if (data == null || data.equals(""))
			return;

		writer.println(data);
		writer.flush();
		input.setText("");
	}
	public void gameStart() {
		new MyFrame().gameStart();;
	}
}

class Loading extends JFrame implements Runnable {
	private JProgressBar jpb = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);

	public Loading() {
		super("로딩 화면");

		JPanel panel = new JPanel() {

			public void paintComponent(Graphics g) {
				Image image = Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\load.gif");

				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		add("Center", panel);
		jpb.setStringPainted(true);
		jpb.setString("0%");
		add("South", jpb);

		setBounds(400, 200, 300, 350);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Thread th = new Thread(this);
		th.start();
	}

	public void run() {
		for (int i = 0; i <= 100; i++) {// 여기서 i=0으로 바꾸면 멈춤을 눌렀다가 다시 시작을 눌렀을 때 0%부터 다시 올라감
			try {
				Thread.sleep(60);
			} catch (InterruptedException ee) {
			}
			jpb.setValue(i);
			jpb.setString(i + "%");
		}
		setVisible(false);
		new MyFrame();
	}
	
	
}

class Panel3_1 extends JPanel {
	public Panel3_1() {
		setBounds(20, 85, 170, 170);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (RoomClient.enterClient) {
			Image image = Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\cookie_girl.gif");
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
		setOpaque(false);

	}

}
