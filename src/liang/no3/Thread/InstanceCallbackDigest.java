package liang.no3.Thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class InstanceCallbackDigest implements Runnable{
	private String filename;
	private InstanceCallbackDigestUserInterface callback;
	
	public InstanceCallbackDigest(String filename,InstanceCallbackDigestUserInterface callback) {
		this.filename=filename;
		this.callback=callback;
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
			callback.receiveDigest(digest);
		} catch (IOException|NoSuchAlgorithmException e) {
			System.err.println(e);
		}
	}
}
