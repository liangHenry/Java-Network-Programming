package liang.no7.URLConnectTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class HearderViewer {
	public static void main(String[] args) {
		try {
			URL u=new URL("");
			URLConnection uc=u.openConnection();
			System.out.println("ContentType:"+uc.getContentType());
			
			if(uc.getContentEncoding()!=null){
				System.out.println("encoding:"+uc.getContentEncoding());
			}
			if(uc.getExpiration()!=0) {
				System.out.println("Expiration:"+uc.getExpiration());
			}
			if(uc.getDate()!=0){
				System.out.println("date:"+new Date(uc.getDate()));
			}
			if(uc.getLastModified()!=0){
				System.out.println("LastModified:"+uc.getLastModified());
			}
			if(uc.getContentLength()!=-1){
				System.out.println("ContentLenth:"+uc.getContentLength());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
