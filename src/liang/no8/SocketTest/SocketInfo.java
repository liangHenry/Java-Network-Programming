package liang.no8.SocketTest;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketInfo {
	public static void main(String[] args) {
		String host="www.baidu.com";
		try {
			Socket socket=new Socket(host, 80);
			System.out.println("Connected to "+socket.getInetAddress()+" on port "+
			socket.getPort()+" from port "+socket.getLocalPort()+" of "+socket.getLocalAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
