import java.awt.*;
import java.awt.event.*;
class ImageMove extends Frame implements ActionListener, KeyListener, WindowListener
{
	//...������� ���콺 ��ǥ
	private int x=100;
	private int y=100; 
	private Button newBtn, leftBtn, rightBtn, topBtn, bottomBtn;
	
	public void init(){
		//��ư 5�� ����
		newBtn = new Button("New");
		leftBtn = new Button("Left");
		rightBtn = new Button("Right");
		topBtn = new Button("Top");
		bottomBtn = new Button("Bottom");

		//...�гο� ��ư �ֱ�
		Panel p = new Panel();
		p.add(newBtn); p.add(leftBtn); p.add(rightBtn);p.add(topBtn);p.add(bottomBtn);
		add("North", p);

		//������ â ����
		setBounds(900,100,500,500);
		setVisible(true);
		setResizable(false);

		//....�̺�Ʈ
		newBtn.addActionListener(this);
		leftBtn.addActionListener(this);
		rightBtn.addActionListener(this);
		topBtn.addActionListener(this);
		bottomBtn.addActionListener(this);
		this.addKeyListener(this);
		this.addWindowListener(this);
	}

	//...paint�޼ҵ�
	public void paint(Graphics g){
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("airplane.jpg");
		g.drawImage(image, x,y,this);
	}

	//...KeyListner�� �߻�޼ҵ�
	public void	keyPressed(KeyEvent e){
		if(e.getKeyCode()==e.VK_LEFT){
			if(x<-50){ x=520;} 
			else { x-=10; }
		} else if(e.getKeyCode()==e.VK_RIGHT){
			if(x>520){ x=-50; } 
			else { x+=10; }
		} else if(e.getKeyCode()==e.VK_UP){
			if(y<-50){ y=510; } 
			else { y-=10; }
		} else if(e.getKeyCode()==e.VK_DOWN){
			if(y>520){ y=-50; }
			else { y+=10; }
		}
		repaint();
	}
	public void	keyReleased(KeyEvent e){}
	public void	keyTyped(KeyEvent e){}	
	
	//...ActionListner�� �߻�޼ҵ�
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="New"){ x=100; y=100; } 
		else if(e.getActionCommand()=="Left"){
			if(x<-50){ x=520;} 
			else { x-=10; }
		} else if(e.getActionCommand()=="Right"){
			if(x>520){ x=-50; } 
			else { x+=10; }
		} else if(e.getActionCommand()=="Top"){
			if(y<-50){ y=510; } 
			else { y-=10; }
		} else if(e.getActionCommand()=="Bottom"){
			if(y>520){ y=-50; }
			else { y+=10; }
		}
		repaint();
		//Keyistener�� ��Ŀ���� �����ʴ� ���� ������ ���� ��Ŀ�� ó��
		requestFocus();
	}
	//...windowListener�� �߻�޼ҵ�
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){} //â�� ���� �� ����ó��
	public void windowClosing(WindowEvent e){ System.exit(0); }  //x�� ������ ����
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}

	//...���θ޼ҵ�
	public static void main(String[] args) 
	{	
		new ImageMove().init();
	}
}

