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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;


class LobbyClient extends JFrame implements ActionListener, Runnable{	
		private JButton makeRoomBtn, shopBtn, miniGameBtn, sendBtn; // 방만들기, 상점, 미니게임, 채팅보내기
		private JButton Room1Btn, Room2Btn, Room3Btn, Room4Btn, Room5Btn, Room6Btn; // 6개의 방 만들기 버튼
	    private JLabel totRankL, myRankL, rosterL, backGroundL, labelL; // 토탈랭킹 라벨, 나의 점수라벨, 명단라벨
	    private JTextArea rosterA, myRankA, chatA;
	    private JTextField chatT;   
	    private AudioInputStream ais;
	    private Clip clip;
	    public static String nickName="딸기";
	  

	    
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;

		public LobbyClient(){
		play("C:\\cookierun\\music\\LobbyMusic.wav");
        setLayout(null); // 프레임깨기        
        // 버튼 만들기
        //makeRoomBtn = new JButton(new ImageIcon("C:\\cookierun\\png\\방만들기.png"));
        //shopBtn = new JButton("쇼핑");      
        //miniGameBtn = new JButton("미니게임");        
        sendBtn = new JButton("보내기");        
        Room1Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));         
        Room2Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));   
        Room3Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));
        Room4Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));   
        Room5Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));   
        Room6Btn = new JButton(new ImageIcon("C:\\cookierun\\png\\빈방.png"));		
		
        //totRankL = new JLabel();
        //rosterA = new JTextArea("백기환\n자바\n천재다");
        rosterA = new JTextArea("");
        rosterL = new JLabel(new ImageIcon("C:\\cookierun\\png\\접속자목록.png"));      
        chatA = new JTextArea();
        chatT = new JTextField();   
        //myRankA = new JTextArea("코인\n최대점수\n");
        myRankA = new JTextArea("");
        myRankL = new JLabel(new ImageIcon("C:\\cookierun\\png\\사용자정보.png"));
        labelL = new JLabel(new ImageIcon("C:\\cookierun\\png\\쿠키런이름2.PNG"));
        backGroundL = new JLabel(new ImageIcon("C:\\cookierun\\png\\배경화면.jpg"));
    
        // 방만들기 버튼
       // makeRoomBtn.setBounds(20, 100, 150, 40);
        // 프레임에 붙히기
       // add(makeRoomBtn);       
        //대기방 만들기 6개        
        Room1Btn.setBounds(190, 120, 230, 63);
        Room2Btn.setBounds(425, 120, 230, 63);      
        Room3Btn.setBounds(190, 200, 230, 63);  
        Room4Btn.setBounds(425, 200, 230, 63);      
        Room5Btn.setBounds(190, 280, 230, 63);      
        Room6Btn.setBounds(425, 280, 230, 63);
        // 프레임에 붙히기
        add(Room1Btn);
        add(Room2Btn);
        add(Room3Btn);
        add(Room4Btn);      
        add(Room5Btn);
        add(Room6Btn);
        //채팅창   패널 만들기
        JPanel panel = new JPanel();
        panel.setBounds(185, 357, 475, 170);        
        panel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.setLayout(null);          
        // 채팅 화면을 보여주는곳         
        chatA.setFont(new Font("Serif", Font.PLAIN, 13));
        chatA.setBackground(new Color(255, 255, 220));
        chatA.setEditable(false);       
        JScrollPane scroll = new JScrollPane(chatA);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(2, 2, 470, 135);
        // 채팅 치는곳           
        chatT.setBounds(2, 138, 369, 30);           
        //chatT.setColumns(10);
        // 채팅 보내기 버튼
        sendBtn.setBounds(373, 138, 100, 29);       
        // 프레임에 붙히기 
        panel.add(scroll);
        panel.add(chatT);
        panel.add(sendBtn);
        getContentPane().add(panel);
        // 접속자 화면 관련
        rosterA.setEditable(false);  // 못쓰게 막는거다
        JScrollPane rosterS = new JScrollPane(rosterA);
        rosterS.setBounds(30, 310, 130, 210);       
        rosterL.setBounds(20, 285, 150, 254);
        // 프레임에 붙히기
        add(rosterS);
        add(rosterL);   
        // 내 상태창 정보     
        myRankA.setBounds(30, 185, 120, 70);
        myRankA.setEditable(false);
        myRankL.setBounds(20, 160, 150, 120);
        // 프레임에 붙히기
        add(myRankA);   
        add(myRankL);   
        // 위에  글씨 라벨
        labelL.setBounds(80, -240, 700, 600);
        add(labelL);
        //gif파일 넣기
        JPanel panel_gif1 = new JPanel() {
            public void paintComponent(Graphics g) {
                Image image = Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\활쟁이뛰는거.gif");
                g.drawImage(image, 0, 0, getWidth (), getHeight (), this);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        panel_gif1.setBounds(180,0,130,130);
        add(panel_gif1);        
        JPanel panel_gif2 = new JPanel() {
            public void paintComponent(Graphics g) {
                Image image = Toolkit.getDefaultToolkit().getImage("C:\\cookierun\\png\\기본뛰는거.gif");                
                g.drawImage(image, 0, -25, getWidth (), getHeight (), this);
                setOpaque(false);
                super.paintComponent(g);
            }
        };        
        panel_gif2.setBounds(550,0,150,150);
        add(panel_gif2);
        // 배경화면 
        backGroundL.setBounds(0, 0, 700, 600);
        add(backGroundL);
        // 프레임 설정
        setTitle("Cookie Run Lobby Room");
        setBounds(700, 100, 700, 600);
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false);     
        
        // 이벤트
        //makeRoomBtn.addActionListener(this);
        Room1Btn.addActionListener(this);
        Room2Btn.addActionListener(this);
        Room3Btn.addActionListener(this);
        Room4Btn.addActionListener(this);
        Room5Btn.addActionListener(this);
        Room6Btn.addActionListener(this);   

		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				//서버로 보내기
				writer.println("exit");
				writer.flush();
				System.out.println("로비방1번 엑스");
			}
		});
		
	}
	public void service(){
		// 서버는 누구인지 모른다 그래서 ip를 입력해주는것이다.
		//String serverIP = JOptionPane.showInputDialog(this, "서버 IP를 입력하세요", "서버IP", JOptionPane.INFORMATION_MESSAGE);
		//String serverIP = JOptionPane.showInputDialog(this, "서버 IP를 입력하세요", "192.168.0.159"); // 아무것도 안적으면 질문으로 물어본다.
		//String serverIP = "192.168.0.153"; //이렇게 적으면 바로 서버로 들어간다 IP안치고
		String serverIP="172.30.1.28";
		//String serverIP = "192.168.147.4";
		if(serverIP == null || serverIP.length() == 0){
			System.out.println("서버IP가 입력되지 않았습니다.");
			System.exit(0);
		}

		// 닉네임
		/*
		String nickName = JOptionPane.showInputDialog(this, "닉네임을 입력하세요", "닉네임", JOptionPane.INFORMATION_MESSAGE); // 아무것도 안적으면 질문으로 물어본다.
		if(nickName == null || nickName.length() == 0){
			nickName = "guest";
		}
		*/
		String nickName = "guest";
		try{
			// 소켓생성
			socket = new Socket(serverIP, 7777); //아이피는 위에있는거고 port 는 고정? 꼭 9500써야하나
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		}catch(UnknownHostException e){
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
			System.exit(0);
		}catch(IOException e){
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
	public void run(){
		String line;
		// 서버로 부터 받기
		while(true){
			try{
				// reader은 서버로부터 읽어오는 과정이다.
				line = reader.readLine(); // line의 기준은 엔터가 기준이다. 엔터 치기전까지의 모든 Line을 읽어 온다
				if(line == null || line.equals("exit")){
					// 통신 끊기
					reader.close(); // 읽기 끊기
					writer.close(); // 쓰기 끊기
					socket.close(); // 소켓 끊기
					dispose();
					System.out.println("로비방 소켓끊느거다");
					break;					
				}
				chatA.append(line + "\n");				
				// 스크롤바를 항상 아래로 넣기
				int pos = chatA.getText().length();
				chatA.setCaretPosition(pos);

			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {        
        if(e.getSource() == makeRoomBtn) {
        	System.out.println("방만들어라");
            new MakeRoom(this,clip);     
            
        }else if(e.getSource() == Room1Btn ||
                e.getSource() == Room2Btn ||
                e.getSource() == Room3Btn ||
                e.getSource() == Room4Btn ||
                e.getSource() == Room5Btn ||
                e.getSource() == Room6Btn) {
            //JOptionPane.showMessageDialog(LobbyClient.this, "방만들기를 누르세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        	System.out.println("방만들어라");
            new MakeRoom(this,clip);   
        }
    	String data = chatT.getText();
		if(data == null || data.equals("")) return;
		// writer는 서버로 나의 데이터를 보내는 과정
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
        }
    }
}
class MakeRoom extends JFrame implements ActionListener{
	
	private JButton createBtn, cancelBtn;
	private JLabel roomNameL, pwL, peopleL;
	private JTextField roomNameT, pwT;
	private JCheckBox pwCheckBox;	
	//private Lobby lobby;
	private LobbyClient chatClient;
	private Clip clip;
	
	//private Sound sound;
	//public MakeRoom(LobbyServerClient lobbyServerClient) {	  //오류가 떠서 잠시 생성자를 바꿔놓았습니다! -하린
	//public MakeRoom(Lobby lobby, Clip clip) {
	public MakeRoom(LobbyClient chatClient, Clip clip) {
		//this.lobby=lobby;
		this.chatClient = chatClient;
		//this.lobbyServerClient = lobbyServerClient;
		//this.writer = lobbyServerClient.getWriter();
		this.clip=clip;
				
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
		roomNameL.setFont(new Font("궁서", Font.PLAIN, 20));	
		// 방제목 적는곳
		roomNameT = new JTextField();
		roomNameT.setBounds(215, 100, 150, 30);	
		roomNameT.setFont(new Font("돋움", Font.PLAIN, 15));	
		roomNameT.setBackground(new Color(255, 255, 200));
		// 비밀번호 라벨		
		pwL = new JLabel("비밀번호");
		pwL.setBounds(85, 130, 100, 50);	
		pwL.setFont(new Font("궁서", Font.PLAIN, 20));	
		// 비밀번호 방 만들때 체크박스 이용하기
		pwCheckBox = new JCheckBox("비밀방");
		pwCheckBox.setBounds(215, 140, 70, 30);
		pwCheckBox.setOpaque(false); // 뒤에 배경 지우기	
		// 비밀번호 적는곳
		pwT = new JTextField();
		pwT.setBounds(285, 140, 80, 30);	
		pwT.setFont(new Font("돋움", Font.PLAIN, 15));	
		pwT.setBackground(new Color(255, 255, 200));
		pwT.setEnabled(false);
		// 인원성정 라벨
		peopleL = new JLabel("인원설정");
		peopleL.setBounds(85, 170, 100, 50);	
		peopleL.setFont(new Font("궁서", Font.PLAIN, 20));	// 채팅창 폰트지정	
		// 인원설정 정하는곳 2~4임 최소2 최대4
	    SpinnerModel value = new SpinnerNumberModel(2, //initial value  
	    											2, //minimum value  
	    											4, //maximum value  
	    											1); //step  
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
		//프레임 설정
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
		if(e.getSource() == createBtn) {
			if(roomNameT.getText() == null || roomNameT.getText().equals("")) {
				// 방만들라는 메시지 띄우기
				//JOptionPane.showMessageDialog(this, "방제목을 넣으세요", "MESSAGE", JOptionPane.WARNING_MESSAGE);
				int result = JOptionPane.showConfirmDialog(this, "방제목을 넣으세요", "MESSAGE", JOptionPane.DEFAULT_OPTION);
				if(result == JOptionPane.OK_OPTION) return;							
			}else if(pwCheckBox.isSelected() == true && pwT.getText() == null ||
					pwCheckBox.isSelected() == true && pwT.getText().equals("")){
				//JOptionPane.showMessageDialog(this, "비밀번호를 넣으세요", "MESSAGE", JOptionPane.WARNING_MESSAGE);
				int result = JOptionPane.showConfirmDialog(this, "비밀번호를 넣으세요", "MESSAGE", JOptionPane.DEFAULT_OPTION);
				if(result == JOptionPane.OK_OPTION) return;			
			}else {	
				this.dispose();
				chatClient.dispose();
				//lobby.dispose();
				//new StandByFrame(clip);
				new RoomClient(clip).service();
				//writer.println("exit");
				//writer.flush();
				
			}
			setVisible(false); // MakeRoom의 프레임을 끄기
		}else if(e.getSource() == cancelBtn) {
			setVisible(false);
		}else if(pwCheckBox.isSelected() == true) {
			pwT.setEnabled(true);
		}
		else if(pwCheckBox.isSelected() == false) { 
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
