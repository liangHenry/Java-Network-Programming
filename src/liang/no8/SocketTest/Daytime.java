package liang.no8.SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daytime {
	public Date getDateFromNetwork() throws IOException,ParseException{
		try(Socket socket=new Socket("time.nist.gov",13)){
			socket.setSoTimeout(15000);
			InputStream in=socket.getInputStream();
			StringBuffer sb=new StringBuffer();
			InputStreamReader r=new InputStreamReader(in,"ASCII");
			for(int i=r.read() ;i!=-1;i=r.read()){
				sb.append((char)i);
			}
			return parseDate(sb.toString());
		}
	}
	static Date parseDate(String s) throws ParseException{
		String[] pieces=s.split(" ");
		String dateTime=pieces[1]+" "+pieces[2]+" UTC";
		DateFormat format=new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
		return format.parse(dateTime);
	}
}
