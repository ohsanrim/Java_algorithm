package cookierunrun;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//게임이 종료되면 나올 패널
public class EndGame extends JFrame implements ActionListener{
	//라벨 생성 필드
	
	public JButton yesBtn;
	public JLabel gameScoreL;
	public JLabel score;
	public JLabel gomScore;
	public JLabel coinScore;
	//스티링 형식 변환
	public DecimalFormat df = new DecimalFormat("#,##0");
	//패널 선언
	public JPanel p;
	public EndBack end;
	public EndGame() {
		this.setSize(700, 460);
		this.setLayout(null);
		p = new JPanel();
		end = new EndBack();
		p.setOpaque(false);
		p.setLayout(null);
		p.setBounds(0,0,700,500);
		end.setLayout(null);
		end.setBounds(0,0,700,450);
		
		yesBtn = new JButton(new ImageIcon("C:\\cookierun\\png\\Gameresult.jpg"));
		yesBtn.setBounds(245,350,200,60);
		yesBtn.addActionListener(this);
		//score라벨 설정
		score = new JLabel();
		score.setFont(new Font("Segoe UI Black",Font.BOLD,30));
		score.setForeground(Color.gray);
		score.setBounds(200,120,300,100);
		score.setText("SCORE");
		score.setHorizontalAlignment(JLabel.CENTER);
		//GameScore라벨 설정
		gameScoreL = new JLabel();
		gameScoreL.setFont(new Font("Segoe UI Black",Font.BOLD,50));
		gameScoreL.setForeground(Color.BLACK);
		gameScoreL.setBounds(200,140,300,100);
		gameScoreL.setText(df.format(Jelly.gameScore));
		gameScoreL.setHorizontalAlignment(JLabel.CENTER);
		//곰젤리 라벨 설정
		gomScore = new JLabel();
		gomScore.setFont(new Font("Segoe UI Black",Font.BOLD,30));
		gomScore.setForeground(Color.gray);
		gomScore.setBounds(540,205,300,100);
		gomScore.setText(df.format(GomJellyDummy.countGom));
		//코인 라벨 설정
		coinScore = new JLabel();
		coinScore.setFont(new Font("Segoe UI Black",Font.BOLD,30));
		coinScore.setForeground(Color.gray);
		coinScore.setBounds(540,260,300,100);		
		coinScore.setText(df.format(Jelly.coinEat));
		//패널 위에 올리기
		p.add(gomScore);
		p.add(coinScore);
		p.add(gameScoreL);
		p.add(yesBtn);
		end.add(p);
		add(end);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==yesBtn) {
			Jelly.gameScore=0;
			Jelly.coinEat=0;
			Jelly.gameScore=0;
			MyFrame.gameDie=false;
			MovingHurdle.health=100;
			new LobbyClient();
			this.setVisible(false);
			
		}
	}
}
class EndBack extends JPanel {
	public Image scoreBoard;
	public void paintComponent(Graphics g) {
		// 이미지 생성
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit t = Toolkit.getDefaultToolkit();
		scoreBoard = t.getImage("C:\\cookierun\\png\\GameresultBack.jpg");
		g.drawImage(scoreBoard,0,0,this.getWidth(), this.getHeight()-20,this);
		
	}
}
//스코어 옆에 만약 이긴사람의 창에는 win을 띄워주고 진사랑므이 창에는 lose를 뿌려준다. 경험치를 주면 더욱 좋다. 
