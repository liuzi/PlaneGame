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
		Socket s = new Socket("192.168.191.1",5566); //����һ��Socket��������IP��ַΪ192.168.24.177�ķ�������5566�˿�
		
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream()); //��ȡSocket��������������������߰�һ��DataOutputStream�ܵ��������������  
	  
	    try {
			Thread.sleep((int)(Math.random()*3000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //�ÿͻ��˲���ʱ�������������Ϣ  
	    dos.writeInt(score); //DataOutputStream�����writeUTF()��������������ݣ�����֧������  
	    dos.flush(); //ȷ���������ݶ��Ѿ����  
	    dos.close(); //�ر������  
	    s.close(); //�ر�Socket����  
	}
	
}
