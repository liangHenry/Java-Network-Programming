package liang.no5.URLURITest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ProtocolTester {
	private static void testProtocol(String url){
		try {
			URL u=new URL(url);
			System.out.println(u.getProtocol()+" is supported");
		} catch (MalformedURLException e) {
			String protocol=url.substring(0,url.indexOf(':'));
			System.out.println(protocol+" is not true");
		}
	}
	
	
	public static void main1(String[] args) {
		//���ı�����Э��
		testProtocol("http://www.adc.org");
		
		//��ȫhttp
		testProtocol("https://www.amazon.com/exec/obidos/order2/");
		
		//�ļ�����Э��
		testProtocol("ftp://ibiblio.org/pub/languages/java/javafaq/");
		
		//���ʼ�����Э��
		testProtocol("mailto:elharo@ibilio.org");
		
		//telnet
		testProtocol("telnet://dibner.poly.edu/");
		
		//�����ļ�����
		testProtocol("file:///E:/a.txt");
		
		//gopher
		testProtocol("gopher://gopher.anc.org.za");
		
		//������Ŀ¼����Э��
		testProtocol("ldap://ldap.itd.umich.edu/o=University%20of%20Michigan,c=US?postallAddress");
		
		//JAR
		testProtocol("jar:http://cafeaulait.org/books/javaio/ioexamples/javaio.jar!/com/macfaq/io/StreamCopier.class");
		
		//NFS,�����ļ�ϵͳ
		testProtocol("nfs://utopia.poly.edu/usr/tmp");
		
		//JDBC�Ķ���Э��
		testProtocol("jdbc:mysql://luna.ibiblio.org:3306/NEWS");
		
		//rmi,Զ�̷������õĶ���Э��
		testProtocol("rmi://ibiblio.org/RenderEngine");
		
		//HotJava�Ķ���Э��
		testProtocol("doc:/UsersGuide/release.html");
		testProtocol("netdoc:/UsersGuide/release.html");
		testProtocol("systemresource://www.adc.org/+/index.html");
		testProtocol("verbatim:http://www.adc.org/");
		
	}
	
	public static void main(String[] args) {
		try {
			URL u=new URL("http://www.baidu.com");
			InputStream in =u.openStream();
			int c;
			while((c=in.read())!=-1)
				System.out.println(c);
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
