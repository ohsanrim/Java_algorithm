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
	public RunRace(){}  //기본 생성자 
	public RunRace(String[] args){
		super("말 경주");
		
		//전체 패널
		JPanel whole = new JPanel(new GridLayout(args.length-1,1,5,5));
		//말 개수만큼 생성
		r = new Racer[args.length-1];
		for(int i=0;i<r.length;i++){
			r[i]= new Racer(args[i+1]);
			r[i].setBackground(new Color(204,189,242));
			whole.add(r[i]);
		}
		add("Center",whole);
		//버튼
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5)); 
//
		bt = new JButton("출발");
		bottom.add(bt);
		add("South",bottom);
		bt.addActionListener(this);
		
		//이벤트
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ System.exit(0); }
		});
		setBounds(300,300,600,400);
		setVisible(true);
		setResizable(false);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("출발")){
			for(int i=0;i<r.length;i++){
				r[i].startThread();
			}
		}
	}
	public static void main(String[] args) 
	{
		if(args.length<2){  //인자가 2개 이상 들어오지 않았을 때
			System.out.println("Usage: java RunRace count name1, ...");
			System.exit(0);
		}
		if(Integer.parseInt(args[0])!=args.length-1){
			System.out.println("말 마리수와 개수가 맞지 않습니다.");
			System.exit(0);
		}
		new RunRace(args);
	}
}
