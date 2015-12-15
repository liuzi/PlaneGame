import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

//自定义frame 外窗口,

public class MyFrame extends JFrame{
	/**
	 * @param args界面窗口
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
		setTitle("飞机大战");
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
//		//连接服务器和数据库Game 
//		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=PlaneGame"; 
//		String userName = "sa"; //默认用户名 
//		String userPwd = "1234"; //密码 
//
//		Connection dbConn=null; 
//
//		try { 
//		Class.forName(driverName); 
//		dbConn = DriverManager.getConnection(dbURL, userName, userPwd); 
//		System.out.println("Connection Successful!"); //如果连接成功 控制台输出
//		}
//		catch (Exception e) { 
//			e.printStackTrace(); 
//			} 
//	

	}
}
