package cookierunrun;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
/*

*/
class MyFrame2 extends JFrame implements ActionListener, KeyListener{
	//시작 화면 
	
	public JButton startBtn;
	public Image round1;
	public JLayeredPane lp;
	public Image startImg;
	public JPanel p;
	public BackgroundT b;
	public ImageIcon backgroundImg;
	public RunningCookie runningCookie;
	public MovingHurdle movingHurdle;
	public HpBar hpBar;
	
	
	public MyFrame2() {
		
		p = new JPanel();
		startBtn = new JButton("시작");
		p.add("Center",startBtn);
		startBtn.addActionListener(this);
		add("South",startBtn);
		// 게임구현부
		movingHurdle = new MovingHurdle();
		movingHurdle.setting();
		b = new BackgroundT();
		b.setLayout(null);
		runningCookie = new RunningCookie();
		runningCookie.setBounds(0,0,700,500); 
		hpBar = new HpBar();
		hpBar.setLayout(null);
		hpBar.setBounds(0,0,700,500); 
		
		movingHurdle.setBounds(0,0,700,500);
		movingHurdle.setLayout(null);
		movingHurdle.add(runningCookie);
		hpBar.add(movingHurdle);
		b.add(hpBar);
		add(b);
		//장애물 리스트 받기
		
		setBounds(200,300,700,500);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startBtn.setFocusable(false);
		this.setResizable(true);
		this.addKeyListener(this);
	}
	public static void main(String[] args) {
		new MyFrame2();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("시작")) {
			System.out.println("시작 클릭");
			p.setVisible(false);  //버튼 안보이게 숨기기
			b.threadStart();
			this.requestFocus();
			runningCookie.setFocusable(true);
			hpBar.threadStart();
			runningCookie.threadStart();
			movingHurdle.threadStart();
		}
	}
	
	//KeyListener 오버라이드
		@Override
		public void keyPressed(KeyEvent e) {
			//눌렀을 떄
			if(e.getKeyCode()==e.VK_DOWN) {
				if(!runningCookie.jump)  //슬라이드 중이 아닐 떄
				runningCookie.setSlide(true);
			} else if(e.getKeyCode()==e.VK_UP) {
				if(!runningCookie.slide)  //슬라이드 중이 아닐 떄
				runningCookie.setJump(true);
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			runningCookie.setSlide(false);
		}
		@Override
		public void keyTyped(KeyEvent e) {} 
		
	

}