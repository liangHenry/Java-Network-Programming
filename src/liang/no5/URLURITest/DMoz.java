package liang.no5.URLURITest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class DMoz {
	public static void main(String[] args) {
		String target="";
		for (int i = 0; i < args.length; i++) {
			target+=args[i]+" ";
		}
		target=target.trim();
		QueryString query=new QueryString();
		query.add("Q", target);
		
		try {
			URL u=new URL("http://www.dmoz.org/search/q?"+query);
			try(InputStream in =new BufferedInputStream(u.openStream());){
				InputStreamReader theHTML=new InputStreamReader(in);
				int c;
				while ((c=theHTML.read())!=-1) {
					System.out.println((char)c);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
