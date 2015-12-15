import java.net.*;

import org.omg.IOP.Encoding;

import java.io.*;


public class SocketClient {
	public void connectServer(int score) throws UnknownHostException, IOException {
//		if((args.length<2)||(args.length>3))
//			throw new IllegalArgumentException("Parameter(s):<Server><Word>[<Port>]");
//		String server=args[0];
//		byte[] data=args[1].getBytes();
//		int servPort=(args.length==3)?Integer.parseInt(args[2]):7;
//		Socket socket=new Socket(server,servPort);
//		System.out.println("Connected to server...sending echo string");
//		InputStream in=socket.getInputStream();
//		OutputStream out=socket.getOutputStream();
//		out.write(data);
//		int totalBytesRcvd=0;
//		int bytesRcvd;
//		while(totalBytesRcvd<data.length){
//			if((bytesRcvd=in.read(data, totalBytesRcvd, data.length-totalBytesRcvd))==-1)
//				throw new SocketException("Connection closed prematurely");
//			totalBytesRcvd+=bytesRcvd;
//		}
//		System.out.println("Received:"+new String(data));
//		socket.close();
		Socket s = new Socket("192.168.191.1",5566); //创建一个Socket对象，连接IP地址为192.168.24.177的服务器的5566端口
		
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream()); //获取Socket对象的输出流，并且在外边包一层DataOutputStream管道，方便输出数据  
	  
	    try {
			Thread.sleep((int)(Math.random()*3000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //让客户端不定时向服务器发送消息  
	    dos.writeInt(score); //DataOutputStream对象的writeUTF()方法可以输出数据，并且支持中文  
	    dos.flush(); //确保所有数据都已经输出  
	    dos.close(); //关闭输出流  
	    s.close(); //关闭Socket连接  
	}
	
}
