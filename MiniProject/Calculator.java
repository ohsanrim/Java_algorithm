import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
class Calculator extends Frame implements WindowListener, ActionListener
{	
	private Label dispL, inputL;
	private JButton[] button;  //18��
	StringBuffer sb= new StringBuffer();
	DecimalFormat df= new DecimalFormat("#,##0.###############");  //�Ҽ��� ���� 15�ڸ����� ǥ��
	
	public double number;

	public Calculator(){
		super("�̴� ����");

		Panel whole = new Panel();  //��ü
	    Panel p1 = new Panel();  
	    Panel p2 = new Panel();  
		Panel p3 = new Panel(); 
		Panel p4 = new Panel();
		Panel p5 = new Panel(); 
		Panel p6 = new Panel();
		Panel p7 = new Panel();
		
		//.....��ư
		String[] buttonName = {"7","8","9","/","4","5","6","*","1","2","3","-",".","0","=","+","Back","C"};
		button = new JButton[18];
		for(int i=0;i<18;i++){
			 button[i]=new JButton(buttonName[i]);
		}
		
		//....��
		dispL= new Label("0",Label.RIGHT);
		dispL.setBackground(new Color(139,158,226));
		inputL= new Label("0", Label.RIGHT);
		inputL.setBackground(new Color(139,158,226));
		
		//....�г�
		whole.setLayout(new GridLayout(7,1,5,5));
		p1.setLayout(new GridLayout(1,1,5,5));
		p1.add(dispL);
		p2.setLayout(new GridLayout(1,1,5,5));
		p2.add(inputL);
		p3.setLayout(new GridLayout(1,2));
		p3.add(button[16]);
		p3.add(button[17]);
		p4.setLayout(new GridLayout(1,4));
		for(int i=0;i<4;i++) { p4.add(button[i]); }
		p5.setLayout(new GridLayout(1,4));
		for(int i=4;i<8;i++) { p5.add(button[i]); }
		p6.setLayout(new GridLayout(1,4));
		for(int i=8;i<12;i++) { p6.add(button[i]); }
		p7.setLayout(new GridLayout(1,4));
		for(int i=12;i<16;i++) { p7.add(button[i]);	}
		whole.add(p1);
		whole.add(p2);
		whole.add(p3);
		whole.add(p4);
		whole.add(p5);
		whole.add(p6);
		whole.add(p7);
		add("Center", whole);
		//...������ â ����
		setBounds(900,180,350,500);
		setBackground(new Color(105,132,224));
		setVisible(true);
		//...�̺�Ʈ ����
		for(int i=0;i<18;i++){ button[i].addActionListener(this); }
		this.addWindowListener(this);
	}

		//...actionPerFormed �̺�Ʈ
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand()=="1"){ sb.append(1); } 
			else if(e.getActionCommand()=="2"){ sb.append(2); }
			else if(e.getActionCommand()=="3"){ sb.append(3); }
			else if(e.getActionCommand()=="4"){ sb.append(4); }
			else if(e.getActionCommand()=="5"){ sb.append(5); }
			else if(e.getActionCommand()=="6"){ sb.append(6); }
			else if(e.getActionCommand()=="7"){ sb.append(7); }
			else if(e.getActionCommand()=="8"){ sb.append(8); }
			else if(e.getActionCommand()=="9"){ sb.append(9); }
			else if(e.getActionCommand()=="0"){ sb.append(0); }
			else if(e.getActionCommand()=="C"){ sb.delete(0,sb.length()); }
			else if(e.getActionCommand()=="Back"){ 
				if(sb.length()>0) sb=sb.delete(sb.length()-1,sb.length());
				else sb.delete(0,sb.length());
			}
			else if(e.getActionCommand()=="."){ 
				if(sb.indexOf(".")==-1){
					if(inputL.getText().equals("0")){
						sb.append("0.");
					} else{
						sb.append(".");
					}
				} 
			}
			//...StringBuffer�� ���� �ӽ÷� ���� temp ����
			String temp;
			//...StringBufferd �ȿ� �ִ� ���� �����ϴ��� ���θ� üũ
			if(sb.length()>0){
				//StringBuffer �ȿ� �Ҽ����� ���� ��(�Ҽ����� �������� ���� �����κ��� decimalFormat����)
				if(sb.indexOf(".")==sb.length()-1){
					temp=sb.substring(0,sb.indexOf("."));
					temp=df.format(Integer.parseInt(temp));
					temp+=".";
					inputL.setText(temp);
				} else {
					temp=sb.toString();
					temp=df.format(Double.parseDouble(temp));
					inputL.setText(temp);
				}
			} else inputL.setText("0");
		}

		//...windowListener�� �߻�޼ҵ�
		public void windowActivated(WindowEvent e){}
		public void windowClosed(WindowEvent e){} //â�� ���� �� ����ó��
		public void windowClosing(WindowEvent e){ System.exit(0); }  //x�� ������ ����
		public void windowDeactivated(WindowEvent e){}
		public void windowDeiconified(WindowEvent e){}
		public void windowIconified(WindowEvent e){}
		public void windowOpened(WindowEvent e){}

	public static void main(String[] args) 
	{
		Calculator c = new Calculator();
		
	}
}
