
import java.awt.event.*;
import java.awt.*;
class Pacman extends Frame implements Runnable
{	
	private Image image;
	private int sel=2;
	private int x=225;
	private int y=225;
	private int MouseX;
	private int MouseY;
	private int [][] food;  //먹이의 x와 y값 난수의 좌표 저장할 배열 생성
	private int count;  //먹이 먹은 횟수 저장
	private Image []foodImg= new Image[5];  //푸드 이미지 배열 생성
	public Pacman(){
		setBounds(300,300,500,500);
		setVisible(true);
		setResizable(false);
		//먹이의 좌표 배열
		food = new int[5][2];//먹이의 x 값과 y값 좌표 저장
		for(int i=0;i<food.length;i++){
			for(int j=0;j<2;j++){
				food[i][j]=(int)(Math.random()*401)+50;   //좌표 값50~450사이의 난수 생성
			}
		}
		//...이벤트
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ System.exit(0); }
		});
		this.addKeyListener(new KeyAdapter(){
			public void	keyPressed(KeyEvent e){
				if(e.getKeyCode()==e.VK_LEFT){
					sel=0;
				} else if(e.getKeyCode()==e.VK_RIGHT){
					sel=2;
				} else if(e.getKeyCode()==e.VK_UP){
					sel=4;
				} else if(e.getKeyCode()==e.VK_DOWN){
					sel=6;
				}
				repaint();
			}
		});
		//...스레드
		Thread t = new Thread(this);
		t.start();
	}//Pacman()
	
	public void paint(Graphics g){
		Toolkit t = Toolkit.getDefaultToolkit();
		image = t.getImage("pacman.png");
		//먹이에 난수 발생하여 좌표값 지정 후 프레임에 올리기
		for(int i=0;i<5;i++){
			int x;
			int y;
			foodImg[i]=t.getImage("food.png");
			x=food[i][0];
			y=food[i][1];
			g.drawImage(foodImg[i], x,y,this);
		}
		g.drawImage(image, x,y,x+50,y+50,sel*50,0,sel*50+50,50,this);
		g.drawImage(image, x,y,x+50,y+50,sel*50,0,sel*50+50,50,this);
	}
	//스레드 run 메소드 오버라이드 
	@Override
	public void run(){
		while(true){
			//입 왔다갔다하는 if문 
			if(sel%2==0) sel++;
			else sel--;
			//키를 눌렀을 때 한쪽 방향으로 반복 이동
			if(sel==0){
				if(x<-50){ x=520;} 
				else { x-=10; }
			} else if(sel==2){
				if(x>520){ x=-50; }  
				else { x+=10; }
			} else if(sel==4){
				if(y<-50){ y=510; } 
				else { y-=10; }
			} else if(sel==6){
				if(y>520){ y=-50; }
				else { y+=10; }
			}
			//픽맨 중앙 값(입) 좌표 저장
			MouseX=x+25;
			MouseY=y+25;
			//먹이를 지나갈 때
			for(int i=0;i<food.length;i++){
				if(food[i][0]+20>MouseX&&food[i][0]<MouseX){   //먹이 x 값의 범주 (반경 10 이내)에 들 때
					if(food[i][1]+20>MouseY&&food[i][1]<MouseY){  //먹이 y 값의 범주 (반경 10 이내)에 들 때
						food[i][0]=0; food[i][1]=0;  //0,0으로 좌표 위치 옮기기
						count++;
					}
				}
			}
			//먹이를 다 먹었을 때 스레드 종료
			if(count==5){
			break;
			}
			repaint();
			//Exception 잡기
			try{
			 Thread.sleep(80);
			} catch (InterruptedException e){ }
		}
	}
	//main 메소드
	public static void main(String[] args) 
	{
		new Pacman();
	}
}
