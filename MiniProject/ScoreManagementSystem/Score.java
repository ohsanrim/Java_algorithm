import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
interface Score //�ʿ��� �������̽��� ����(�߻� �޼ҵ� ����)
{
	public void insert();  //�Է� �޼ҵ�
	
	public DefaultTableModel outputModel(DefaultTableModel model);  //��¸żҵ�
	
	public DefaultTableModel search(String hak, DefaultTableModel searchModel);  //�̻� �޼ҵ�

	
	public DefaultTableModel to_desc(DefaultTableModel model); //�����޼ҵ�(�������� ��������)
	public DefaultTableModel delete(String hak, DefaultTableModel model);  //����
	public void save();
	public DefaultTableModel load(DefaultTableModel model);
	/*
	public load();
	public save();
	*/
	
}
