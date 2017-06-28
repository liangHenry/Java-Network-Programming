package liang.no4.Internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {
	public static void main1(String[] args) {
		try {
			InetAddress address=InetAddress.getLocalHost();
			System.out.println(address);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main2(String[] args) {
		try {
			InetAddress address=InetAddress.getLocalHost();
			
			String dottedQiad=address.getHostAddress();
			
			System.out.println("My Address is "+dottedQiad);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main3(String[] args) {
		try {
			InetAddress address=InetAddress.getLocalHost();
			
			byte[] b=address.getAddress();
			
			System.out.println(b.length);
			for (byte c : b) {
				System.out.println(c);
			}
			System.out.println("My Address is "+b);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			InetAddress a=InetAddress.getLocalHost();
			System.out.println("isAnyLocalAddress:  "+a.isAnyLocalAddress());
			System.out.println("isLoopbackAddress: "+a.isLoopbackAddress());
			System.out.println("isLinkLocalAddress: "+a.isLinkLocalAddress());
			System.out.println("isSiteLocalAddress: "+a.isSiteLocalAddress());
			System.out.println("isMulticastAddress: "+a.isMulticastAddress());
			System.out.println("isMCGlobal: "+a.isMCGlobal());
			System.out.println("isMCNodeLocal: "+a.isMCNodeLocal());
			System.out.println("isMCLinkLocal: "+a.isMCLinkLocal());
			System.out.println("isMCSiteLocal: "+a.isMCSiteLocal());
			System.out.println("isMCOrgLocal: "+a.isMCOrgLocal());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
