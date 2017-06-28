package liang.no4.Internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReillyByName {
	public static void main(String[] args) {
		try {
			InetAddress address1=InetAddress.getByName("www.aiyuke.com");
			System.out.println(address1);
			
			InetAddress[] address=InetAddress.getAllByName("www.sina.com.cn");
			for (InetAddress inetAddress : address) {
				System.out.println(inetAddress);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
