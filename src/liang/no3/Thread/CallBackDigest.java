package liang.no3.Thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CallBackDigest implements Runnable{
	
	private String filename;
	
	public CallBackDigest(String filename){
		this.filename=filename;
	}

	@Override
	public void run() {
		try {
			FileInputStream in =new FileInputStream(filename);
			MessageDigest sha=MessageDigest.getInstance("SHA-256");
			DigestInputStream dig=new DigestInputStream(in, sha);
			while(dig.read()!=-1);
			dig.close();
			byte[] digest=sha.digest();
			CallbackDigestUserInterface.receiveDigest(digest, filename);
			
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

}
