package liang.no4.Internet;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class InetfaceLister {
	public static void main(String[] args) throws SocketException {
		Enumeration<NetworkInterface> interfaces= NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) interfaces
					.nextElement();
			System.out.println(ni);
			
		}
	}
}
