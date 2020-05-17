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
		//�󺧰� �ؽ�Ʈ�ʵ� ����
		idL = new Label("���̵�");
		pwdL = new Label("��й�ȣ");
		idT= new TextField();
		pwdT= new TextField();
		//�󺧰� �ؽ�Ʈ ��ġ ����
		idL.setBounds(50,100,50,30);
		idT.setBounds(120,100, 100, 30);
		pwdL.setBounds(50,150,50,30);
		pwdT.setBounds(120,150,100,30);
		//��ư ����
		login = new Button("�α���");
		login.setBounds(90,200,50,30);
		exit = new Button("���");
		exit.setBounds(150,200,50,30);
		//�����ӿ� �� �߰�
		add(exit);
		add(login);
		add(idL); add(idT);
		add(pwdL); add(pwdT);
		//������ â �⺻����
		setBounds(900,100,300,300);
		setVisible(true);
		//�̺�Ʈ
		this.addWindowListener(this);
		login.addActionListener(this);
		exit.addActionListener(this);
	}
		public void actionPerformed(ActionEvent e){
		//� ��ư�� Ŭ���ߴ��� Ȯ��
		if(e.getActionCommand()=="�α���"){
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
	public void windowClosed(WindowEvent e){} //â�� ���� �� ����ó��
	public void windowClosing(WindowEvent e){ setVisible(false); }  //x�� ������ ����
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}

	public static void main(String[] args) 
	{
		new Login();
	}
}

