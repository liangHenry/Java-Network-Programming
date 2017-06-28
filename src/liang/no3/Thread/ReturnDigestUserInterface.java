package liang.no3.Thread;

import javax.xml.bind.DatatypeConverter;

public class ReturnDigestUserInterface {
	public static void main1(String[] args) {
		String[] s={"E:/a.txt","E:/aa.txt","E:/b.txt","E:/bb.txt"};
		for(String sa:s){
			ReturnDigest dr=new ReturnDigest(sa);
			dr.start();
			
			StringBuilder result=new StringBuilder(sa);
			result.append(": ");
			byte[] digest=dr.getDigest();
			result.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(result);
		}
	}
	public static void main2(String[] args) {
		String[] s={"E:/a.txt","E:/aa.txt","E:/b.txt","E:/bb.txt"};
		ReturnDigest[] dr=new ReturnDigest[s.length];
		for(int i=0;i<s.length;i++){
			dr[i]=new ReturnDigest(s[i]);
			dr[i].start();
		}
		for(int i=0;i<s.length;i++){
			StringBuilder result=new StringBuilder(s[i]);
			result.append(": ");
			byte[] digest=dr[i].getDigest();
			result.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(result);
		}
	}
	
	
	public static void main(String[] args) {
		String[] s={"E:/a.txt","E:/aa.txt","E:/b.txt","E:/bb.txt"};
		ReturnDigest[] dr=new ReturnDigest[s.length];
		
		for(int i=0;i<s.length;i++){
			dr[i]=new ReturnDigest(s[i]);
			dr[i].start();
		}
		for(int i=0;i<s.length;i++){
			while(true){
				byte[] digest=dr[i].getDigest();
				if(digest!=null){
					StringBuilder result=new StringBuilder(s[i]);
					result.append(": ");
					result.append(DatatypeConverter.printHexBinary(digest));
					System.out.println(result);
					break;
				}
			}
		}
		
		
	}
}
