package liang.no5.URLURITest;

import java.net.URI;
import java.net.URISyntaxException;

public class URISplittler {
	public static void main(String[] args) {
		String[] s=new String[]{"",""};
		
		for (int i = 0; i < s.length; i++) {
			try {
				URI u=new URI(s[i]);
				System.out.println("The URI is "+u);
				
				if(u.isOpaque()){
					System.out.println("This is an opaque URI.");
					System.out.println("The scheme is "+u.getScheme());
					System.out.println("The scheme specific part is "+u.getSchemeSpecificPart());
					
					System.out.println("The fragment ID is "+u.getFragment());
					
				}else{
					System.out.println("This is a hierarchical URI.");
					System.out.println("The scheme is "+u.getScheme());
					try {
						u=u.parseServerAuthority();
						System.out.println("The host is "+u.getHost());
						System.out.println("The user info is "+u.getUserInfo());
						System.out.println("The post is "+u.getPort());
						
					} catch (URISyntaxException e) {
						System.out.println("The authority is "+u.getAuthority());
					}
					System.out.println("The path is "+u.getPath());
					System.out.println("The query string is "+u.getQuery());
					System.out.println("The fragment ID is "+u.getFragment());
				}
				
				
			} catch (URISyntaxException e) {
				System.err.println(s[i]+" does not seem to be a URI");
			}
			System.out.println();
			
		}
		
	}
}
