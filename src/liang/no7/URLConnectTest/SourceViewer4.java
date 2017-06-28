package liang.no7.URLConnectTest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer4 {
	public static void main(String[] args) {
		try {
			URL url=new URL("http://www.baidu.com");
			HttpURLConnection uc=(HttpURLConnection) url.openConnection();
			try(InputStream in=uc.getInputStream()){
				printFromStream(in);
			}catch (IOException e) {
				printFromStream(uc.getErrorStream());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	private static void printFromStream(InputStream raw)  throws IOException{
		try(InputStream buffer=new BufferedInputStream(raw);Reader r=new InputStreamReader(buffer);){
			int c ;
			while((c=r.read())!=-1){
				System.out.println((char)c);
			}
			
		}
		
	}
}
