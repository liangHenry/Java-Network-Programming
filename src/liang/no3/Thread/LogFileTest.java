package liang.no3.Thread;

import java.io.File;
import java.io.IOException;

public class LogFileTest implements Runnable{
	private String filename;
	public LogFileTest(String filename) {
		this.filename = filename;
	}

	public static void main(String[] args) {
		String[] s={"E:/z.txt","E:/z1.txt","E:/z2.txt","E:/z3.txt","E:/z4.txt","E:/z5.txt","E:/z6.txt","E:/z7.txt"};
		for(String file:s){
			new Thread(new LogFileTest(file)).start();
		}
	}

	@Override
	public void run() {
		try {
			LogFile file=new LogFile(new File(filename));
			file.writeEntry(filename+": "+filename+":adfnmadfjakhfdkdhfuiadf hadfnamdfad ufadf,dfakfjadfoia");
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
