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

public class FormPoster {
	private URL url;
	private QueryString query=new QueryString();
	
	public FormPoster(URL url){
		if(!url.getProtocol().toLowerCase().startsWith("http")){
			throw new IllegalArgumentException("Posting only works for http URLs");
		}
		this.url=url;
	}
	
	public void add(String name,String value){
		query.add(name, value);
	}
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
			out.write(query.toString());
			out.write("\r\n");
			out.flush();
		}
		return uc.getInputStream();
	}
	
	public static void main(String[] args) {
		URL url;
		try {
			url=new URL("http://www.cafeaulait.org/book/jnp4/postquery.html");
		} catch (MalformedURLException e) {
			System.out.println(e);
			return;
		}
		FormPoster poster=new FormPoster(url);
		poster.add("name","Elliotte Rusty Harold");
		poster.add("email", "elharo@ibiblio.org");
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
	
	
	
	
	
	
	
}
