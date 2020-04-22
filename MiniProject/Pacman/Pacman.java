
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
	private int [][] food;  //������ x�� y�� ������ ��ǥ ������ �迭 ����
	private int count;  //���� ���� Ƚ�� ����
	private Image []foodImg= new Image[5];  //Ǫ�� �̹��� �迭 ����
	public Pacman(){
		setBounds(300,300,500,500);
		setVisible(true);
		setResizable(false);
		//������ ��ǥ �迭
		food = new int[5][2];//������ x ���� y�� ��ǥ ����
		for(int i=0;i<food.length;i++){
			for(int j=0;j<2;j++){
				food[i][j]=(int)(Math.random()*401)+50;   //��ǥ ��50~450������ ���� ����
			}
		}
		//...�̺�Ʈ
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
		//...������
		Thread t = new Thread(this);
		t.start();
	}//Pacman()
	
	public void paint(Graphics g){
		Toolkit t = Toolkit.getDefaultToolkit();
		image = t.getImage("pacman.png");
		//���̿� ���� �߻��Ͽ� ��ǥ�� ���� �� �����ӿ� �ø���
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
	//������ run �޼ҵ� �������̵� 
	@Override
	public void run(){
		while(true){
			//�� �Դٰ����ϴ� if�� 
			if(sel%2==0) sel++;
			else sel--;
			//Ű�� ������ �� ���� �������� �ݺ� �̵�
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
			//�ȸ� �߾� ��(��) ��ǥ ����
			MouseX=x+25;
			MouseY=y+25;
			//���̸� ������ ��
			for(int i=0;i<food.length;i++){
				if(food[i][0]+20>MouseX&&food[i][0]<MouseX){   //���� x ���� ���� (�ݰ� 10 �̳�)�� �� ��
					if(food[i][1]+20>MouseY&&food[i][1]<MouseY){  //���� y ���� ���� (�ݰ� 10 �̳�)�� �� ��
						food[i][0]=0; food[i][1]=0;  //0,0���� ��ǥ ��ġ �ű��
						count++;
					}
				}
			}
			//���̸� �� �Ծ��� �� ������ ����
			if(count==5){
			break;
			}
			repaint();
			//Exception ���
			try{
			 Thread.sleep(80);
			} catch (InterruptedException e){ }
		}
	}
	//main �޼ҵ�
	public static void main(String[] args) 
	{
		new Pacman();
	}
}
