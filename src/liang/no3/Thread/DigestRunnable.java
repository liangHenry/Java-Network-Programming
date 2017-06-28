package liang.no3.Thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;


public class DigestRunnable implements Runnable{

	private String filename;
	
	public DigestRunnable(String filename){
		this.filename=filename;
	}
	
	@Override
	public void run() {
		try {
			FileInputStream in =new FileInputStream(filename);
			
			MessageDigest sha =MessageDigest.getInstance("SHA-256");
			
			DigestInputStream din =new DigestInputStream(in, sha);
			
			while(din.read()!=-1);
			din.close();
			byte[] digest=sha.digest();
			StringBuffer result = new StringBuffer(filename);
			result.append(": ");
			result.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(result);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String[] s={"E:/a.txt","E:/aa.txt","E:/b.txt","E:/bb.txt"};
		for(String ss:s){
			DigestRunnable dr=new DigestRunnable(ss);
			Thread t=new Thread(dr);
			t.start();
		}
	}
}
