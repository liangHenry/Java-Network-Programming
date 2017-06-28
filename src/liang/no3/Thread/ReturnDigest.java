package liang.no3.Thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnDigest extends Thread{
	private String filename;
	private byte[] digest;
	
	public ReturnDigest(String filename){
		this.filename=filename;
	}
	@Override
	public void run() {
		try {
			FileInputStream in =new FileInputStream(filename);
			MessageDigest sha=MessageDigest.getInstance("SHA-256");
			DigestInputStream din =new DigestInputStream(in, sha);
			while(din.read()!=-1);
			din.close();
			digest=sha.digest();
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
	public byte[] getDigest(){
		return digest;
	}
	
}
