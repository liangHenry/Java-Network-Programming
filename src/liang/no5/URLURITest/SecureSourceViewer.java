package liang.no5.URLURITest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

public class SecureSourceViewer {
	public static void main(String[] args) {
		String[] ars = {};
		Authenticator.setDefault(new DialogAuthenticator());

		for (int i = 0; i < ars.length; i++) {
			try {
				URL u = new URL(ars[i]);
				try (InputStream in = new BufferedInputStream(u.openStream())) {
					Reader r = new InputStreamReader(in);
					int c;
					while ((c = r.read()) != -1) {
						System.out.println((char) c);
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		}
		System.exit(0);
	}
}
