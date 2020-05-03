import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
class MsPaint extends JFrame implements ActionListener
{	
	private JLabel x1L, x2L, y1L,y2L, z1L, z2L;
	private JTextField x1T, x2T, y1T, y2T, z1T, z2T;
	private JCheckBox fill;
	private JRadioButton line, circle, rect, roundRect, pen;
	private JComboBox<String> combo;
	private JButton draw, newDraw;
	private DrCanvas can;
	private JRadioButton [] radioAll={line, circle, rect, roundRect, pen};
	private int radioChoice;
	private String [] ComboName={"빨강","초록","파랑","보라","하늘"};
	private JTextField [] textFieldAll;
	private Graphics g;
	private ArrayList<ShapeDTO> list = new ArrayList<ShapeDTO>();
	private int x1Temp, y1Temp;
	private Vector<Integer> vector = new Vector<Integer>();  //펜으로 그릴 때 좌표값

	public MsPaint(){
		super("미니 그림판");
		//라벨 설정
		x1L= new JLabel("x1",SwingConstants.RIGHT);
		y1L= new JLabel("Y1",SwingConstants.RIGHT);
		x2L= new JLabel("X2",SwingConstants.RIGHT);
		y2L= new JLabel("Y2",SwingConstants.RIGHT);
		z1L= new JLabel("Z1",SwingConstants.RIGHT);
		z2L= new JLabel("Z2",SwingConstants.RIGHT);
		JLabel [] LabelAll = {x1L,y1L,x2L,y2L, z1L, z2L};
		//텍스트 필드 설정(크기를 4로 지정)
		x1T= new JTextField("0"); 
		x2T= new JTextField("0");
		y1T= new JTextField("0");
		y2T= new JTextField("0");
		z1T= new JTextField("50");
		z2T= new JTextField("50");
		textFieldAll= new JTextField[]{x1T, y1T, x2T, y2T, z1T, z2T};
		//체크박스
		fill= new JCheckBox("채우기");
		//컨테이너 생성
		Container c = this.getContentPane();
		
		//상단부
		JPanel p = new JPanel(new GridLayout(1,13,0,0));
		for(int i=0;i<LabelAll.length;i++){
			LabelAll[i].setSize(100,100);
			textFieldAll[i].setSize(100,100);
			p.add(LabelAll[i]);
			p.add(textFieldAll[i]);
		}
		p.add(fill);
		
		//캔버스
		can = new DrCanvas(this);
		
		// 하단부
		JPanel bottom = new JPanel(new GridLayout(1,8,5,5)); 
		//라디오 버튼 중복 선택을 막기 위한 ButtonGroup 설정
		ButtonGroup bg = new ButtonGroup();
		line= new JRadioButton("선", true);  //true로 해 놓으면 프로그램 실행 시 항상 선택되어 싫행된다. 
		circle= new JRadioButton("원"); 
		rect= new JRadioButton("사각형"); 
		roundRect= new JRadioButton("둥근사각형");
		pen= new JRadioButton("펜");
		radioAll=new JRadioButton[]{line, circle, rect, roundRect, pen};

		combo = new JComboBox<String>(ComboName);//콤보박스 설정 필요
		draw = new JButton("그리기");
		newDraw = new JButton("모두 지우기");
		draw.addActionListener(this);
		newDraw.addActionListener(this);
		for(int i=0;i<radioAll.length;i++){
			bg.add(radioAll[i]);
			bottom.add(radioAll[i]);
			radioAll[i].addActionListener(this);
		}
		bottom.add(combo);
		bottom.add(draw);
		bottom.add(newDraw);

		//컨테이너 위에 올리기
		c.add("North",p);
		c.add("Center",can);
		c.add("South", bottom);
		//이벤트
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ System.exit(0); }
		});
		//마우스 이벤트
		can.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){ // 눌린순간
				x1T.setText(Integer.toString(e.getX()));
				y1T.setText(Integer.toString(e.getY()));
				x2T.setText(x1T.getText());
				y2T.setText(y1T.getText());
				if(getRadio()==4){
					x1Temp=e.getX();
					y1Temp=e.getY();
					vector.add(e.getX());
					vector.add(e.getY());
				}
			}
			public void mouseReleased(MouseEvent e){
				if((!x1T.getText().equals(x2T.getText())&&!y1T.getText().equals(y2T.getText()))||getRadio()==4){
					ShapeDTO s = new ShapeDTO();
					s.setPoint(x1T.getText(),y1T.getText(),x2T.getText(),y2T.getText(),z1T.getText(),z2T.getText(),vector);
					s.setColor(combo.getSelectedIndex());
					s.setCheckBox(fill.isSelected());
					s.setRadio(getRadio());
					list.add(s);
				} 
			}
		});
		can.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){ // 드래그일시
				if(getRadio()==4){  //펜이 실행될 경우에는 직접 캔버스로 그리기
					g=can.getGraphics();
					//펜의 색 설정
					switch(getComboIndex()){
						//this.setForeground(new Color(255,0,0)); break;  로 써도 색이 입혀진다.
						case 0: g.setColor(new Color(255,0,0)); break;
						case 1: g.setColor(new Color(34,177,76)); break;
						case 2: g.setColor(new Color(0,0,255)); break;
						case 3: g.setColor(new Color(160,50,143)); break;
						case 4: g.setColor(new Color(91,198,198)); break;
					}
					//펜 그리기
					g.drawLine(x1Temp,y1Temp,e.getX(),e.getY());
					x1Temp=e.getX();
					y1Temp= e.getY();
					vector.add(e.getX());
					vector.add(e.getY());
				} else {
					x2T.setText(Integer.toString(e.getX()));
					y2T.setText(Integer.toString(e.getY()));
					can.repaint();
				}
			}
		});
		
		setBounds(300,600,900,500);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	//ActionPerformed 오버라이드
	@Override
	public void actionPerformed(ActionEvent e){
		String s = e.getActionCommand();  
		if(s.equals("그리기")){
			can.repaint();

		}  
		else if(s.equals("모두 지우기")){  //초기화 작업 진행
			list.clear();
			vector.clear();
			x1T.setText("0");
			y1T.setText("0");
			x2T.setText("0");
			y2T.setText("0");
			x1Temp=0;
			y1Temp=0;
			can.repaint();
		}
	}
	//좌표값 보내는 메소드
	public JTextField getX1T(){ return x1T; }
	public JTextField getX2T(){ return x2T; }
	public JTextField getY1T(){ return y1T; }
	public JTextField getY2T(){ return y2T; }
	public JTextField getZ1T(){ return z1T; }
	public JTextField getZ2T(){ return z2T; }
	public int getx1Temp(){ return x1Temp; }
	public int gety1Temp(){ return y1Temp; }
	//콤보박스와 체크박스 전송
	public int getComboIndex(){ 	return combo.getSelectedIndex(); }
	public boolean getCheckBox(){ return fill.isSelected(); }
	public int getRadio(){
		int number=0;
		for(int i=0;i<radioAll.length;i++){
			if(radioAll[i].isSelected()){
				number= i;
			}
		}
		return number;
	}
	public ArrayList<ShapeDTO> saveList(){ return list; }
	
	//메인
	public static void main(String[] args) 
	{
		MsPaint mp = new MsPaint();
	}
}