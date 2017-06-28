package liang.no3.Thread;

import javax.xml.bind.DatatypeConverter;

public class InstanceCallbackDigestUserInterface {
	private String filename;
	private byte[] digest;
	
	public InstanceCallbackDigestUserInterface(String filename){
		this.filename=filename;
	}
	public void calculateDigest(){
		InstanceCallbackDigest cb=new InstanceCallbackDigest(filename, this);
		Thread t=new Thread(cb);
		t.start();
	}
	
	void receiveDigest(byte[] digest){
		this.digest=digest;
		System.out.println(this);
	}
	@Override
	public String toString() {
		String result=filename+": ";
		if(digest!=null){
			result+=DatatypeConverter.printHexBinary(digest);
		}else{
			result+="digest not available";
		}
		
		return result;
	}
	public static void main(String[] args) {
		String[] s={"E:/a.txt","E:/aa.txt","E:/b.txt","E:/bb.txt"};
		for (String sa:s) {
			InstanceCallbackDigestUserInterface d =new InstanceCallbackDigestUserInterface(sa);
			d.calculateDigest();
		}
	}
}
