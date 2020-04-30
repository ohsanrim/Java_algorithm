import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


class ScoreForm extends JFrame implements ActionListener
{
	private JLabel hakL, nameL, korL, engL, mathL;
	private JLabel [] allLabel;
	private JButton bt1, bt2, bt3, bt4, bt5, bt6,bt7;
	private JButton [] allBtn;
	private JTextField hakT, nameT, korT, engT, mathT;
	private JTextField [] allText;
	private DefaultTableModel model;
	//private DefaultTableModel modelTemp;  //model ����ǰ
	private Vector <String> head;
	private JTable table;
	private ScoreImple si;
	//private Vector <ArrayList> addTable;
	
	public ScoreForm(){
		si= new ScoreImple(this);
		//�ϴܿ� ��ư ����
		JPanel bottom = new JPanel(new GridLayout(1,5,0,0)); 
		bt1=new JButton("�Է�"); 
		bt2=new JButton("���"); 
		bt3=new JButton("�й��˻�"); 
		bt4=new JButton("����"); 
		bt5=new JButton("����"); 
		bt6=new JButton("��������");
		bt7=new JButton("���Ͽ���"); 
		allBtn = new JButton[]{bt1,bt2,bt3,bt4,bt5,bt6,bt7};
		//��ư �гο� ���̱�
		for(int i=0;i<allBtn.length;i++){
			bottom.add(allBtn[i]);
		}
		add("South",bottom);

		//���̺� ����
		head = new Vector<String>();
		head.add("�й�");
		head.add("�̸�");
		head.add("����");
		head.add("����");
		head.add("����");
		head.add("����");
		head.add("���");
//		modelTemp = new DefaultTableModel(head,0);
		model = new DefaultTableModel (head,0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		
		//�߾� ���̾ƿ�
		JPanel center = new JPanel(new GridLayout(1,2,0,0));
		JPanel center1= new JPanel(new GridLayout(5,1,0,0));  //�󺧰� �ؽ�Ʈ �ʵ尡 ���
		JPanel []leftP= new JPanel[5];
		for(int i=0;i<leftP.length;i++){
			leftP[i] = new JPanel();
		}
		//�� ����
		hakL=new JLabel("�й�");
		nameL=new JLabel("�̸�");
		korL=new JLabel("����");
		engL=new JLabel("����");
		mathL=new JLabel("����");
		allLabel= new JLabel[]{ hakL,nameL, korL, engL, mathL };
		//�ؽ�Ʈ �ʵ� ����
		hakT= new JTextField("",20);
		nameT= new JTextField("",20);
		korT= new JTextField("",20);
		engT= new JTextField("",20);
		mathT= new JTextField("",20);
		allText = new JTextField[]{ hakT, nameT, korT, engT, mathT };
		//�гο� �󺧰� �ؽ�Ʈ �ʵ� �ø���
		for(int i=0;i<leftP.length;i++){
			leftP[i].add("West",allLabel[i]);
			leftP[i].add("Center",allText[i]);
		}
		//������ ���� �ø���
		for(int i=0;i<5;i++){
			center1.add(leftP[i]);
		}
		center.add(center1); center.add(scroll);
		add(center);

		//������ â ����
		setBounds(300,500,600,300);
		setVisible(true);
		//��ư�� �̺�Ʈ ����
		for(int i=0;i<allBtn.length;i++){
			allBtn[i].addActionListener(this);
		}

		//�̺�Ʈ
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ System.exit(0); }
		});
		

	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==bt1){  //�Է�
			JOptionPane.showConfirmDialog(ScoreForm.this,"������ �Է��մϴ�","����", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
			si.insert();
			for(int i=0;i<allText.length;i++){
				allText[i].setText("");
			}
		} else if(e.getSource()==bt2){ //���
			model=si.outputModel(model);
		} else if(e.getSource()==bt3){
			String answer=JOptionPane.showInputDialog(this,"�й��� �Է��ϼ���");
			if(answer!=null){
				model=si.search(answer,model);
			}
		} else if(e.getSource()==bt4){  //�˻�
			 model=si.to_desc(model);
		} else if (e.getSource()==bt5){  //����
			String answer=JOptionPane.showInputDialog(this,"������ �й��� �Է��ϼ���");
			if(answer!=null) model=si.delete(answer,model);
		} else if (e.getSource()==bt6){  //���� ����
			si.save();
		} else if (e.getSource()==bt7){  //���� ����
			model=si.load(model);
		} 
	}
	public String getHak(){ return hakT.getText(); }
	public String getName(){ return nameT.getText(); }
	public int getKor(){ return Integer.parseInt(korT.getText()); }
	public int getEng(){ return Integer.parseInt(engT.getText()); }
	public int getMath(){ return Integer.parseInt(mathT.getText()); }
}
