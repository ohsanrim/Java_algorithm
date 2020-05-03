import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
class RunRace extends Frame implements ActionListener
{
	private JButton bt;
	private Thread t;
	private Racer [] r;
	private long []time;
	public static int rank=1;
	public RunRace(){}  //�⺻ ������ 
	public RunRace(String[] args){
		super("�� ����");
		
		//��ü �г�
		JPanel whole = new JPanel(new GridLayout(args.length-1,1,5,5));
		//�� ������ŭ ����
		r = new Racer[args.length-1];
		for(int i=0;i<r.length;i++){
			r[i]= new Racer(args[i+1]);
			r[i].setBackground(new Color(204,189,242));
			whole.add(r[i]);
		}
		add("Center",whole);
		//��ư
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5)); 
//
		bt = new JButton("���");
		bottom.add(bt);
		add("South",bottom);
		bt.addActionListener(this);
		
		//�̺�Ʈ
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ System.exit(0); }
		});
		setBounds(300,300,600,400);
		setVisible(true);
		setResizable(false);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("���")){
			for(int i=0;i<r.length;i++){
				r[i].startThread();
			}
		}
	}
	public static void main(String[] args) 
	{
		if(args.length<2){  //���ڰ� 2�� �̻� ������ �ʾ��� ��
			System.out.println("Usage: java RunRace count name1, ...");
			System.exit(0);
		}
		if(Integer.parseInt(args[0])!=args.length-1){
			System.out.println("�� �������� ������ ���� �ʽ��ϴ�.");
			System.exit(0);
		}
		new RunRace(args);
	}
}
