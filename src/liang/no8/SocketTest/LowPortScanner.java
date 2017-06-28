package liang.no8.SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class LowPortScanner {
	public static void main(String[] args) {
		String host="localhost";
		for(int i=1;i<1024;i++){
			try {
				Socket socket=new Socket(host, i);
				System.out.println("There is a server on port "+i+" of host");
				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				break;
			} catch (IOException e) {
				//这个端口上不是一个服务器
			}
		}
	}
	public static void main1(String[] args) throws IOException {
		//Socket s=new Socket();
		SocketAddress address=new InetSocketAddress("10.46.101.18", 808);
		//s.connect(address);
		Proxy proxy=new Proxy(Proxy.Type.HTTP,address);
		Socket s=new Socket(proxy);
		SocketAddress remote=new InetSocketAddress("time.nist.gov", 37);
		s.connect(remote);
		
	}

}
