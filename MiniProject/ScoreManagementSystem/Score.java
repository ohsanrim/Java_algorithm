import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
interface Score //필요한 인터페이스를 구현(추상 메소드 생성)
{
	public void insert();  //입력 메소드
	
	public DefaultTableModel outputModel(DefaultTableModel model);  //출력매소드
	
	public DefaultTableModel search(String hak, DefaultTableModel searchModel);  //겁색 메소드

	
	public DefaultTableModel to_desc(DefaultTableModel model); //순위메소드(총점으로 내림차순)
	public DefaultTableModel delete(String hak, DefaultTableModel model);  //삭제
	public void save();
	public DefaultTableModel load(DefaultTableModel model);
	/*
	public load();
	public save();
	*/
	
}
