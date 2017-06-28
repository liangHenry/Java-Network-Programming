package liang.no5.URLURITest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentGetter {
	public static void main(String[] args) {
		try {
			URL u=new URL("http://www.baidu.com");
			Object o=u.getContent();
			System.out.println("I got a "+o.getClass().getName());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
