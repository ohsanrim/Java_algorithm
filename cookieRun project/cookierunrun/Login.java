package cookierunrun;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame implements ActionListener {
	private JButton loginBtn, registerBtn, idSearchBtn, pwSearchBtn;
	private JLabel idL, pwL;
	private JTextField idT;
	private ImageIcon icon;
	private JPasswordField pwF;
	private LoginDTO dto = new LoginDTO();
	private String getPw;
	private Clip clip; 
	

	public Login() {
		super("LogIn");

		icon = new ImageIcon("C:\\cookierun\\png\\Login.png");
		// 배경화면을 입혀주는 패널 생성
		JPanel backP = new JPanel(new GridLayout(2, 1, 10, 10)) {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(true);
			}
		};

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		Font fL = new Font("Consolas", Font.BOLD, 20);

		// 아이디와 비밀번호 입력하는 라벨과 텍스트필드 생성
		JPanel idP = new JPanel();
		JPanel idTemp = new JPanel();
		idL = new JLabel("ID");
		idT = new JTextField(10);
		idL.setFont(fL); 
		idTemp.add(idL);
		idTemp.add(idT);
		idTemp.setOpaque(false);
		//비밀번호 필드 설정
		JPanel ppw = new JPanel();
		pwL = new JLabel("PW");
		pwL.setFont(fL);
		pwF = new JPasswordField(10);
		pwF.setEchoChar('*');
		ppw.add(pwL);
		ppw.add(pwF);
		ppw.setOpaque(false);
		
		idP.add(idTemp);
		idP.add(ppw);
		idP.setOpaque(false);

		// 버튼
		JPanel btnP = new JPanel(new GridLayout(2, 2, 10, 10));
		loginBtn = new JButton("로그인");
		registerBtn = new JButton("회원가입");
		idSearchBtn = new JButton("ID 찾기");
		pwSearchBtn = new JButton("PW 찾기");
		btnP.add(loginBtn);
		btnP.add(registerBtn);
		btnP.add(idSearchBtn);
		btnP.add(pwSearchBtn);
		btnP.setOpaque(false);
		p2.add("Center", idP);
		p2.add("South", btnP);
		p1.setOpaque(false);
		p2.setOpaque(false);

		Container con = getContentPane();
		
		// add
		backP.setOpaque(false);

		con.add("Center", backP);
		backP.add("Center", p1);
		backP.add("South", p2);

		setResizable(false);
		setBounds(700, 200, 400, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 배경음악
		File file = new File("C:\\cookierun\\music\\loginMusic.wav");
		
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			try {
				clip = AudioSystem.getClip();
				clip.open(stream);
				clip.start();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 이벤트
		loginBtn.addActionListener(this);
		registerBtn.addActionListener(this);
		idSearchBtn.addActionListener(this);
		pwSearchBtn.addActionListener(this);

		pwF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					keyEnter();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getPw = new String(pwF.getPassword());
		if (e.getSource() == loginBtn) {
			// 로그인
			// if(idT.getText().equals(dto.getId()) && getPw.equals(dto.getPw())){
			if (idT.getText().equals("asd") && getPw.equals("asd")) {
				// 아이디와 비밀번호가 일치할경우 로그인
				this.dispose();
				clip.close();
				//new Lobby();
				new LobbyClient().service();
				//new LobbyServerClient();
			} else if (idT.getText().equals("")) {
				// 아이디를 입력하는 textfield가 빈공간일경우
				JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요!!");
			} else if (getPw.equals("")) {
				// 비밀번호를 입력하는 textfield가 빈공간일경우
				JOptionPane.showMessageDialog(this, "비밀번호를 입력해 주세요!!");
			} 
			else if (!idT.getText().equals(dto.getId()) || !getPw.equals(dto.getPw())) {
				// 아이디나 비밀번호가 틀렸을 경우 창출력
				JOptionPane.showMessageDialog(this, "없는 아이디나 없는 비밀번호입니다!!");
				idT.setText("");
				pwF.setText("");
			}

		} else if (e.getSource() == registerBtn) {
			// 회원가입
			new Register();			
		} else if (e.getSource() == idSearchBtn) {
			// 아이디 찾기
			new IdSearch();
			
		} else if (e.getSource() == pwSearchBtn) {
			// 비밀번호 찾기
			new PwSearch();
		}
	}
	public void keyEnter() {
		getPw = new String(pwF.getPassword());
		// if(idT.getText().equals(dto.getId()) && getPw.equals(dto.getPw())){
		if (idT.getText().equals("asd") && getPw.equals("asd")) {
			// 아이디와 비밀번호가 일치할경우 로그인
			this.dispose();
			clip.close();
			//new Lobby();
			new LobbyClient().service();
		} else if (idT.getText().equals("")) {
			// 아이디를 입력하는 textfield가 빈공간일경우
			JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요!!");
		} else if (getPw.equals("")) {
			// 비밀번호를 입력하는 textfield가 빈공간일경우
			JOptionPane.showMessageDialog(this, "비밀번호를 입력해 주세요!!");
		}else if (!idT.getText().equals(dto.getId()) || !getPw.equals(dto.getPw())) {
			// 아이디나 비밀번호가 틀렸을 경우 창출력
			JOptionPane.showMessageDialog(this, "없는 아이디나 없는 비밀번호입니다!!");
		}
	}
	public static void main(String[] args) {
		new Login();
	}
}
//---------------------------회원가입 창 클래스--------------------------------------
class Register extends JFrame implements ActionListener {

	private JLabel idL, nickNameL, pwL, pwconfirmL, nameL, phoneNL, emailL, titleL, idconfirmL, nickconfirmL;

	private JTextField idT, nickNameT, nameT, phoneNT, emailT;
	private JButton registerBtn, cancelBtn, idConfirmBtn, nickConfirmBtn, pwConfirmBtn;
	private ImageIcon icon;
	private JPasswordField pwF, pwconfirmF;

	public Register() {
		super("쿠키런 회원가입");
		
		//DB모든레코드를 꺼내서 JList뿌리기
		//List<LoginDTO> list = LoginDTO.getLoginList(); import list>util
		/*
		 * public List<LoginDTO> getLoginList(){
		 * List<LoginDTO> list = new ArrayList<LoginDTO>();
		 * getConnection();
		 * String sql = "select*from Login"
		 * try{
		 * pstmt = conn.prepareStatement(sql);
		 * rs=pstmt.executeQuery();
		 * 
		 * while(rs.next()){
		 * LoginDTO loginDTO = new LoginDTO();
		 * list.add(loginDTO);
		 * loginDTO.setSeq(rs.getInt("seq"));
		 * loginDTO.setName(rs.getString("name"));
		 * }
		 * }catch(){
		 * list=null;
		 * }
		 * return list;
		 * }
		 * */

		icon = new ImageIcon("C:\\cookierun\\png\\RegisterBackgroundImg.jpg");
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		background.setLayout(null);

		JPanel northP = new JPanel();
		titleL = new JLabel("회원가입");
		titleL.setOpaque(false);
		northP.add(titleL);
		northP.setBounds(165, 15, 50, 50);
		northP.setOpaque(false);
/////////////////////////////////////////////////

		idL = new JLabel("아이디 : ");
		nickNameL = new JLabel("닉네임 : ");
		pwL = new JLabel("비밀번호 : ");
		pwconfirmL = new JLabel("비밀번호 확인 : ");
		nameL = new JLabel("이름 : ");
		phoneNL = new JLabel("전화번호: ");
		emailL = new JLabel("이메일 : ");
		//서쪽 라벨 설정하기
		JPanel westP = new JPanel();
		westP.setBounds(20, 20, 105, 240);
		westP.setLayout(null);
		westP.setOpaque(false);
		westP.add(idL);
		westP.add(nickNameL);
		westP.add(pwL);
		westP.add(pwconfirmL);
		westP.add(nameL);
		westP.add(phoneNL);
		westP.add(emailL);

		westP.setOpaque(false);
		
		idL.setBounds(10, 30, 65, 20);
		nickNameL.setBounds(10, 60, 65, 20);
		pwL.setBounds(10, 90, 80, 20);
		pwconfirmL.setBounds(10, 120, 95, 20);
		nameL.setBounds(10, 150, 65, 20);
		phoneNL.setBounds(10, 180, 65, 20);
		emailL.setBounds(10, 210, 65, 20);

//////////////////////////////////////////////////////
		//텍스트 필드와 라벨 새롭게 생성하기
		idT = new JTextField();
		pwF = new JPasswordField("");
		pwconfirmF = new JPasswordField("");
		nameT = new JTextField();
		nickNameT = new JTextField();
		phoneNT = new JTextField();
		emailT = new JTextField();
		idConfirmBtn = new JButton("확인");
		nickConfirmBtn = new JButton("확인");
		pwConfirmBtn = new JButton("확인");
		idconfirmL = new JLabel("");
		nickconfirmL = new JLabel("");
		pwconfirmL = new JLabel("");
		
		//텍스트 필드 중앙에 넣기(패널)
		JPanel centerP = new JPanel();
		centerP.setBounds(15, 30, 400, 230);
		centerP.setLayout(null);
		centerP.add(idT);
		centerP.add(pwF);
		centerP.add(pwconfirmF);
		centerP.add(nameT);
		centerP.add(nickNameT);
		centerP.add(phoneNT);
		centerP.add(emailT);
		centerP.add(idConfirmBtn);
		centerP.add(idconfirmL);
		centerP.add(nickConfirmBtn);
		centerP.add(nickconfirmL);
		centerP.add(pwConfirmBtn);
		centerP.add(pwconfirmL);
		centerP.setOpaque(false);

		idT.setBounds(110, 20, 100, 20);
		nickNameT.setBounds(110, 50, 100, 20);
		pwF.setBounds(110, 80, 100, 20);
		pwconfirmF.setBounds(110, 110, 100, 20);
		nameT.setBounds(110, 140, 100, 20);
		phoneNT.setBounds(110, 170, 100, 20);
		emailT.setBounds(110, 200, 150, 20);
		idConfirmBtn.setBounds(225, 20, 60, 20);
		idconfirmL.setBounds(295, 20, 60, 20);
		nickConfirmBtn.setBounds(225, 50, 60, 20);
		nickconfirmL.setBounds(295, 50, 60, 20);
		pwConfirmBtn.setBounds(225, 110, 60, 20);
		pwconfirmL.setBounds(295, 110, 60, 20);

////////////////////////////////////////////////////////
		//남쪽 패널 설정(버튼 넣기)
		JPanel southP = new JPanel();

		southP.setBounds(100, 275, 220, 30);
		southP.setLayout(null);

		registerBtn = new JButton("회원가입");
		registerBtn.setPreferredSize(new Dimension(100, 28));

		cancelBtn = new JButton(" 취    소 ");
		cancelBtn.setPreferredSize(new Dimension(100, 28));
		southP.add(registerBtn);
		southP.add(cancelBtn);
		southP.setOpaque(false);

		registerBtn.setBounds(0, 0, 100, 28);
		cancelBtn.setBounds(110, 0, 100, 28);

		/////////////////////

		background.add(northP);
		background.add(westP);
		background.add(centerP);
		background.add(southP);
		background.setOpaque(false);

		Container c = this.getContentPane();

		c.add("Center", background);

		setBounds(500, 300, 400, 350);
		setVisible(true);
		setResizable(false);
		

		registerBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		idConfirmBtn.addActionListener(this);
		nickConfirmBtn.addActionListener(this);
		pwConfirmBtn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = "cucja";
		String nickName = "구자구";

		String strPw = new String(pwF.getPassword());
		String strPwc = new String(pwconfirmF.getPassword());

		if (e.getSource() == registerBtn) {
			JOptionPane.showMessageDialog(this, "회원가입완료");
			setVisible(false);
		} else if (e.getSource() == cancelBtn) {
			setVisible(false);
		}

		// ID 중복확인
		else if (e.getSource() == idConfirmBtn) {
			// idT.getText();

			if (idT.getText().equals(id)) {
				idconfirmL.setText("중복!");
				idconfirmL.setForeground(Color.RED);
			} else if (idT.getText().equals("")) {
				idconfirmL.setText("사용불가");
				idconfirmL.setForeground(Color.RED);
			} else {
				idconfirmL.setText("사용가능");
				idconfirmL.setForeground(Color.BLACK);
			}

		}
		// NickName 중복확인
		else if (e.getSource() == nickConfirmBtn) {
			if (nickNameT.getText().equals(nickName)) {
				nickconfirmL.setText("중복!");
				nickconfirmL.setForeground(Color.RED);
			} else if (nickNameT.getText().equals("")) {
				nickconfirmL.setText("사용불가");
				nickconfirmL.setForeground(Color.RED);
			} else {
				nickconfirmL.setText("사용가능");
				nickconfirmL.setForeground(Color.BLACK);
			}
			// pw 중복확인
		} else if (e.getSource() == pwConfirmBtn) {
			if (strPw.equals("") || strPwc.equals("")) {
				pwconfirmL.setText("입력해줘");
				System.out.println(pwF.getPassword());
				System.out.println(pwconfirmF.getPassword());

			}else if (strPw.equals(strPwc)) {

				pwconfirmL.setText("사용가능");
				System.out.println(pwF.getPassword());
				System.out.println(pwconfirmF.getPassword());
			} else {
				pwconfirmL.setText("일치x");
				System.out.println(pwF.getPassword());
				System.out.println(pwconfirmF.getPassword());
			}
		}
	}
	public static void main(String[] args) {
		new Register();
	}
}
//-----------------비밀번호 찾기 관련 클래스-----------------------
class PwSearch extends JFrame implements ActionListener {
	private JButton checkBtn, cancelBtn;
	private JLabel idL, emailL;
	private JTextField idT, emailT;
	private LoginDTO dto = new LoginDTO();
	
	public PwSearch() {
		
		super("비밀번호 찾기");
		
		setLayout(null);
		
		idL = new JLabel("ID");
		idL.setBounds(100, 0, 80, 60);
		idT = new JTextField(10);
		idT.setBounds(150, 20, 80, 20);
		
		emailL = new JLabel("EMAIL");
		emailL.setBounds(100, 30, 80, 60);
		emailT = new JTextField(10);
		emailT.setBounds(150, 50, 80, 20);
		
		checkBtn = new JButton("확인");
		checkBtn.setBounds(100, 80, 60, 30);
		cancelBtn = new JButton("취소");
		cancelBtn.setBounds(170, 80, 60, 30);
		
		Container con = getContentPane();
		
		con.add(idL);
		con.add(idT);
		con.add(emailL);
		con.add(emailT);
		con.add(checkBtn);
		con.add(cancelBtn);
		
		checkBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		emailT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					keyEnter();
				}
			}
		});		
		setResizable(false);
		setBounds(750, 250, 350,150);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == checkBtn) {
			//if(idT.getText().equals(dto.getId()) && emailT.getText().equals(dto.getEmail())) {
				// 아이디와 이메일이 일치할경우 비밀번호 바로 수정할수있게 한다.
			if(idT.getText().equals("asd") && emailT.getText().equals("asd")) {
				new PwChange();
				setVisible(false);
			}else if(idT.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력해주세요");
			}else if(emailT.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "이메일를 입력해주세요");
			}else if(!idT.getText().equals(dto.getId()) || !emailT.getText().equals(dto.getEmail())) {
				JOptionPane.showMessageDialog(this, "없는 아이디거나 없는 이메일입니다.");
			}
		}else if(e.getSource() == cancelBtn) {
			setVisible(false);
		}		
	}
	
	public void keyEnter() {
		if(idT.getText().equals("asd") && emailT.getText().equals("asd")) {
			new PwChange();
			setVisible(false);
		}else if(idT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "아이디를 입력해주세요");
		}else if(emailT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "이메일를 입력해주세요");
		}else if(!idT.getText().equals(dto.getId()) || !emailT.getText().equals(dto.getEmail())) {
			JOptionPane.showMessageDialog(this, "없는 아이디거나 없는 이메일입니다.");
		}
	}
}
class PwChange extends JFrame implements ActionListener {
	private JButton checkBtn, cancelBtn;
	private JLabel newPwL, checkPwL;
	private JPasswordField newPwT, checkPwT;
	private String getNewPw, getCheckPw;
	
	public PwChange() {
		super("비밀번호 변경");
		
		setLayout(null);
		
		newPwL = new JLabel("새 비밀번호");
		newPwL.setBounds(60, 0, 100, 60);
		newPwT = new JPasswordField(10);
		newPwT.setBounds(190, 20, 80, 20);
		
		checkPwL = new JLabel("새 비밀번호 확인");
		checkPwL.setBounds(60, 30, 100, 60);
		checkPwT = new JPasswordField(10);
		checkPwT.setBounds(190, 50, 80, 20);
		
		checkBtn = new JButton("확인");
		checkBtn.setBounds(100, 80, 60, 30);
		cancelBtn = new JButton("취소");
		cancelBtn.setBounds(170, 80, 60, 30);
		
		Container con = getContentPane();
		
		con.add(newPwL);
		con.add(newPwT);
		con.add(checkPwL);
		con.add(checkPwT);
		con.add(checkBtn);
		con.add(cancelBtn);
		
		setResizable(false);
		setBounds(750, 250, 350,150);
		setVisible(true);
		
		//이벤트
		checkBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		checkPwT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					keyEnter();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getNewPw = new String(newPwT.getPassword());
		getCheckPw = new String(checkPwT.getPassword());
		if(e.getSource() == checkBtn) {
			if(getNewPw.equals("")) {
				JOptionPane.showMessageDialog(this, "새 비밀번호를 입력해주세요");
			}else if (getCheckPw.equals("")) {
				JOptionPane.showMessageDialog(this, "새 비밀번호 확인을 입력해주세요");
			}else if(getNewPw.equals(getCheckPw)) {
				JOptionPane.showMessageDialog(this, "비밀번호가 수정되었습니다.");
				setVisible(false);
			}else if(!getNewPw.equals(getCheckPw)) {
				JOptionPane.showMessageDialog(this, "입력하신 비밀번호가 일치하지 않습니다.");
			}
		}else if(e.getSource() == cancelBtn) {
			setVisible(false);
		}		
	}
	
	public void keyEnter() {
		getNewPw = new String(newPwT.getPassword());
		getCheckPw = new String(checkPwT.getPassword());
		if(getNewPw.equals("")) {
			JOptionPane.showMessageDialog(this, "새 비밀번호를 입력해주세요");
		}else if (getCheckPw.equals("")) {
			JOptionPane.showMessageDialog(this, "새 비밀번호 확인을 입력해주세요");
		}else if(getNewPw.equals(getCheckPw)) {
			JOptionPane.showMessageDialog(this, "비밀번호가 수정되었습니다.");
			setVisible(false);
		}else if(!getNewPw.equals(getCheckPw)) {
			JOptionPane.showMessageDialog(this, "입력하신 비밀번호가 일치하지 않습니다.");
		}
	}

}
//-------------------아이디 찾기-----------------------
class IdSearch extends JFrame implements ActionListener {
	private JButton cancelBtn,  confirmNBtn;
	private JLabel nameL, emailL;
	private JTextField nameT, emailT;

	public IdSearch() {

		super("아이디 찾기");

		setLayout(null);
		nameL = new JLabel("이름: ");
		emailL = new JLabel("Email: ");		

		nameT = new JTextField();
		emailT = new JTextField();

		cancelBtn = new JButton("취소");
		confirmNBtn = new JButton("확인");

		nameL.setBounds(60, 30, 80, 20);
		emailL.setBounds(60, 60, 80, 20);
		nameT.setBounds(110, 30, 170, 20);
		emailT.setBounds(110, 60, 170, 20);
		
		cancelBtn.setBounds(170, 110, 90, 30);
		confirmNBtn.setBounds(75, 110, 90, 30);

		Container c = getContentPane();

		c.add(nameL);
		c.add(emailL);
		c.add(nameT);
		c.add(emailT);
		
		c.add(cancelBtn);
		c.add(confirmNBtn);

		confirmNBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		setResizable(false);
		setBounds(750, 250, 350, 175);
		setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = "cucja";
		String name = "구자현";
		String email = "cucja@naver.com";
		if (e.getSource() == confirmNBtn) {
			if (nameT.getText().equals(name) && emailT.getText().equals(email)) {
				this.setVisible(false);
				new IdSearchResult();
			} else if (nameT.getText().equals("") || emailT.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디 또는 이메일을 입력해 주세요!!");
			}else {
				JOptionPane.showMessageDialog(this, "일치하는 정보가 존재하지 않습니다!");
			}
		} else if (e.getSource() == cancelBtn) {
			setVisible(false);
		} 
	}
}
class IdSearchResult extends JFrame implements ActionListener{
	private JLabel idL;
	private JButton cancelBtn,pwSearchBtn;
	
	public IdSearchResult() {
		String id = "cucja";
		
		setLayout(null);
		
		idL = new JLabel("");
		
		cancelBtn = new JButton("취소");
		pwSearchBtn = new JButton("PW찾기");
		Font font = new Font("함초롱돋움",Font.PLAIN,15);
		
		
		idL.setText("아이디는 ' "+id+" '입니다.");
		idL.setFont(font);
		idL.setBounds(80,40,180,30);
		
		
		pwSearchBtn.setBounds(75,110,90,30);
		cancelBtn.setBounds(170,110,90,30);
		
		
		
		Container c = getContentPane();
		
		c.add(idL);
		c.add(cancelBtn);
		c.add(pwSearchBtn);
		
		setResizable(false);
		setVisible(true);
		setBounds(750, 250, 350, 175);
		
		cancelBtn.addActionListener(this);
		pwSearchBtn.addActionListener(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cancelBtn) {
			setVisible(false);
		}else if(e.getSource()==pwSearchBtn) {
			new PwSearch();
			setVisible(false);
		}
	}
}
class LoginDTO {
	private String name, id, pw, pwConfirm, nickName, email, birth, phoneN ,coin; //점수 코인 ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPwConfirm() {
		return pwConfirm;
	}

	public void setPwConfirm(String pwConfirm) {
		this.pwConfirm = pwConfirm;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhoneN() {
		return phoneN;
	}
	public void setPhoneN(String phoneN) {
		this.phoneN=phoneN;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}
}
