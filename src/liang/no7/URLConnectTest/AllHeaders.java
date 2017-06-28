package liang.no7.URLConnectTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AllHeaders {
	public static void main(String[] args) {
		try {
			URL u = new URL("http://www.baidu.com");
			URLConnection uc=u.openConnection();
			for(int i=1;;i++){
				String header=uc.getHeaderField(i);
				if (header==null) {
					break;
				}
				System.out.println(uc.getHeaderFieldKey(i)+"--"+header);
			}
			
		} catch (MalformedURLException e) {
			System.out.println("1");
		} catch (IOException e) {
			System.out.println("2");
		}
		
	}
}
