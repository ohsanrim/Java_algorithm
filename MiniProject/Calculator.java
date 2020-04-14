import java.awt.Frame;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.*;
class Calculator extends Frame 
{	
	private Label dispL, inputL;
	private Button[] button;  //18개
	
	public Calculator(){
		super("미니계산기");
		Panel whole = new Panel();  //전체
		
	    Panel p1 = new Panel();  
	    Panel p2 = new Panel();  
		Panel p3 = new Panel(); 
		Panel p4 = new Panel();
		Panel p5 = new Panel(); 
		Panel p6 = new Panel();
		Panel p7 = new Panel();
		
		//.....버튼
		String[] buttonName = {"7","8","9","/","4","5","6","*","1","2","3","-",".","0","=","+","CE","C"};
		button = new Button[18];
		for(int i=0;i<18;i++){
			 button[i]=new Button(buttonName[i]);
		}
		//....라벨
		dispL= new Label();
		dispL.setBackground(new Color(139,158,226));
		inputL= new Label();
		inputL.setBackground(new Color(139,158,226));
		
		
		//....패널
		whole.setLayout(new GridLayout(7,1,5,5));
		p1.setLayout(new GridLayout(1,1,0,0));
		p1.add(dispL);
		p2.setLayout(new GridLayout(1,1,0,0));
		p2.add(inputL);
		p3.setLayout(new GridLayout(1,2,5,0));
		p3.add(button[16]);
		p3.add(button[17]);
		p4.setLayout(new GridLayout(1,4,5,0));
		for(int i=0;i<4;i++) {
			p4.add(button[i]);
		}
		p5.setLayout(new GridLayout(1,4,5,0));
		for(int i=4;i<8;i++) {
			p5.add(button[i]);
		}
		p6.setLayout(new GridLayout(1,4,5,0));
		for(int i=8;i<12;i++) {
			p6.add(button[i]);
		}
		p7.setLayout(new GridLayout(1,4,5,0));
		for(int i=12;i<16;i++) {
			p7.add(button[i]);
		}
		
		whole.add(p1);
		whole.add(p2);
		whole.add(p3);
		whole.add(p4);
		whole.add(p5);
		whole.add(p6);
		whole.add(p7);
		add("Center", whole);
		
		setBounds(900,180,300,500);
		setBackground(new Color(105,132,224));
		setVisible(true);
		
	}

	public static void main(String[] args) 
	{
		Calculator c = new Calculator();
	}
}
