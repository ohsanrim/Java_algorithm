import java.util.*;
import java.io.*;
class  ScoreDTO implements Serializable, Comparable <ScoreDTO>
{
	private String hak;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg;
	public ScoreDTO(String hak, String name, int kor, int eng, int math){
		this.name=name;
		this.hak=hak;
		this.kor=kor;
		this.eng=eng;
		this.math=math;
		this.tot=kor+eng+math;
		this.avg= tot/3;
	}

	public String getName(){ return name; }
	public String getHak(){ return hak; }
	public int getKor(){ return kor; }
	public int getEng(){ return eng; }
	public int getMath(){ return math; }
	public int getTot(){ return tot; }
	public double getAvg(){ return avg; }

	public int compareTo(ScoreDTO o){  //compareTo �������̵�(���̸� ��������)
        if(getTot()<o.getTot()) return -1;
        else if(getTot()==o.getTot()) return 0;
        else return 1;
    }
	 @Override
	public String toString() {
		return hak +  "," + name +"," + kor+"," + eng+"," + math+"," + tot+"," + avg;
	}
}
/*
ScoreForm.java  ���̾�常
Score.java  implements 
ScoreDTO     .java
Scoreimpl
ScoreMain
�й�, �̸�, ����, ����, ����
*/