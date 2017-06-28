package liang.no3.Thread;

import javax.xml.bind.DatatypeConverter;

public class JoinDigestUserInterface {
	public static String[] FILES={"E:/a.txt","E:/aa.txt","E:/b.txt","E:/bb.txt"};
	public static void main(String[] args) {
		ReturnDigest[] digestThreads=new ReturnDigest[FILES.length];
		for (int i = 0; i < FILES.length; i++) {
			digestThreads[i]=new ReturnDigest(FILES[i]);
			digestThreads[i].start();
		}
		
		for (int i = 0; i < FILES.length; i++) {
			try {
				digestThreads[i].join();
				StringBuffer result=new StringBuffer(FILES[i]);
				result.append(": ");
				byte[] digest=digestThreads[i].getDigest();
				result.append(DatatypeConverter.printHexBinary(digest));
				System.out.println(result);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
