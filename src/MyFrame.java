import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

//�Զ���frame �ⴰ��,

public class MyFrame extends JFrame{
	/**
	 * @param args���洰��
	 */
	private int w;
	private int h;
	public static int hh;
	private JPanel jpanel;
	public int getH() {
		return h;
	}
	/*
	 * default construct for init window's width and height
	 * */
	public MyFrame(){
		Util.fireup();
		this.w=Util.jframex;
		this.h=Util.jframey;
		hh=this.h;
		jpanel=new MyJPanel();
	}
	public void showFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(w,h);
		setTitle("�ɻ���ս");
		add(jpanel);
		setVisible(true);
		MyThread thread=((MyJPanel)jpanel).getMth();
		addKeyListener(thread);
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MyFrame frame=new MyFrame();
		frame.showFrame();
		
//		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
//		//���ӷ����������ݿ�Game 
//		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=PlaneGame"; 
//		String userName = "sa"; //Ĭ���û��� 
//		String userPwd = "1234"; //���� 
//
//		Connection dbConn=null; 
//
//		try { 
//		Class.forName(driverName); 
//		dbConn = DriverManager.getConnection(dbURL, userName, userPwd); 
//		System.out.println("Connection Successful!"); //������ӳɹ� ����̨���
//		}
//		catch (Exception e) { 
//			e.printStackTrace(); 
//			} 
//	

	}
}
