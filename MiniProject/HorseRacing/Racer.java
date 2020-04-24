import java.awt.*;
class Racer extends Canvas implements Runnable//말 한마리
{	
	private String name;
	private Image image;
	
	private int x=0;
	// 생성자
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
		//for 문으로 600까지 실행시켜주어도 가능
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
		System.out.println(rr.rank+"등 "+name+"말");
		rr.rank++;
	}
}
