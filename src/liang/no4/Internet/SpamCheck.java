package liang.no4.Internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamCheck {
	public static final String BLACKHOLE="sbl.spamhaus.org";
	
	private static boolean isSpammer(String arg){
		try {
			InetAddress address =InetAddress.getByName(arg);
			byte[] quad=address.getAddress();
			String query=BLACKHOLE;
			for (byte octed : quad) {
				int unsignedByte=octed<0?octed+256:octed;
				query=unsignedByte+"."+query;
			}
			System.out.println(InetAddress.getByName(query));
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		String[] s={"207.34.56.23","125.12.32.4","130.130.130.130","10.46.2.55"};
		for (String arg : s) {
			if (isSpammer(arg)) {
				System.out.println(arg+" is a know spammer.");
			}else{
				System.out.println(arg+" appears legitimate.");
			}
		}
	}
	
}
