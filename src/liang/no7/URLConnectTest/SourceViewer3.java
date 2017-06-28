package liang.no7.URLConnectTest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SourceViewer3 {
	public static void main(String[] args) {
		try {
			URL url=new URL("http://www.baidu.com");
			HttpURLConnection uc=(HttpURLConnection) url.openConnection();
			int code=uc.getResponseCode();
			String response=uc.getResponseMessage();
			System.out.println("HTTP/1.x "+code+" "+ response);
			
			for (int i = 0; ; i++) {
				String header =uc.getHeaderField(i);
				String key =uc.getHeaderFieldKey(i);
				if(header==null||key==null){
					break;
				}
				System.out.println(key+" "+header);
			}
			System.out.println();
			try(InputStream in =new BufferedInputStream(uc.getInputStream())){
				Reader r = new InputStreamReader(in);
				int c;
				while((c=r.read())!=-1){
					System.out.println((char)c);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
