import java.awt.event.*;
import java.awt.*;


class LoginResult extends Frame implements WindowListener
{
	public boolean answer;
	public LoginResult(Boolean answer){  //�����ڸ� ȣ��
		this.answer = answer;
		setBounds(900,100,200,200);
		setVisible(true);

		//...�̺�Ʈ
		this.addWindowListener(this);
	}
	public void paint(Graphics g){
		if(answer==true){
			g.drawString("�α��ο� �����ϼ̽��ϴ�!",20,100);
		}else {
			g.drawString("�α��ο� �����ϼ̽��ϴ�",20,100);
		}
		
	}
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){} //â�� ���� �� ����ó��
	public void windowClosing(WindowEvent e){ setVisible(false); }  //x�� ������ ����
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
}
