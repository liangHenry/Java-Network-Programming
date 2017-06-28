package liang.no4.Internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReverseTest {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress ia=InetAddress.getByName("121.201.5.169");
		System.out.println(ia.getCanonicalHostName());
	}
}
