package cookierunrun;

//문자열만 주고 받기 위한 채팅 프로그램.

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

class LobbyClient extends JFrame implements ActionListener, Runnable {
	private JButton sendBtn, newListBtn, exitBtn; // 방만들기, 상점, 미니게임, 채팅보내기
	private JButton room1Btn, room2Btn, room3Btn, room4Btn, room5Btn, room6Btn; // 6개의 방 만들기 버튼
	private JLabel rosterL, backGroundL, labelL; // 토탈랭킹 라벨, 나의 점수라벨, 명단라벨
	private JLabel user1L, user2L, user3L, user4L, user5L, user6L;
	private JLabel user7L, user8L, user9L, user10L, user11L, user12L;
	private JTextArea rosterA, chatA;
	private JTextField chatT;
	private AudioInputStream ais;
	private Clip clip;
	private JLabel myInfoNameL, myInfoNameT, myInfoScoreL, myInfoScoreT, myInfoCoinL, myInfoCoinT;
	private DefaultListModel<LoginDTO> model;
	public static int roomMake = 0;
	private RegisterDAO registerDAO = new RegisterDAO();

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;

	private LoginDTO loginDTO;
	private JScrollPane scroll, scroll2;
	private JPanel panel;
	private JList<LoginDTO> list;
	private int roomNum;
	private ArrayList<RoomDTO> roomAr = new ArrayList<RoomDTO>();
	private RoomDTO roomDTO;
	
	
	
	int sleepSec = 3;	

	public LobbyClient(LoginDTO loginDTO) {
		
		// 자동리스트 하기? test임 상의해보자 팀원들과	
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {
			public void run(){
				try {
					JoinList();
					RoomList();
				}catch (Exception e) { 
				}			
			}
		},0,sleepSec, TimeUnit.SECONDS);


		
		this.loginDTO = loginDTO;
		System.out.println("lobbyClient오자마자 닉네임" + loginDTO.getNickName());
		play("C:\\cookierun\\music\\LobbyMusic.wav");
		setLayout(null); // 프레임깨기
		// 버튼 만들기
		// makeRoomBtn = new JButton(new ImageIcon("C:\\cookierun\\png\\방만들기.png"));
		sendBtn = new JButton("보내기");
		exitBtn = new JButton("나가기");
		room1Btn  = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		room2Btn  = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		room3Btn  = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		room4Btn  = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		room5Btn  = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		room6Btn  = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		
       // user1L = new JLabel("운영자");
       // user1L.setBounds(220, 145, 60, 20);
       // user1L.setOpaque(false);
      //  getContentPane().add(user1L);    
        
      //  user2L = new JLabel("");
      //  user2L.setBounds(350, 145, 60, 20);
      //  getContentPane().add(user2L);
    //    user2L.setOpaque(false);
/*
		RoomDAO roomDAO = new RoomDAO();
		roomAr = roomDAO.selectAllRoom();
		System.out.println(roomAr.get(0).getRoomState());
		*/
		
/*
		if(roomAr.get(0).getRoomState()==1) {			
			room1Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		}else if(roomAr.get(0).getRoomState()==2) {
			String user1 = roomAr.get(0).getUser1();
			user1L = new JLabel(user1);
			user1L.setBounds(220, 145, 60, 20);
			user1L.setOpaque(false);
			getContentPane().add(user1L);    
			String user2 = roomAr.get(0).getUser2();   
			user2L = new JLabel(user2);
			user2L.setBounds(350, 145, 60, 20);
			getContentPane().add(user2L);
			user2L.setOpaque(false);
			room1Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\대기중.png"));			
		}else if(roomAr.get(0).getRoomState()==3) {			
			String user1 = roomAr.get(0).getUser1();
			user1L = new JLabel(user1);
			user1L.setBounds(220, 145, 60, 20);
			user1L.setOpaque(false);
			getContentPane().add(user1L);    
			String user2 = roomAr.get(0).getUser2();   
			user2L = new JLabel(user2);
			user2L.setBounds(350, 145, 60, 20);
			getContentPane().add(user2L);
			user2L.setOpaque(false);
			room1Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\게임중.png"));
		}
		if(roomAr.get(1).getRoomState()==1) {
			room2Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		}else if(roomAr.get(1).getRoomState()==2) {
			room2Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\대기중.png"));
		}else if(roomAr.get(1).getRoomState()==3) {
			room2Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\게임중.png"));
		}
		if(roomAr.get(2).getRoomState()==1) {
			room3Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		}else if(roomAr.get(2).getRoomState()==2) {
			room3Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\대기중.png"));
		}else if(roomAr.get(2).getRoomState()==3) {
			room3Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\게임중.png"));
		}
		if(roomAr.get(3).getRoomState()==1) {
			room4Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		}else if(roomAr.get(3).getRoomState()==2) {
			room4Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\대기중.png"));
		}else if(roomAr.get(3).getRoomState()==3) {
			room4Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\게임중.png"));
		}
		if(roomAr.get(4).getRoomState()==1) {
			room5Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		}else if(roomAr.get(4).getRoomState()==2) {
			room5Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\대기중.png"));
		}else if(roomAr.get(4).getRoomState()==3) {
			room5Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\게임중.png"));
		}
		if(roomAr.get(5).getRoomState()==1) {
			room6Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		}else if(roomAr.get(5).getRoomState()==2) {
			room6Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\대기중.png"));
		}else if(roomAr.get(5).getRoomState()==3) {
			room6Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\게임중.png"));
		}
*/
		rosterA = new JTextArea("");
		rosterL = new JLabel(new ImageIcon("C:\\cookierun\\png\\접속자목록.png"));
		chatA = new JTextArea();
		chatT = new JTextField();
		labelL = new JLabel(new ImageIcon("C:\\cookierun\\png\\쿠키런이름2.PNG"));
		backGroundL = new JLabel(new ImageIcon("C:\\cookierun\\png\\배경화면.jpg"));

		// 방만들기 버튼
		// makeRoomBtn.setBounds(20, 100, 150, 40);
		// 프레임에 붙히기
		// add(makeRoomBtn);
		// 대기방 만들기 6개
		room1Btn.setOpaque(false);
		room2Btn.setOpaque(false);
		room3Btn.setOpaque(false);
		room4Btn.setOpaque(false);
		room5Btn.setOpaque(false);
		room6Btn.setOpaque(false);
		room1Btn.setBounds(190, 120, 230, 63);
		room2Btn.setBounds(425, 120, 230, 63);
		room3Btn.setBounds(190, 200, 230, 63);
		room4Btn.setBounds(425, 200, 230, 63);
		room5Btn.setBounds(190, 280, 230, 63);
		room6Btn.setBounds(425, 280, 230, 63);
		exitBtn.setBounds(600, 530, 90, 40);
		// 프레임에 붙히기
		add(room1Btn);
		add(room2Btn);
		add(room3Btn);
		add(room4Btn);
		add(room5Btn);
		add(room6Btn);
		add(exitBtn);
		// 채팅창 패널 만들기
		panel = new JPanel();
		panel.setBounds(185, 357, 475, 170);
		panel.setBorder(new LineBorder(Color.BLACK, 1));
		panel.setLayout(null);
		// 채팅 화면을 보여주는곳
		chatA.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		chatA.setBackground(new Color(255, 255, 220));
		chatA.setEditable(false);
		scroll = new JScrollPane(chatA);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(2, 2, 470, 135);
		// 채팅 치는곳
		chatT.setBounds(2, 138, 369, 30);
		// 채팅 보내기 버튼
		sendBtn.setBounds(373, 138, 100, 29);
		// 프레임에 붙히기
		panel.add(scroll);
		panel.add(chatT);
		panel.add(sendBtn);
		getContentPane().add(panel);
		// 접속자 화면 관련
		newListBtn = new JButton("새로고침");
		list = new JList<LoginDTO>(new DefaultListModel<LoginDTO>());
		model = (DefaultListModel<LoginDTO>) list.getModel();
		this.scroll2 = new JScrollPane(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(scroll2);
		add(newListBtn);
		scroll2.setBounds(20, 285, 150, 254);
		newListBtn.setBounds(20, 542, 90, 25);
		// 내 상태창 정보
		JPanel myInfoPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				Image image = Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\사용자정보.png");
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		myInfoPanel.setBounds(20, 160, 150, 110);
		myInfoPanel.setLayout(null);
		myInfoNameL = new JLabel("닉네임: ");
		myInfoScoreL = new JLabel("내점수: ");
		myInfoCoinL = new JLabel("내코인: ");
		myInfoNameT = new JLabel(loginDTO.getNickName() + "");
		myInfoScoreT = new JLabel(loginDTO.getScore() + "점");
		myInfoCoinT = new JLabel(loginDTO.getCoin() + "coin");

		myInfoNameL.setBounds(8, 20, 50, 30);
		myInfoScoreL.setBounds(8, 40, 50, 30);
		myInfoCoinL.setBounds(8, 60, 50, 30);

		myInfoNameT.setBounds(60, 20, 100, 30);
		myInfoScoreT.setBounds(60, 40, 100, 30);
		myInfoCoinT.setBounds(60, 60, 100, 30);

		myInfoPanel.add(myInfoNameL);
		myInfoPanel.add(myInfoScoreL);
		myInfoPanel.add(myInfoCoinL);
		myInfoPanel.add(myInfoNameT);
		myInfoPanel.add(myInfoScoreT);
		myInfoPanel.add(myInfoCoinT);
		add(myInfoPanel);

		// 위에 글씨 라벨
		labelL.setBounds(80, -240, 700, 600);
		add(labelL);

		// gif파일 넣기
		JPanel panel_gif1 = new JPanel() {
			public void paintComponent(Graphics g) {
				Image image = Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\활쟁이뛰는거.gif");
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel_gif1.setBounds(180, 0, 130, 130);
		JPanel panel_gif2 = new JPanel() {
			public void paintComponent(Graphics g) {
				Image image = Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\기본뛰는거.gif");
				g.drawImage(image, 0, -25, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel_gif2.setBounds(550, 0, 150, 150);
		add(panel_gif1);
		add(panel_gif2);
		// 배경화면
		backGroundL.setBounds(0, 0, 700, 600);
		add(backGroundL);
		// 프레임 설정
		setTitle("Cookie Run Lobby Room");
		setBounds(700, 100, 700, 600);
		setVisible(true);
		setResizable(false);

		// 이벤트
		room1Btn.addActionListener(this);
		room2Btn.addActionListener(this);
		room3Btn.addActionListener(this);
		room4Btn.addActionListener(this);
		room5Btn.addActionListener(this);
		room6Btn.addActionListener(this);
		newListBtn.addActionListener(this);
		exitBtn.addActionListener(this);

		this.addWindowListener(null);
		List<LoginDTO> list = registerDAO.getJoinList();
		for (LoginDTO dto : list) {
			model.addElement(dto);
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// 서버로 보내기
				registerDAO.deleteJoinList(loginDTO.getId());
				writer.println("exit");
				writer.flush();
			}
		});

	}

	public void JoinList() {
		List<LoginDTO> list = registerDAO.getJoinList();
		model.removeAllElements();
		for (LoginDTO dto : list) {
			model.addElement(dto);
		}
	}
	
	
	public void RoomList() {
		RoomDAO roomDAO = new RoomDAO();
		roomAr = roomDAO.selectAllRoom();
		System.out.println("방 상태 새로고침 완료!!"+roomAr.get(0).getRoomState());
		if(roomAr.get(0).getRoomState()==1) {			
			room1Btn.setIcon(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
		}else if(roomAr.get(0).getRoomState()==2) {
			String user1 = roomAr.get(0).getUser1();
			user1L = new JLabel(user1);
			user1L.setBounds(220, 145, 60, 20);
			user1L.setOpaque(false);
			getContentPane().add(user1L);    
			String user2 = roomAr.get(0).getUser2();   
			user2L = new JLabel(user2);
			user2L.setBounds(350, 145, 60, 20);
			getContentPane().add(user2L);
			user2L.setOpaque(false);
			room1Btn.setIcon(new ImageIcon("C:\\cookierun\\png\\대기중.png"));			
		}else if(roomAr.get(0).getRoomState()==3) {			
			String user1 = roomAr.get(0).getUser1();
			user1L = new JLabel(user1);
			user1L.setBounds(220, 145, 60, 20);
			user1L.setOpaque(false);
			getContentPane().add(user1L);    
			String user2 = roomAr.get(0).getUser2();   
			user2L = new JLabel(user2);
			user2L.setBounds(350, 145, 60, 20);
			getContentPane().add(user2L);
			user2L.setOpaque(false);
			room1Btn.setIcon(new ImageIcon("C:\\cookierun\\png\\게임중.png"));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void service() {
		// 서버는 누구인지 모른다 그래서 ip를 입력해주는것이다.
		//String serverIP = "192.168.0.159"; // 이렇게 적으면 바로 서버로 들어간다 IP안치고
		//String serverIP = "192.168.147.4";
		String serverIP = "192.168.0.153";
		if (serverIP == null || serverIP.length() == 0) {
			System.out.println("서버IP가 입력되지 않았습니다.");
			System.exit(0);
		}

		// 닉네임
		String nickName = loginDTO.getNickName();
		try {
			// 소켓생성
			socket = new Socket(serverIP, 7777); // 아이피는 위에있는거고 port 는 고정? 꼭 9500써야하나
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
		chatT.addActionListener(this); // 타자를 치고 엔터를 쳤을때 나타낸다

	} // service생성자
	@Override
	public void run() {
		String line;
		// 서버로 부터 받기
		while (true) {
			try {
				// reader은 서버로부터 읽어오는 과정이다.
				line = reader.readLine(); // line의 기준은 엔터가 기준이다. 엔터 치기전까지의 모든 Line을 읽어 온다
				if (line == null || line.equals("exit")) {
					// 통신 끊기
					reader.close(); // 읽기 끊기
					writer.close(); // 쓰기 끊기
					socket.close(); // 소켓 끊기
					dispose();
					System.exit(0);
					break;
				}
				chatA.append(line + "\n");
				// 스크롤바를 항상 아래로 넣기
				int pos = chatA.getText().length();
				chatA.setCaretPosition(pos);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
//		|| e.getSource() == Room2Btn || e.getSource() == Room3Btn
//				|| e.getSource() == Room4Btn || e.getSource() == Room5Btn || e.getSource() == Room6Btn
		RoomDAO roomDAO = new RoomDAO();
		roomAr = roomDAO.selectAllRoom();
		/*
		if(e.getSource() == room1Btn) {
			roomNum=1;
			if(roomAr.get(0).getUserCount()==0) {
				new MakeRoom(this, loginDTO, clip, roomNum);
			}else if(roomAr.get(0).getUserCount()==1) {
				roomDAO.updateUser2(loginDTO.getNickName(),roomNum);
			}else if(roomAr.get(0).getUserCount()==2) {
				JOptionPane.showMessageDialog(this, "인원이 꽉 찼습니다.");
			}
			*/
		
		if(e.getSource() == room1Btn ) {
	         roomNum=1;
	         if(roomAr.get(0).getUserCount()==0) {
	            new MakeRoom(this, loginDTO, clip, roomNum);
	         }else if(roomAr.get(0).getUserCount()==1) {	            
	            if(roomAr.get(0).getSecretRoom()==0) {
	               roomDAO.updateUser2(loginDTO.getNickName(),roomNum);   
	               //new RoomClient(loginDTO,roomDTO,clip).service();
	               new RoomClient(loginDTO,clip).service();
	            
	            }else if(roomAr.get(0).getSecretRoom()==1) {	               
	               String pwInput = JOptionPane.showInputDialog("비밀번호를 입력하시오");
	               if(roomAr.get(0).getRoomPw().equals(pwInput)) {
	                  roomDAO.updateUser2(loginDTO.getNickName(),roomNum);
	                 // new RoomClient(loginDTO,roomDTO,clip).service();
	                  new RoomClient(loginDTO,clip).service();
	               }else {
	                  JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다.");
	               }
	            }
	         }else if(roomAr.get(0).getUserCount()==2) {
	            JOptionPane.showMessageDialog(this, "인원이 꽉 찼습니다.");
	         }
	         
	         
	      
			
		} else if (e.getSource() == newListBtn) {
			List<LoginDTO> list = registerDAO.getJoinList();
			model.removeAllElements();
			for (LoginDTO dto : list) {
				model.addElement(dto);
			}
		} else if (e.getSource() == exitBtn) {
			registerDAO.deleteJoinList(loginDTO.getId());
			writer.println("exit");
			writer.flush();
			System.exit(0);
			return;
		}
		String data = chatT.getText();
		if (data == null || data.equals(""))
			return;
		// writer는 서버로 나의 데이터를 보내는 과정
		// 서버로 채팅 데이터보는거다
		writer.println(data);
		writer.flush(); // 버퍼안에 남아있는 찌꺼기 들을 비워준다.
		chatT.setText("");
	}
	public void play(String fileName) {
		try {
			ais = AudioSystem.getAudioInputStream(new File(fileName));
			clip = AudioSystem.getClip();
			clip.stop();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MakeRoom extends JFrame implements ActionListener {

	private JButton createBtn, cancelBtn;
	private JLabel roomNameL, pwL, peopleL;
	private JTextField roomNameT, pwT;
	private JCheckBox pwCheckBox;
	private LobbyClient lobbyClient;
	private Clip clip;
	private LoginDTO loginDTO;
	private PrintWriter writer;
	private int roomNum;
	
	private RoomDTO roomDTO;
	private RoomDAO roomDAO;
	

	public MakeRoom(LobbyClient lobbyClient, LoginDTO loginDTO, Clip clip,int roomNum) {
		this.lobbyClient = lobbyClient;
		this.clip = clip;
		this.loginDTO = loginDTO;
		this.roomNum=roomNum;

		setContentPane(new MyPanel());
		// 확인 버튼
		createBtn = new JButton(new ImageIcon("C:\\cookierun\\png\\확인.png"));
		createBtn.setBounds(100, 242, 110, 35);
		// 취소 버튼
		cancelBtn = new JButton(new ImageIcon("C:\\cookierun\\png\\취소.png"));
		cancelBtn.setBounds(245, 242, 110, 35);
		// 방제목 라벨
		roomNameL = new JLabel("방  제 목");
		roomNameL.setBounds(85, 90, 100, 50);
		roomNameL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		// 방제목 적는곳
		roomNameT = new JTextField();
		roomNameT.setBounds(215, 100, 150, 30);
		roomNameT.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		roomNameT.setBackground(new Color(255, 255, 200));
		// 비밀번호 라벨
		pwL = new JLabel("비밀번호");
		pwL.setBounds(85, 130, 100, 50);
		pwL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		// 비밀번호 방 만들때 체크박스 이용하기
		pwCheckBox = new JCheckBox("비밀방");
		pwCheckBox.setBounds(215, 140, 70, 30);
		pwCheckBox.setOpaque(false); // 뒤에 배경 지우기
		// 비밀번호 적는곳
		pwT = new JTextField();
		pwT.setBounds(285, 140, 80, 30);
		pwT.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		pwT.setBackground(new Color(255, 255, 200));
		pwT.setEnabled(false);
		// 인원성정 라벨
		peopleL = new JLabel("인원설정");
		peopleL.setBounds(85, 170, 100, 50);
		peopleL.setFont(new Font("맑은 고딕", Font.PLAIN, 20)); // 채팅창 폰트지정
		// 인원설정 정하는곳 2~4임 최소2 최대4
		SpinnerModel value = new SpinnerNumberModel(2, // initial value
				2, // minimum value
				4, // maximum value
				1); // step
		JSpinner peopleS = new JSpinner(value);
		peopleS.setBounds(215, 180, 50, 30);
		// 각 버튼 및 등등 프레임에 붙히기
		add(createBtn);
		add(cancelBtn);
		add(roomNameL);
		add(roomNameT);
		add(pwL);
		add(pwCheckBox);
		add(pwT);
		add(peopleL);
		add(peopleS);
		// 프레임 설정
		setTitle("Cookie Run Make Room");
		setLayout(null);
		setBounds(825, 240, 450, 315);
		setVisible(true);
		setResizable(false);
		// 이벤트
		createBtn.addActionListener(this); // 생성 버튼 이벤트
		cancelBtn.addActionListener(this); // 취소 버튼 이벤트
		pwCheckBox.addActionListener(this); // 체크박스 이벤트

	}// 생성자
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createBtn) {
			RoomDAO roomDAO = new RoomDAO();
			if (roomNameT.getText() == null || roomNameT.getText().equals("")) {
				// 방만들라는 메시지 띄우기
				int result = JOptionPane.showConfirmDialog(this, "방제목을 넣으세요", "MESSAGE", JOptionPane.DEFAULT_OPTION);
				if (result == JOptionPane.OK_OPTION)
					return;
			} else if (pwCheckBox.isSelected() == true && pwT.getText() == null
					|| pwCheckBox.isSelected() == true && pwT.getText().equals("")) {
				int result = JOptionPane.showConfirmDialog(this, "비밀번호를 넣으세요", "MESSAGE", JOptionPane.DEFAULT_OPTION);
				if (result == JOptionPane.OK_OPTION)
					return;
			}
			 if(roomNum==1){
				RoomDTO roomDTO = new RoomDTO();
				System.out.println(roomNum);
				roomDTO.setRoomNumber(1);
				roomDTO.setRoomName(roomNameT.getText());
				if(pwT.getText()==null||pwT.getText().equals("")) {
					roomDTO.setSecretRoom(0);
					
				}else{
					roomDTO.setSecretRoom(1);
					roomDTO.setRoomPw(pwT.getText());
				}
				roomDTO.setUser1(loginDTO.getNickName());
				roomDTO.setUserCount(1);
				roomDTO.setRoomState(2);			 
			 
				System.out.println("roomClient넘기기전에 닉네임 " + loginDTO.getNickName());
				roomDAO.updateRoomInf(roomDTO);
				this.dispose();
				lobbyClient.dispose();
				new RoomClient(loginDTO, clip).service();
				// 방만들때 로비 서버에 exit 를 보내서 통신을 끊는다.
				// writer.println("exit");
				// writer.flush();
			}
			setVisible(false); // MakeRoom의 프레임을 끄기
		} else if (e.getSource() == cancelBtn) {
			setVisible(false);
		} else if (pwCheckBox.isSelected() == true) {
			pwT.setEnabled(true);
		} else if (pwCheckBox.isSelected() == false) {
			pwT.setEnabled(false);
			pwT.setText("");
		}
	}
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Toolkit t = Toolkit.getDefaultToolkit();
			Image ing = t.getImage("C:\\cookierun\\png\\방만들기화면.png");
			g.drawImage(ing, 0, -10, this);
		}
	}
}