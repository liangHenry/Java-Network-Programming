package liang.no7.URLConnectTest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {
	public static void main(String[] args) {
		try {
			URL u = new URL("");
			URLConnection uc=u.openConnection();
			try(InputStream raw=uc.getInputStream()){
				InputStream buffer=new BufferedInputStream(raw);
				//将InputStream串链到一个Reader
				Reader reader=new InputStreamReader(buffer);
				int c;
				while ((c=reader.read())!=-1) {
					System.out.println((char)c);
				}
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
