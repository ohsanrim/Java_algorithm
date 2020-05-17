import java.awt.event.*;
import java.awt.*;

class Login extends Frame implements ActionListener, WindowListener
{	
	private TextField idT, pwdT;
	private Button login, exit;
	private Label idL, pwdL;
	private final String ID="angel";
	private final String PWD="1004";
	private String id, pwd;
	public boolean loginResult;


	public Login(){
		setLayout(null);
		//라벨과 텍스트필드 생성
		idL = new Label("아이디");
		pwdL = new Label("비밀번호");
		idT= new TextField();
		pwdT= new TextField();
		//라벨과 텍스트 위치 선정
		idL.setBounds(50,100,50,30);
		idT.setBounds(120,100, 100, 30);
		pwdL.setBounds(50,150,50,30);
		pwdT.setBounds(120,150,100,30);
		//버튼 생성
		login = new Button("로그인");
		login.setBounds(90,200,50,30);
		exit = new Button("취소");
		exit.setBounds(150,200,50,30);
		//프레임에 값 추가
		add(exit);
		add(login);
		add(idL); add(idT);
		add(pwdL); add(pwdT);
		//윈도우 창 기본설정
		setBounds(900,100,300,300);
		setVisible(true);
		//이벤트
		this.addWindowListener(this);
		login.addActionListener(this);
		exit.addActionListener(this);
	}
		public void actionPerformed(ActionEvent e){
		//어떤 버튼을 클릭했는지 확인
		if(e.getActionCommand()=="로그인"){
			this.id=idT.getText();
			this.pwd=pwdT.getText();
			idT.setText(" "); idT.setText("");
			pwdT.setText(" "); pwdT.setText("");
			if(ID.equals(id)&&PWD.equals(pwd)){
				loginResult=true;
				new LoginResult(loginResult);
			} else {
				loginResult=false;
				new LoginResult(loginResult);
			}
		} else if(e.getSource()==exit){
			idT.setText(" "); idT.setText("");
			pwdT.setText(" "); pwdT.setText("");
		}
	}
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){} //창을 닫은 뒤 사후처리
	public void windowClosing(WindowEvent e){ setVisible(false); }  //x를 누르는 시정
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}

	public static void main(String[] args) 
	{
		new Login();
	}
}

