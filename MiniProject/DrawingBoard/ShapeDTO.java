import java.util.Vector;
class ShapeDTO   //도형 한개의 모든정보값에 대해서 저장
{	
	private int colorCombo,radio;  //색과 라디오 버튼 인덱스 값을 저장
	private String x1,x2,y1,y2,z1,z2;  //좌표값을 저장
	private boolean checkBox; //"채우기" 유무가 저장
	private Vector <Integer>vector= new <Integer> Vector();  //"펜 그리기"좌표값들에 대한 정보가 담김

	//생성자를 통해 좌표값을 받음
	public void setPoint(String x1,String y1,String x2, String y2, String z1, String z2,Vector vector){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.z1=z1;
		this.z2=z2;
		this.vector= vector;
	}
	//색의 정보를 저장
	public void setColor(int colorCombo){  
		this.colorCombo= colorCombo;
	}
	//도형정보에 대한 인덱스를 저장
	public void setRadio(int radio){
		this.radio = radio;
	}
	//채우기 유무에 대한 정보를 저장
	public void setCheckBox(boolean checkBox){
		this.checkBox= checkBox;
	}
	//좌표 보내기
	public int getX1(){ return Integer.parseInt(x1); }
	public int getX2(){ return Integer.parseInt(x2); }
	public int getY1(){ return Integer.parseInt(y1); }
	public int getY2(){ return Integer.parseInt(y2); }
	public int getZ1(){ return Integer.parseInt(z1); }
	public int getZ2(){ return Integer.parseInt(z2); }
	public Vector <Integer> getVector(){ return vector; }

	//색의 정보
	public int getColor(){  
		return colorCombo;
	}
	//도형정보에 대한 인덱스
	public int getRadio(){
		 return radio;
	}
	//채우기 유무에 대한 정보
	public boolean getCheckBox(){
		return checkBox;
	}
}

