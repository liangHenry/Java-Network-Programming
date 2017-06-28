package liang.no5.URLURITest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryString {
	private StringBuilder query =new StringBuilder();
	
	public QueryString() {
		// TODO Auto-generated constructor stub
	}
	public synchronized void add(String name ,String value){
		query.append('&');
		encode(name,value);
	}
	private synchronized void encode(String name, String value) {
		try {
			query.append(URLEncoder.encode(name, "UTF-8"));
			query.append('=');
			query.append(URLEncoder.encode(value,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized String getQuery(){
		return query.toString();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getQuery();
	}
	public static void main(String[] args) {
		QueryString qs=new QueryString();
		qs.add("hl", "en");
		qs.add("as_q", "java");
		qs.add("as_epq", "I/O");
		String url="http://www.google.com/search?"+qs;
		System.out.println(url);
	}
}
