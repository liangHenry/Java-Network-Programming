package liang.no7.URLConnectTest;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

public class EncodingAwareSourceViewer {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			try {
				String encoding="ISO-8859-1";
				URL u=new URL(args[i]);
				URLConnection uc=u.openConnection();
				String contentType=uc.getContentType();
				int encodingStrart=contentType.indexOf("charset=");
				if(encodingStrart!=-1){
					encoding=contentType.substring(encodingStrart+8);
				}
				InputStream in = new BufferedInputStream(uc.getInputStream());
				Reader reader=new InputStreamReader(in);
				
				int c ;
				
				while ((c=reader.read())!=-1) {
					System.out.println((char)c);
				}
				reader.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
