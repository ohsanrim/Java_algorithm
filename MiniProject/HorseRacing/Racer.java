import java.awt.*;
class Racer extends Canvas implements Runnable//�� �Ѹ���
{	
	private String name;
	private Image image;
	
	private int x=0;
	// ������
	public Racer(String name){  
		this.name= name;
	}
	public void paint(Graphics g){
		super.paint(g);
		Toolkit t = Toolkit.getDefaultToolkit();
		image = t.getImage("horse.GIF");
		g.drawImage(image,x,0,80,getHeight(),this);
	}
	public void startThread(){
		Thread t= new Thread(this);
		t.setPriority((int)(Math.random()*10)+1);
		t.start();
	}
	public String getName(){
		return name;
	}
	public void run(){
		//for ������ 600���� ��������־ ����
		while(true){
			x+= (int)(Math.random()*6)+5;
			repaint();
			try{
				Thread.sleep((int)(Math.random() * 1000));
			} catch(InterruptedException e){
			}
			if(x>515){
				break;
			}
		}
		RunRace rr = new RunRace();
		System.out.println(rr.rank+"�� "+name+"��");
		rr.rank++;
	}
}
