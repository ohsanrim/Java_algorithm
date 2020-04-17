import java.awt.event.*;
import java.awt.*;


class LoginResult extends Frame implements WindowListener
{
	public boolean answer;
	public LoginResult(Boolean answer){  //생성자를 호출
		this.answer = answer;
		setBounds(900,100,200,200);
		setVisible(true);

		//...이벤트
		this.addWindowListener(this);
	}
	public void paint(Graphics g){
		if(answer==true){
			g.drawString("로그인에 성공하셨습니다!",20,100);
		}else {
			g.drawString("로그인에 실패하셨습니다",20,100);
		}
		
	}
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){} //창을 닫은 뒤 사후처리
	public void windowClosing(WindowEvent e){ setVisible(false); }  //x를 누르는 시정
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
}
