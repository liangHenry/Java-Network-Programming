package liang.no5.URLURITest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer {
	public static void main(String[] args) {
		if(1>0){
			InputStream in = null;
			try {
				//打开URL进行读取
				URL u=new URL("http://www.oreilly.com");
				in=u.openStream();
				//缓冲输入以提高性能
				in=new BufferedInputStream(in);
				//将InputStream串链到一个Reader
				Reader r=new InputStreamReader(in);
				int c;
				while((c=r.read())!=-1){
					System.out.println((char)c);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in!=null) {
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
