package liang.no8.SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DayTimeClient {
	public static void main(String[] args) {
		String hostname="time.nist.gov";
		Socket socket=null;
		try {
			socket=new Socket(hostname,13);
			socket.setSoTimeout(15000);
			InputStream in =socket.getInputStream();
			StringBuilder sb=new StringBuilder();
			InputStreamReader reader=new InputStreamReader(in,"ASCII");
			for (int i = reader.read(); i !=-1; i= reader.read()) {
				sb.append((char)i);
			}
			System.out.println(sb);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
