import java.util.Vector;
class ShapeDTO   //���� �Ѱ��� ����������� ���ؼ� ����
{	
	private int colorCombo,radio;  //���� ���� ��ư �ε��� ���� ����
	private String x1,x2,y1,y2,z1,z2;  //��ǥ���� ����
	private boolean checkBox; //"ä���" ������ ����
	private Vector <Integer>vector= new <Integer> Vector();  //"�� �׸���"��ǥ���鿡 ���� ������ ���

	//�����ڸ� ���� ��ǥ���� ����
	public void setPoint(String x1,String y1,String x2, String y2, String z1, String z2,Vector vector){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.z1=z1;
		this.z2=z2;
		this.vector= vector;
	}
	//���� ������ ����
	public void setColor(int colorCombo){  
		this.colorCombo= colorCombo;
	}
	//���������� ���� �ε����� ����
	public void setRadio(int radio){
		this.radio = radio;
	}
	//ä��� ������ ���� ������ ����
	public void setCheckBox(boolean checkBox){
		this.checkBox= checkBox;
	}
	//��ǥ ������
	public int getX1(){ return Integer.parseInt(x1); }
	public int getX2(){ return Integer.parseInt(x2); }
	public int getY1(){ return Integer.parseInt(y1); }
	public int getY2(){ return Integer.parseInt(y2); }
	public int getZ1(){ return Integer.parseInt(z1); }
	public int getZ2(){ return Integer.parseInt(z2); }
	public Vector <Integer> getVector(){ return vector; }

	//���� ����
	public int getColor(){  
		return colorCombo;
	}
	//���������� ���� �ε���
	public int getRadio(){
		 return radio;
	}
	//ä��� ������ ���� ����
	public boolean getCheckBox(){
		return checkBox;
	}
}

