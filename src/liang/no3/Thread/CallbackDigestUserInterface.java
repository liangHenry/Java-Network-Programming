package liang.no3.Thread;

import javax.xml.bind.DatatypeConverter;

public class CallbackDigestUserInterface {
	public static void receiveDigest(byte[] digeset,String name){
		StringBuilder result=new StringBuilder(name);
		result.append(": ");
		result.append(DatatypeConverter.printHexBinary(digeset));
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		String[] s={"E:/a.txt","E:/aa.txt","E:/b.txt","E:/bb.txt"};
		for(String sa:s){
			CallBackDigest cb=new CallBackDigest(sa);
			Thread t =new Thread(cb);
			t.start();
		}
	}
}
