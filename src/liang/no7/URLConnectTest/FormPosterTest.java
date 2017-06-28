package liang.no7.URLConnectTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import liang.no5.URLURITest.QueryString;

public class FormPosterTest {
	private URL url;
	private QueryString query=new QueryString();
	
	public FormPosterTest(URL url){
		if(!url.getProtocol().toLowerCase().startsWith("http")){
			throw new IllegalArgumentException("Posting only works for http URLs");
		}
		this.url=url;
	}
	
	/*public void add(String name,String value){
		query.add(name, value);
	}*/
	public URL getURL(){
		return this.url;
	}

	public InputStream post() throws IOException{
		URLConnection uc=url.openConnection();
		uc.setDoOutput(true);
		try(OutputStreamWriter out=new OutputStreamWriter(uc.getOutputStream(),"UTF-8")){
			//POST行。Content-type首部和Content-length首部
			//由URLConnection发送
			//我们只需要发送数据
			//out.write(query.toString());
			//out.write("\r\n");
			out.flush();
			out.close();
		}
		return uc.getInputStream();
	}
	
	public static void main1(String[] args) {
		URL url;
		try {
			url=new URL("http://www.baidu.com");
		} catch (MalformedURLException e) {
			System.out.println(e);
			return;
		}
		FormPosterTest poster=new FormPosterTest(url);
		/*poster.add("name","Elliotte Rusty Harold");
		poster.add("email", "elharo@ibiblio.org");*/
		try(InputStream in=poster.post()){
			Reader r =new InputStreamReader(in);
			int c;
			while((c=r.read())!=-1){
				System.out.println((char)c);
			}
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		URL url=new URL("http://www.baidu.com");
		URLConnection uc=url.openConnection();
		InputStream in=uc.getInputStream();
		int c;
		while((c=in.read())!=-1){
			System.out.println((char)c);
		}
		System.out.println();
		in.close();
	}
	
	
	
	
}
